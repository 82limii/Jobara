package kr.co.jobara.review.comCerti.service;

import java.util.List;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.review.comCerti.vo.ComCertiVO;

/**
 * 작성일 2022.02.24
 * @author 김승현
 * 기업리뷰(CRUD)용 Service
 */
public interface ComCertiService {
	/**
	 * 기업리뷰 인증 작성
	 * @param comCerti
	 * @return
	 */
	public ServiceResult createComCerti(ComCertiVO comCerti);
	
	/**
	 * 기업리뷰 인증 상세조회
	 * @param coceSn
	 * @return
	 */
	public ComCertiVO retrieveComCerti(int coceSn);
	/**
	 * 기업리뷰 인증 목록조회
	 * @param paging
	 * @return
	 */
	public List<ComCertiVO> retrieveComCertiList(PagingVO<ComCertiVO> paging);

}
