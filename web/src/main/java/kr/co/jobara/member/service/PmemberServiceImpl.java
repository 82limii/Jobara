package kr.co.jobara.member.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import javax.inject.Inject;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.empInfo.vo.EmpInfoVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.dao.PmemberDAO;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.resume.vo.CareerVO;
import kr.co.jobara.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 장인슬
 * @author PC-17
 *
 */
@Service
@Slf4j
public class PmemberServiceImpl implements PmemberService {

	@Inject
	private PmemberDAO pmemberDAO;
	
	
	@Value("#{appInfo['member.attatch']}")
	private static File folder;
	
	
	private void encryptPmemPass(PmemberVO input) {
		//입력된 글자형태의 비밀번호데이터를 가져옴
		String plain = input.getPmemPass();
		//암호화 형태로 변경하여 변수에 담아줌
		String encoded = SecurityUtils.encode(plain);
		//암호화형태의 비밀번호를 vo에 담아주기
		input.setPmemPass(encoded);	
	}	
	
	private void processePmemImg(PmemberVO pmem) {
		//vo에 담긴 전송된 이미지파일을 가져옴
		MultipartFile pmemImag = pmem.getPmemImg();
		//데이터가 들어오면
		if(!pmemImag.isEmpty()) {
			String mime = pmemImag.getContentType();
			//이미지형식인지 확인
			if(mime.startsWith("image/")) {
				//기존의파일명
//				String fileName = pmemImag.getOriginalFilename();
				//고유아이디 파일명
				String saveName = UUID.randomUUID().toString();
				//아이디와 폴더경로를 담아줌
				File saveFile = new File("D:\\saveFiles", saveName + ".jpg");
				try {
					//폴더에 저장
					pmemImag.transferTo(saveFile);
					//디비에 경로 저장
					pmem.setPmemPic(saveName + ".jpg");
					
					log.info("pmem!!! : " + pmem.toString());
					
				}catch (IOException e) {
					throw new RuntimeException(e);
				}
				
			}// mime check
		}	
	}
	
	@Override
	public ServiceResult createPmember(PmemberVO pmember) {
		
		ServiceResult result = null;
		
		
		
		
		//파일업로드
		//이미지 파일이 있을때 
		processePmemImg(pmember);
		encryptPmemPass(pmember);
		int rowcnt = pmemberDAO.insertPmember(pmember);
		if(rowcnt == 0) {
			return ServiceResult.FAIL;
		}
		

        
//		//재직상태 등록
//		EmpInfoVO empinfo = new EmpInfoVO();
////		empinfo.setEmpSn(random);
		String ememId = pmember.getEmemId();
		if(ememId!=null) {
			int cnt = pmemberDAO.insertEmpInfo(pmember);
			if(cnt == 0) {
				return ServiceResult.FAIL;
			}
		}

		result = ServiceResult.OK;
		return result;

	}
	

	@Override
	public PmemberVO retrievePmember(String pmemId) {
		PmemberVO pmember = pmemberDAO.selectPmember(pmemId);
		if(pmember == null)
			throw new PKNotFoundException(pmemId+"에 해당하는 회원이 없음.");
		return pmember;
	}

	@Override
	public ServiceResult updatePmember(PmemberVO pmember) {
		
		ServiceResult result = null;
		processePmemImg(pmember);
		int rowcnt = pmemberDAO.updatePmember(pmember);
		result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL; 

		return result;
	}

	@Override
	public List<PmemberVO> retrievePmemberList(PagingVO<PmemberVO> pagingVO) {
		int totalRecord = pmemberDAO.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		//list로 정보를 조회해서 가져온다.
		List<PmemberVO> memberList = pmemberDAO.selectPmemberList(pagingVO);
		//조회한list정보를 pagingvo에 넣어준다.
		pagingVO.setDataList(memberList);
		return memberList;
	}
}
