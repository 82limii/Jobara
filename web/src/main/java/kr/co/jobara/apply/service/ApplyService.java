package kr.co.jobara.apply.service;

import java.util.List;

import kr.co.jobara.apply.vo.ApplyVO;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;

/**
 * @author 이상림
 * 최초작성일 2022.03.21
 * 지원 로직 service
 */
public interface ApplyService {
	public ServiceResult applyBoard(ApplyVO apply);

	/**
	 * 채용공고 목록 조회
	 * @param pagingVO TODO
	 * @return 조건에 맞는 회원이 없으면, size==0
	 */
	public List<ApplyVO> retrieveJobList(PagingVO<ApplyVO> pagingVO);
	
	/**
	 * 채용공고 목록 조회
	 * @param pagingVO TODO
	 * @return 조건에 맞는 회원이 없으면, size==0
	 */
	public List<ApplyVO> retrieveProList(PagingVO<ApplyVO> pagingVO);
}
