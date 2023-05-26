package kr.co.jobara.incumbent.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.incumbent.vo.IncumbentVO;

@Mapper
public interface IncumbentDAO {

	/**
	 * 재직자 상세조회
	 * @param empSn
	 * @return
	 */
	public IncumbentVO selectIncumbent(int empSn);
	
	/**
	 * 재직자 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public List<IncumbentVO> selectIncumbentList(PagingVO<IncumbentVO> pagingVO);
	
	public int selectTotalRecord(PagingVO<IncumbentVO> pagingVO);
	
	/**
	 * 재직자 등록
	 * @param incumbent
	 * @return
	 */
	public int insertIncumbent(IncumbentVO incumbent);
	
	/**
	 * 재직자 정보 수정
	 * @param incumbent
	 * @return
	 */
	public int updateIncumbent(IncumbentVO incumbent);
	
	/**
	 * 재직자 삭제
	 * @param empSn
	 * @return
	 */
	public int deleteIncumbent(int empSn);
}
