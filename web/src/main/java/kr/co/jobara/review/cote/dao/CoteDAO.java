package kr.co.jobara.review.cote.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.review.cote.vo.CoteVO;

/**
 * 작성일 2022.02.25
 * @author 김승현
 * 코딩테스트(CR)용 dao
 */

@Mapper
public interface CoteDAO {
	/**
	 * 코딩테스트 리뷰  조회 페이징 기능
	 * @param pagingVO
	 * @return
	 */
	public int selectTotalRecord(PagingVO<CoteVO> pagingVO);
	/**
	 * 코딩테스트 리뷰  조회
	 * @param paging
	 * @return
	 */
	public List<CoteVO> selectCoteList(PagingVO<CoteVO> paging);
	/**
	 * 코딩테스트 리뷰  상세 조회
	 * @param coteSn
	 * @return
	 */
	public CoteVO selectCote(int coteSn);
	/**
	 * 코딩테스트 리뷰  등록
	 * @param cote
	 * @return
	 */
	public int insertCote(CoteVO cote);
	
}
