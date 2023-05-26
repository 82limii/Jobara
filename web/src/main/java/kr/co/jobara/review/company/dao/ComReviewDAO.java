package kr.co.jobara.review.company.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.review.company.vo.ComReviewVO;

/**
 * 작성일 2022.02.23
 * @author 김승현
 * 기업리뷰(CRUD)용 dao
 */

@Mapper
public interface ComReviewDAO {
	/**
	 * 기업 리뷰 조회 페이징 기능
	 * @param pagingVO
	 * @return
	 */
	public int selectTotalRecord(PagingVO<ComReviewVO> pagingVO);
	/**
	 * 기업 리뷰 조회
	 * @param paging
	 * @return
	 */
	public List<ComReviewVO> selectComReviewList(PagingVO<ComReviewVO> paging);
	/**
	 * 기업 리뷰 상세 조회
	 * @param reviewSn
	 * @return
	 */
	public ComReviewVO selectComReview(int reviewSn);
	/**
	 * 기업 리뷰 등록
	 * @param comReview
	 * @return
	 */
	public int insertComReview(ComReviewVO comReview);
	/**
	 * 기업 리뷰 수정
	 * @param comReview
	 * @return
	 */
	public int updateComReview(ComReviewVO comReview);
	/**
	 * 기업 리뷰 삭제
	 * @param reviewSn
	 * @return
	 */
//	public int deleteComReview(int reviewSn);
}
