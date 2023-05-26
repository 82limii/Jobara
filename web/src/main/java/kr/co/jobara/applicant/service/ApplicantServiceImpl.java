package kr.co.jobara.applicant.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.applicant.dao.ApplicantDAO;
import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.incumbent.vo.IncumbentVO;
import kr.co.jobara.jobboard.vo.JobBoardVO;
import kr.co.jobara.proboard.vo.ProBoardVO;

/**
 * @author 전영준
 *	지원자 관리 ServiceImpl
 */
@Service
public class ApplicantServiceImpl implements ApplicantService {

	@Inject
	private ApplicantDAO applicantDao;
	
	// 채용 지원자 리스트
	@Override
	public List<JobBoardVO> retrievejobApplicantList(PagingVO<JobBoardVO> pagingVO) {
		List<JobBoardVO> jobApplicantList = applicantDao.selectJobApplicantList(pagingVO);
		if(pagingVO!=null) {
			int totalRecord = applicantDao.selectJobTotalRecord(pagingVO);
			pagingVO.setTotalRecord(totalRecord);
			pagingVO.setDataList(jobApplicantList);
		}
		return jobApplicantList;
	}
	
	// 채용 지원자 리스트
	@Override
	public List<JobBoardVO> retrievejobApplicantListEnd(PagingVO<JobBoardVO> pagingVO) {
		List<JobBoardVO> jobApplicantList = applicantDao.selectJobApplicantListEnd(pagingVO);
		if(pagingVO!=null) {
			int totalRecord = applicantDao.selectJobTotalRecord(pagingVO);
			pagingVO.setTotalRecord(totalRecord);
			pagingVO.setDataList(jobApplicantList);
		}
		return jobApplicantList;
	}
	
	// 프로젝트 지원자 리스트
	@Override
	public List<ProBoardVO> retrieveproApplicantList(PagingVO<ProBoardVO> pagingVO) {
		List<ProBoardVO> proApplicantList = applicantDao.selectProApplicantList(pagingVO);
		if(pagingVO!=null) {
			int totalRecord = applicantDao.selectProTotalRecord(pagingVO);
			pagingVO.setTotalRecord(totalRecord);
			pagingVO.setDataList(proApplicantList);
		}
		return proApplicantList;
	}

	@Override
	public List<ProBoardVO> retrieveproApplicantListEnd(PagingVO<ProBoardVO> pagingVO) {
		List<ProBoardVO> proApplicantList = applicantDao.selectProApplicantListEnd(pagingVO);
		if(pagingVO!=null) {
			int totalRecord = applicantDao.selectProTotalRecord(pagingVO);
			pagingVO.setTotalRecord(totalRecord);
			pagingVO.setDataList(proApplicantList);
		}
		return proApplicantList;
	}

	@Override
	public List<JobBoardVO> retrievejobApplicantManList(PagingVO<JobBoardVO> pagingVO) {
		List<JobBoardVO> jobApplicantManList = applicantDao.selectJobApplicantManList(pagingVO);
		if(pagingVO!=null) {
			int totalRecord = applicantDao.selectJobManTotalRecord(pagingVO);
			pagingVO.setTotalRecord(totalRecord);
			pagingVO.setDataList(jobApplicantManList);
		}
		return jobApplicantManList;
	}

	@Override
	public JobBoardVO retrieveJobApplicant(int jobSn) {
		JobBoardVO jobApplicant = applicantDao.selectJobApplicant(jobSn);
		if(jobApplicant == null)
			throw new PKNotFoundException(jobSn+"에 해당하는 프로젝트가 없음");
		return jobApplicant;
	}
	
	@Override
	public List<ProBoardVO> retrieveproApplicantManList(PagingVO<ProBoardVO> pagingVO) {
		List<ProBoardVO> proApplicantManList = applicantDao.selectProApplicantManList(pagingVO);
		if(pagingVO!=null) {
			int totalRecord = applicantDao.selectProManTotalRecord(pagingVO);
			pagingVO.setTotalRecord(totalRecord);
			pagingVO.setDataList(proApplicantManList);
		}
		return proApplicantManList;
	}
	
	@Override
	public ProBoardVO retrieveProApplicant(int probSn) {
		ProBoardVO proApplicant = applicantDao.selectProApplicant(probSn);
		if(proApplicant == null)
			throw new PKNotFoundException(probSn+"에 해당하는 프로젝트가 없음");
		return proApplicant;
	}

	@Override
	public ServiceResult createIncumbent(IncumbentVO incumbent) {
		int rowcnt = applicantDao.insertIncumbent(incumbent);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public ServiceResult removeApplicantMan(int appSn) {
		int rowcnt = applicantDao.deleteApplicantMan(appSn);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public JobBoardVO retrieveJobApplicantMan(int reSn) {
		JobBoardVO jobApplicantMan = applicantDao.selectJobApplicantMan(reSn);
		if(jobApplicantMan == null)
			throw new PKNotFoundException(reSn+"에 해당하는 지원자가 없음.");
		return jobApplicantMan;
	}
	
	public ProBoardVO retrieveProApplicantMan(int reSn) {
		ProBoardVO proApplicantMan = applicantDao.selectProApplicantMan(reSn);
		if(proApplicantMan == null)
			throw new PKNotFoundException(reSn+"에 해당하는 지원자가 없음.");
		return proApplicantMan;
	}

}
