package kr.co.jobara.info.service;

import java.util.List;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.info.vo.InfoVO;
import kr.co.jobara.jobboard.vo.JobBoardVO;

public interface InfoService {
	
	/**
	 * 기업 정보 상세조회
	 * @param ememId
	 * @return
	 */
	public InfoVO retrieveInfo(String ememId);
	
	/**
	 * 공고 리스트
	 * @param pagingVO
	 * @return
	 */
	public List<JobBoardVO> retrieveInfoList(PagingVO<JobBoardVO> pagingVO);
	
}
