package kr.co.jobara.review.coint.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.review.coint.vo.CointVO;
/**
 * 작성일 2022.02.28
 * @author 김승현
 * 리뷰리스트(R)용 dao
 */

@Mapper
public interface CointDAO {
	/**
	 * 리뷰리스트 조회 페이징 기능
	 * @param pagingVO
	 * @return
	 */
	public int selectTotalRecord(PagingVO<CointVO> pagingVO);
	/**
	 * 리뷰리스트  조회
	 * @param paging
	 * @return
	 */
	public List<CointVO> selectCointList(PagingVO<CointVO> pagingVO);
	
}
