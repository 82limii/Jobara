package kr.co.jobara.ftp.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.pool2.ObjectPool;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.jobara.ftp.vo.FTPFileWrapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 장인슬
 * @author PC-17
 * 2022-03-15
 */
@Service("ftpPoolService")
@Lazy
@Slf4j
//ftp서버 대상으로 파일관리 로직 서비스
public class FTPFileServiceImpl_WithPool implements FTPFileService {
	
	//FTPClientPool == beanid 에 등록되어 있음
	@Resource(name="FTPClientPool")
	private ObjectPool<FTPClient> clientPool;
	
	@Override
	public List<FTPFileWrapper> traversing(String path) throws Exception {
		 List<FTPFileWrapper> files = new ArrayList<>();
		 FTPClient ftpClient = null;
		 try {
			 //플링절차(주고받기)
			 ftpClient = clientPool.borrowObject();
			 
			 if(StringUtils.isNotBlank(path))
				 ftpClient.changeWorkingDirectory(path);
			 
			 FTPFile[] listFiles = ftpClient.listFiles();
			 if (listFiles != null) {
				for (FTPFile file : listFiles) {
					if (file == null)
						continue;
					files.add(new FTPFileWrapper(file));
				}
			 }
			 return files;
		}finally {
			ftpClient.changeWorkingDirectory("/");
			if(ftpClient!=null)
				clientPool.returnObject(ftpClient);
		}
	}
	
	@Override
	public boolean sendFile(String path, MultipartFile sendFile) throws Exception {
		 boolean success = true;
		 FTPClient ftpClient = null;
		 try {
			ftpClient = clientPool.borrowObject();
			if(StringUtils.isNotBlank(path))
				 ftpClient.changeWorkingDirectory(path);
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.storeFile(sendFile.getOriginalFilename(), sendFile.getInputStream());
			return success;
		}finally {
			ftpClient.changeWorkingDirectory("/");
			clientPool.returnObject(ftpClient);
		}
	}
	
	@Override
	//선택한 파일을 paths에 담겨져서 가져옴
	public Path receiveFiles(String[] paths) throws Exception{
		FTPClient ftpClient = null;
		org.springframework.core.io.Resource[] resources = new FileSystemResource[paths.length];
		 try {
			ftpClient = clientPool.borrowObject();
			//임시디렉토리 생성
			Path saveFolder = Files.createTempDirectory(null);
			//선택된 여러개의 파일을 for문으로 처리
			for(int idx = 0; idx < paths.length; idx++) {
				//ftpfile에 null값이 담김????? paths값은 가져오고 idx에는 0이먼저들어옴
				FTPFile ftpFile = ftpClient.mlistFile(paths[idx]);
				//파일과 파일이름을 file에 담아줌 
				File file = new File(saveFolder.toFile(), ftpFile.getName());
				//부모폴더 생성
				file.getParentFile().mkdirs();
				//빈파일 생성
				boolean created = file.createNewFile();
				if(created) {
					log.info("{} 생성", file.getCanonicalPath());
				}else {
					throw new IOException(file.getCanonicalPath()+"생성 실패");
				}
				try(
					//file에 있는 파일을 쓰기 위한 객체생성
					FileOutputStream os = new FileOutputStream(file); 	
				){
					//다운로드
					if(ftpClient.retrieveFile(paths[idx], os)) {
						resources[idx] = new FileSystemResource(file);
					}
				}
			}
			return saveFolder;
		}finally {
			ftpClient.changeWorkingDirectory("/");
			clientPool.returnObject(ftpClient);
		}
	}

}
