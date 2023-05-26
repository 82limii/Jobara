package kr.co.jobara.successint.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.successint.vo.SuccessIntVO;

/**
 * 작성일 2022.03.02
 * @author 김승현
 * 합격자소서(CRU)용 dao
 */
@Mapper
public interface SuccessIntDAO {
	
	/**
	 * 합격자소서 등록
	 * @param successInt
	 * @return
	 */
	public int insertSuccessInt(SuccessIntVO successInt);
	
	/**
	 * 조건에 맞는 자소서 갯수
	 * @param successInt
	 * @return
	 */
	public int selectTotalRecord(PagingVO<SuccessIntVO> pagingVO);
	
	/**
	 * 합격자소서 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public List<SuccessIntVO> selectSuccessIntList(PagingVO<SuccessIntVO> pagingVO);
	
	/**
	 * 합격자소서 상세조회
	 * @param successInt
	 * @return
	 */
	public SuccessIntVO selectSuccessInt(int sinSn);
	
	/**
	 * 합격자소서 수정
	 * @param successInt
	 * @return
	 */
	public int deleteSuccessInt(int sinSn);
	
}
