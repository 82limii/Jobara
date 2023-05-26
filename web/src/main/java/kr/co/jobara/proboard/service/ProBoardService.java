package kr.co.jobara.proboard.service;

import java.util.List;

import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.proboard.vo.ProBoardVO;

/**
 * @author 이상림
 * 최초작성 2022.03.04
 */
public interface ProBoardService {
	/**
	 * 프로젝트 공고 목록 불러오기
	 * @param pagingVO
	 * @return
	 */
	public List<ProBoardVO> retrieveProBoardList(PagingVO<ProBoardVO> pagingVO);
	
	/**
	 * 프로젝트 공고 상세조회
	 * @param probSn
	 * @return
	 */
	public ProBoardVO retrieveProBoard(int probSn);
	
	/**
	 * 기업별 프로젝트 공고 목록 조회
	 * @param pagingVO, ememId는 필수로 포함되어야 한다
	 * @return
	 */
	public List<ProBoardVO> retrieveMyProBoard(PagingVO<ProBoardVO> pagingVO);
	
	/**
	 * 신규 공고 등록
	 * @param proBoard
	 * @return OK, FAIL
	 */
	public ServiceResult createProBoard(ProBoardVO proBoard);
	
	/**
	 * 프로젝트 공고 수정
	 * @param proBoard
	 * @return OK, FAIL, {@link PKNotFoundException}, INVALIDUSER
	 */
	public ServiceResult modifyProBoard(ProBoardVO proBoard);
}
