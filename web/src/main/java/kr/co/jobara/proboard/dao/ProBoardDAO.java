package kr.co.jobara.proboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.proboard.vo.ProBoardVO;

/**
 * @author 이상림
 * 최초작성 2022.03.04
 * 프로젝트 공고 관리 persistence layer
 */
@Mapper
public interface ProBoardDAO {
	/**
	 * 목록 조회
	 * @param pagingVO
	 * @return
	 */
	public List<ProBoardVO> selectProBoardList(PagingVO<ProBoardVO> pagingVO);
	public int selectTotalRecord(PagingVO<ProBoardVO> pagingVO);
	
	/**
	 * 상세조회
	 * @param probSn : 프로젝트 공고 글번호
	 * @return
	 */
	public ProBoardVO selectProBoard(int probSn);
	
	/**
	 * 기업별 공고 목록 조회
	 * @param pagingVO, ememId가 null이면 안됨
	 * @return
	 */
	public List<ProBoardVO> selectMyProBoard(PagingVO<ProBoardVO> pagingVO);
	public int selectMyTotalRecord(PagingVO<ProBoardVO> pagingVO);
	
	/**
	 * 프로젝트 공고 추가
	 * @param proBoard
	 * @return > 0 : OK
	 */
	public int insertProBoard(ProBoardVO proBoard);
	
	/**
	 * 공고 수정
	 * @param proBoard
	 * @return
	 */
	public int updateProBoard(ProBoardVO proBoard);
}
