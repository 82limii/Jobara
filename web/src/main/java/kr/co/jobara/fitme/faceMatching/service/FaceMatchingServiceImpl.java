package kr.co.jobara.fitme.faceMatching.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.fitme.faceMatching.dao.FaceMatchingDAO;
import kr.co.jobara.fitme.faceMatching.vo.FaceMatchingVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 작성일 2022.03.22
 * @author 김승현
 * 기업추천(CRD)용 ServiceImpl
 */
@Service
@Slf4j
public class FaceMatchingServiceImpl implements FaceMatchingService {
	
	@Inject
	private FaceMatchingDAO faceMatchingDAO;



	@Override
	public ServiceResult createFaceMatching(FaceMatchingVO faceMatching) {
		int rowcnt = faceMatchingDAO.insertFaceMatching(faceMatching);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public List<FaceMatchingVO> retrieveFaceMatchingList(PagingVO<FaceMatchingVO> pagingVO) {
		int totalRecord = faceMatchingDAO.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<FaceMatchingVO> faceMatching = faceMatchingDAO.selectFaceMatchingList(pagingVO);
		pagingVO.setDataList(faceMatching);
		return faceMatching;
	}

	@Override
	public ServiceResult removeFaceMatching(int faceSn) {
		int rowcnt = faceMatchingDAO.deleteFaceMatching(faceSn);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public FaceMatchingVO retrieveFaceMatching(String pmemId) {
		FaceMatchingVO faceMatching = faceMatchingDAO.selectFaceMatching(pmemId);
        if(faceMatching == null) {
        	faceMatching = new FaceMatchingVO();
        	faceMatching.setFaceMsg("신규");
        }
		return faceMatching;
	}


}
