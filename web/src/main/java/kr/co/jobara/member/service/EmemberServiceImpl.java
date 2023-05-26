package kr.co.jobara.member.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.dao.EmemberDAO;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 장인슬
 * @author PC-17
 */
@Service
@Slf4j
public class EmemberServiceImpl implements EmemberService {

	
	@Inject
	private EmemberDAO ememberDAO;
	
	@Value("#{appInfo['member.attatch']}")
	private static File folder;

	
	private void encryptEmemPass(EmemberVO input) {
		//입력된 글자형태의 비밀번호데이터를 가져옴
		String plain = input.getEmemPass();
		//암호화 형태로 변경하여 변수에 담아줌
		String encoded = SecurityUtils.encode(plain);
		//암호화형태의 비밀번호를 vo에 담아주기
		input.setEmemPass(encoded);	
	}
	
	private void processeEmemImg(EmemberVO emem) {
		//vo에 담긴 전송된 이미지파일을 가져옴
		MultipartFile ememImag = emem.getEmemImg();
		//데이터가 들어오면
		if(!ememImag.isEmpty()) {
			String mime = ememImag.getContentType();
			//이미지형식인지 확인
			if(mime.startsWith("image/")) {
				//기존의파일명
				String fileName = ememImag.getOriginalFilename();
				//고유아이디 파일명
				String saveName = UUID.randomUUID().toString();
				//아이디와 폴더경로를 담아줌
				File saveFile = new File("D:\\saveFiles", saveName+"_"+fileName);
				try {
					//폴더에 저장
					ememImag.transferTo(saveFile);
					//디비에 경로 저장
					emem.setEmemPic(saveName+"_"+ fileName);
					log.info("ememImg!!! : " + emem.toString());
				}catch (IOException e) {
					throw new RuntimeException(e);
				}
				
			}// mime check
		}	
	}
	

	@Override
	public ServiceResult createEmember(EmemberVO emember) {
		//insert
		ServiceResult result = null;
		//암호화된 패스워드
		encryptEmemPass(emember);
		//고유이름의 이미지파일
		processeEmemImg(emember);
		int rowcnt = ememberDAO.insertEmember(emember);
		//rowcnt에 값이존재하면 ok, 없으면 fail
		result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		return result;
	}


	@Override
	public EmemberVO retrieveEmember(String ememId) {
		//selete
		EmemberVO emember = ememberDAO.selectEmember(ememId);
		if(emember == null)
			throw new PKNotFoundException(ememId+"에 해당하는 회원이 없음.");
		return emember;
	}


	@Override
	public ServiceResult updateEmember(EmemberVO emember) {
		//update
		ServiceResult result = null;
		//고유이름의 이미지파일
		processeEmemImg(emember);
		// 값이있으면 ok, 없으면 fail
		int rowcnt = ememberDAO.updateEmember(emember);
		result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL; 
		
		return result;
	}

	@Override
	public List<EmemberVO> retrieveEmemberList(PagingVO<EmemberVO> pagingVO) {
		
		int totalRecord = ememberDAO.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		//list로 정보를 조회해서 가져온다.
		List<EmemberVO> memberList = ememberDAO.selectEmemberList(pagingVO);
		//조회한list정보를 pagingvo에 넣어준다.
		pagingVO.setDataList(memberList);
		return memberList;
	}

	@Override
	public List<EmemberVO> searchResultCompany(String searchName) {
		return ememberDAO.selectSearchList(searchName);
	}

	@Override
	public EmemberVO selectId(String ememId) {
//		String cnt = ememberDAO.selectId(ememId);
		
		return ememberDAO.selectId(ememId);
	}



}
