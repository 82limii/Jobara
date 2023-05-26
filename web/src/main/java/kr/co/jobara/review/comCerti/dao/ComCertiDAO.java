package kr.co.jobara.review.comCerti.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.review.comCerti.vo.ComCertiVO;

/**
 * 작성일 2022.02.24
 * @author 김승현
 * 기업리뷰인증(CR)용 dao
 */

@Mapper
public interface ComCertiDAO {
	/**
	 * 기업 리뷰 인증 조회 페이징 기능
	 * @param pagingVO
	 * @return
	 */
	public int selectTotalRecord(PagingVO<ComCertiVO> pagingVO);
	/**
	 * 기업 리뷰 인증 조회
	 * @param paging
	 * @return
	 */
	public List<ComCertiVO> selectComCertiList(PagingVO<ComCertiVO> paging);
	/**
	 * 기업 리뷰 인증 상세 조회
	 * @param coceSn
	 * @return
	 */
	public ComCertiVO selectComCerti(int coceSn);
	/**
	 * 기업 리뷰 인증 등록
	 * @param comCerti
	 * @return
	 */
	public int insertComCerti(ComCertiVO comCerti);
	
}
