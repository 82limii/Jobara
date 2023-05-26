package kr.co.jobara.review.certi.service;

import java.util.List;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.review.certi.vo.CertiVO;

/**
 * 작성일 2022.03.04
 * @author 김승현
 * 리뷰인증(CRU)용 Service
 */
public interface CertiService {
	
	/**
	 * 리뷰인증 입력
	 * @param certi
	 * @return
	 */
	public ServiceResult createCerti(CertiVO certi);
	
	/**
	 * 리뷰인증 상세조회
	 * @param certiSn
	 * @return
	 */
	public CertiVO retrieveCerti(int certiSn);
	
	/**
	 * 리뷰인증 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public List<CertiVO> retrieveCertiList(PagingVO<CertiVO> pagingVO);
	
	
	
}
