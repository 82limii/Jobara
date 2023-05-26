package kr.co.jobara.ftp.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.jobara.ftp.service.FTPFileService;
import kr.co.jobara.ftp.utils.CompressUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 장인슬
 * @author PC-17
 * 2022-03-15
 */
@Controller
@RequestMapping("/ftp")
@Slf4j
public class FTPFileController {
	@Inject
	@Named("ftpPoolService")
	private FTPFileService service;
	
	@PostMapping("uploadFile.do")
	public String upload(
			String parentPath
			, @RequestPart(required=true) MultipartFile uploadFile
			, Model model
			, RedirectAttributes redirectAttributes) throws Exception{
		
		//service로 들어온 파일을 보내줌
		boolean success = service.sendFile(parentPath, uploadFile);
		
		if(success) {	
			//비동기지원, 일회성
			redirectAttributes.addFlashAttribute("parentPath", parentPath);
			return "redirect:/ftp/fileBrowser.do";
		}else {
			model.addAttribute("message", "전송 실패");
			return "ftp/fileForm";
		}
	}
	
	@PostMapping(value="fileDownload.do")
	public ResponseEntity<StreamingResponseBody> download(
			String[] downFiles
			, Model model) throws Exception{
		Path saveFolder = service.receiveFiles(downFiles);
		log.info("임시 파일 저장 위치 : {}", saveFolder);
		
		// Spring4 이후의 API 로 servlet spec 3 이후의 async request 처리를 지원함.
		// DispatcherServlet을 포함한 모든 필터와 servlet에 async-supported 설정이 필요함.
		// web.xml 및 servlet-context.xml 참고.
		
		
		
		
		//쓰레들르 비동기로 가지고 있다가 쓸일이 없으면 풀어준다. 
		//async-supportedq 필터들을 비동기로 만들어야 디스패쳐까지 도달할수 있음
		
		//비정형화된 Byte 응답을 Streaming형태로 줄 때 사용
		StreamingResponseBody streamResponseBody = new StreamingResponseBody() {
			@Override
			public void writeTo(OutputStream outputStream) throws IOException {
				try {
					//파일을 zip 형태로 보냄
					CompressUtils.compressIntoZip(saveFolder, outputStream);
					outputStream.flush();
				}finally {
					//사용후 닫아줌
					outputStream.close();
					FileUtils.deleteDirectory(saveFolder.toFile());
				}
			}
		};
		
		return ResponseEntity.ok()
					.header("Content-Disposition", "attachment; filename=download.zip")
					.contentType(MediaType.APPLICATION_OCTET_STREAM)
					.body(streamResponseBody);
	}
	
	
}
