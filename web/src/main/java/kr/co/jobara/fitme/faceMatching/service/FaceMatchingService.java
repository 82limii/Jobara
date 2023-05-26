package kr.co.jobara.fitme.faceMatching.service;

import java.util.List;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.fitme.faceMatching.vo.FaceMatchingVO;

/**
 * 작성일 2022.03.22
 * @author 김승현
 * 기업추천(CRD)용 Service
 */
public interface FaceMatchingService {
	
	/**
	 * 기업추천 입력
	 * @param faceMatching
	 * @return
	 */
	public ServiceResult createFaceMatching(FaceMatchingVO faceMatching);
	
	
	
	/**
	 * 기업추천 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public List<FaceMatchingVO> retrieveFaceMatchingList(PagingVO<FaceMatchingVO> pagingVO);
	
	/**
	 * 기업추천 상세조회
	 * @param pmemId
	 * @return
	 */
	public FaceMatchingVO retrieveFaceMatching(String pmemId);
	
	/**
	 * 기업추천 삭제
	 * @param faceMatching
	 * @return
	 */
	public ServiceResult removeFaceMatching(int faceSn);
	
}
