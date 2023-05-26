package kr.co.jobara.applicant.service;

import java.util.List;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.incumbent.vo.IncumbentVO;
import kr.co.jobara.jobboard.vo.JobBoardVO;
import kr.co.jobara.proboard.vo.ProBoardVO;

/**
 * @author 전영준
 * 지원자 관리 Service
 */
public interface ApplicantService {
	
	public List<JobBoardVO> retrievejobApplicantList(PagingVO<JobBoardVO> pagingVO);
	
	public List<JobBoardVO> retrievejobApplicantListEnd(PagingVO<JobBoardVO> pagingVO);
	
	public List<ProBoardVO> retrieveproApplicantList(PagingVO<ProBoardVO> pagingVO);
	
	public List<ProBoardVO> retrieveproApplicantListEnd(PagingVO<ProBoardVO> pagingVO);
	
	public List<JobBoardVO> retrievejobApplicantManList(PagingVO<JobBoardVO> pagingVO);
	
	public JobBoardVO retrieveJobApplicant(int jobSn);
	
	public List<ProBoardVO> retrieveproApplicantManList(PagingVO<ProBoardVO> pagingVO);
	
	public ProBoardVO retrieveProApplicant(int probSn);
	
	public ServiceResult createIncumbent(IncumbentVO incumbent);
	
	public ServiceResult removeApplicantMan(int appSn);
	
	public JobBoardVO retrieveJobApplicantMan(int reSn);
	
	public ProBoardVO retrieveProApplicantMan(int reSn);
}
