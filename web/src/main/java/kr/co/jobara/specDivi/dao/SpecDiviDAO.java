package kr.co.jobara.specDivi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.specDivi.vo.SpecDiviVO;

/**
 * 작성일 2022.03.14
 * @author 김승현
 * 합격스펙(CR)용 dao
 */
@Mapper
public interface SpecDiviDAO {
	
	/**
	 * 합격스펙 등록
	 * @param specDivi
	 * @return
	 */
	public int insertSpecDivi(SpecDiviVO specDivi);
	
	/**
	 * 조건에 맞는 합격스펙 갯수
	 * @param specDivi
	 * @return
	 */
	public int selectTotalRecord(PagingVO<SpecDiviVO> pagingVO);
	
	/**
	 * 합격스펙 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public List<SpecDiviVO> selectSpecDiviList(PagingVO<SpecDiviVO> pagingVO);
	
	/**
	 * 합격스펙 상세조회
	 * @param specDivi
	 * @return
	 */
	public SpecDiviVO selectSpecDivi(int sdiSn);
	
}
