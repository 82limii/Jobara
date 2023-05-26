package kr.co.jobara.commons.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.jobara.commons.dao.AttatchDAO;
import kr.co.jobara.commons.vo.AttatchVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AttatchService {

	@Inject
	private AttatchDAO attatchDao;

	/**
	 * @param files 첨부파일 배열
	 * @param biztype 저장할 폴더명
	 * @return attSn
	 */
	public String processAttatchFile(MultipartFile[] files, String biztype) {
		
		// 날짜형식 폴더 지정
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date time = new Date();
		String nowTime = format.format(time); //20220228
		
		//폴더 생성
		String path = "D:\\fileSave\\"+nowTime+"\\"+biztype;
		log.debug(path);
		File folder = new File(path);
		if(!folder.exists()) {
			try {
				folder.mkdirs();
				log.debug("폴더 생성 완료");
			} catch (Exception e) {
				e.getStackTrace();
			}
		} else {
			log.debug("이미 생성되어있음");
		}
		
		if(files == null) {
			return null;
		}
		
		List<AttatchVO> attatchList = new ArrayList<>();
		String nextVal = null;
		for(MultipartFile file : files) {
			if(file.isEmpty()) {
				continue;
			}
			else {
				attatchList.add(new AttatchVO(file));
				nextVal = attatchDao.selectNextVal();
			}
		}
		
		// 메타데이터 저장
		int i = 1;
		for(AttatchVO attatch : attatchList) {
			attatch.setAttNum(i+"");
			attatch.setAttRoute(path);
			attatch.setAttSn(nextVal);
			attatchDao.insertAttatches(attatch);
			i++;
		}
		
		//2진데이터 저장
		for (AttatchVO attatchVO : attatchList) {
			try {
				attatchVO.saveTo(folder);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return nextVal;
	}
	
	/**
	 * 첨부파일 목록 불러오기
	 * @param attSn
	 * @return
	 */
	public List<AttatchVO> retrieveAttatchList(String attSn) {
		List<AttatchVO> list = attatchDao.selectAttatchList(attSn);
		return list;
	}
	
	/**
	 * 첨부파일 첫번째 하나만 불러오기
	 * @param attSn
	 * @return
	 */
	public AttatchVO retrieveAttatch(String attSn) {
		AttatchVO attatch = attatchDao.selectAttatch(attSn);
		return attatch;
	}
}
