package kr.co.jobara.review.inter.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.review.inter.vo.InterVO;

/**
 * 작성일 2022.02.28
 * @author 김승현
 * 면접리뷰(CR)용 dao
 */

@Mapper
public interface InterDAO {
	/**
	 * 면접 리뷰 조회 페이징 기능
	 * @param pagingVO
	 * @return
	 */
	public int selectTotalRecord(PagingVO<InterVO> pagingVO);
	/**
	 * 면접 리뷰 조회
	 * @param paging
	 * @return
	 */
	public List<InterVO> selectInterList(PagingVO<InterVO> paging);
	/**
	 * 면접 리뷰 상세 조회
	 * @param interSn
	 * @return
	 */
	public InterVO selectInter(int interSn);
	/**
	 * 면접 리뷰 등록
	 * @param inter
	 * @return
	 */
	public int insertInter(InterVO inter);
	
}
