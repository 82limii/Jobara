package kr.co.jobara.review.company.service;

import java.util.List;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.review.company.vo.ComReviewVO;

/**
 * 작성일 2022.02.23
 * @author 김승현
 * 기업리뷰(CRUD)용 Service
 */
public interface ComReviewService {
	/**
	 * 기업리뷰 작성
	 * @param comReview
	 * @return
	 */
	public ServiceResult createComReview(ComReviewVO comReview);
	
	/**
	 * 기업리뷰 상세조회
	 * @param reviewSn
	 * @return
	 */
	public ComReviewVO retrieveComReview(int reviewSn);
	/**
	 * 기업리뷰 목록조회
	 * @param paging
	 * @return
	 */
	public List<ComReviewVO> retrieveComReviewList(PagingVO<ComReviewVO> pagingVO);
	/**
	 * 기업리뷰 수정
	 * @param comReview
	 * @return
	 */
	public ServiceResult modifyComReview(ComReviewVO comReview);
	/**
	 * 기업리뷰 삭제
	 * @param comReview
	 * @return
	 */
//	public ServiceResult removeComReview(ComReviewVO comReview);
}
