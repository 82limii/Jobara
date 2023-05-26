package kr.co.jobara.review.certi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.review.certi.vo.CertiVO;

/**
 * 작성일 2022.03.04
 * @author 김승현
 * 리뷰인증(CR)용 dao
 */
@Mapper
public interface CertiDAO {
	
	/**
	 * 리뷰인증 등록
	 * @param certi
	 * @return
	 */
	public int insertCerti(CertiVO certi);
	
	/**
	 * 조건에 맞는 리뷰인증 갯수
	 * @param certi
	 * @return
	 */
	public int selectTotalRecord(PagingVO<CertiVO> pagingVO);
	
	/**
	 * 리뷰인증 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public List<CertiVO> selectCertiList(PagingVO<CertiVO> pagingVO);
	
	/**
	 * 리뷰인증 상세조회
	 * @param certiSn
	 * @return
	 */
	public CertiVO selectCerti(int certiSn);
	
	
}
