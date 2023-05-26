package kr.co.jobara.resume.service;

import java.util.List;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.resume.vo.ResumeVO;

/**
 * 작성일 2022.03.02
 * @author 김승현
 * 합격자소서(CRU)용 Service
 */
public interface ResumeService {
	
	/**
	 * 합격자소서 입력
	 * @param resume
	 * @return OK, FAIL
	 */
	public ServiceResult createResume(ResumeVO resume);
	
	/**
	 * 합격자소서 상세조회
	 * @param int reSn
	 * @return
	 */
	public ResumeVO retrieveResume(int reSn);
	
	/**
	 * 합격자소서 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public List<ResumeVO> retrieveResumeList(PagingVO<ResumeVO> pagingVO);
	
	/**
	 * 합격자소서 삭제
	 * @param resume
	 * @return
	 */
	public ServiceResult removeResume(int reSn);
	
}
