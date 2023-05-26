package kr.co.jobara.jobboard.service;

import java.util.List;

import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.jobboard.vo.JobBoardVO;

/**
 * @author 이상림
 * 최초작성 2022.02.23
 */
public interface JobBoardService {
	/**
	 * 신규 공고 등록
	 * @param jobBoard
	 * @return OK, FAIL
	 */
	public ServiceResult createJobBoard(JobBoardVO jobBoard);
	
	/**
	 * 채용공고 목록 불러오기
	 * @param pagingVO
	 * @return
	 */
	public List<JobBoardVO> retrieveJobBoardList(PagingVO<JobBoardVO> pagingVO);
	
	/**
	 * 채용공고 상세조회
	 * @param jobSn
	 * @return
	 */
	public JobBoardVO retrieveJobBoard(int jobSn);
	
	/**
	 * 기업별 채용공고 목록 조회
	 * @param pagingVO, ememId는 필수로 포함되어야 한다
	 * @return
	 */
	public List<JobBoardVO> retreiveMyJobBoard(PagingVO<JobBoardVO> pagingVO);
	
	/**
	 * 채용공고 수정
	 * @param jobBoard
	 * @return OK, FAIL, {@link PKNotFoundException}, INVALIDUSER
	 */
	public ServiceResult modifyJobBoard(JobBoardVO jobBoard);
	
}
