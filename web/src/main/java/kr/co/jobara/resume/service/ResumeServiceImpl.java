package kr.co.jobara.resume.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.resume.dao.ResumeDAO;
import kr.co.jobara.resume.vo.ActivityVO;
import kr.co.jobara.resume.vo.CareerVO;
import kr.co.jobara.resume.vo.ConditionsVO;
import kr.co.jobara.resume.vo.EducationVO;
import kr.co.jobara.resume.vo.LanguageVO;
import kr.co.jobara.resume.vo.LicenseVO;
import kr.co.jobara.resume.vo.ResumeVO;
import kr.co.jobara.resume.vo.SchoolVO;

/**
 * 작성일 2022.03.02
 * @author 김승현
 * 합격자소서(CRU)용 ServiceImpl
 */
@Service
public class ResumeServiceImpl implements ResumeService {
	
	@Inject
	private ResumeDAO resumeDAO;


	@Transactional
	@Override
	public ServiceResult createResume(ResumeVO resume) {
		ServiceResult result = null;
		int rowcnt = resumeDAO.insertResume(resume);
		if(rowcnt == 0) {
			return ServiceResult.FAIL;
		}
		
		Integer reSn = resume.getReSn();
		// 각 항목에 대한 처리 - 학력정보
		List<EducationVO> educationList = resume.getEducationList();
		if(educationList != null) {
			for (EducationVO educationVO : educationList) {
				educationVO.setReSn(reSn);
				int cnt = resumeDAO.insertEducation(educationVO);
				if(cnt == 0) {
					return ServiceResult.FAIL;
				}
			}
		}
		// 각 항목에 대한 처리 - 경력정보
		List<CareerVO> careerList = resume.getCareerList();
		if(careerList != null) {
			for (CareerVO careerVO : careerList) {
				careerVO.setReSn(reSn);;
				int cnt = resumeDAO.insertCareer(careerVO);
				if(cnt == 0) {
					return ServiceResult.FAIL;
				}
			}
		}
		// 각 항목에 대한 처리 - 활동정보
		List<ActivityVO> activityList = resume.getActivityList();
		if(activityList != null) {
			for (ActivityVO activityVO : activityList) {
				activityVO.setReSn(reSn);
				int cnt = resumeDAO.insertActivity(activityVO);
				if(cnt == 0) {
					return ServiceResult.FAIL;
				}
			}
		}
		// 각 항목에 대한 처리 - 교육정보
		List<SchoolVO> schoolList = resume.getSchoolList();
		if(schoolList != null) {
			for (SchoolVO schoolVO : schoolList) {
				schoolVO.setReSn(reSn);
				int cnt = resumeDAO.insertSchool(schoolVO);
				if(cnt == 0) {
					return ServiceResult.FAIL;
				}
			}
		}
		// 각 항목에 대한 처리 - 희망근무조건 정보
		ConditionsVO condi = resume.getCondi();
		condi.setReSn(reSn);
		if(condi != null) {
			int cnt = resumeDAO.insertConditions(condi);
			if(cnt == 0) {
				return ServiceResult.FAIL;
			}
		}
		// 각 항목에 대한 처리 - 어학정보
		List<LanguageVO> langList = resume.getLanguageList();
		if(langList != null) {
			for (LanguageVO languageVO : langList) {
				languageVO.setReSn(reSn);
				int cnt = resumeDAO.insertLanguage(languageVO);
				if(cnt == 0) {
					return ServiceResult.FAIL;
				}
			}
		}
		// 각 항목에 대한 처리 - 자격증 정보
		List<LicenseVO> licenseList = resume.getLicenseList();
		if(licenseList != null) {
			for (LicenseVO licenseVO : licenseList) {
				licenseVO.setReSn(reSn);
				int cnt = resumeDAO.insertLicense(licenseVO);
				if(cnt == 0) {
					return ServiceResult.FAIL;
				}
			}
		}
		
		result = ServiceResult.OK;
		return result;
		
	}

	@Override
	public ResumeVO retrieveResume(int reSn) {
		ResumeVO resume = resumeDAO.selectResume(reSn);
		if(resume == null)
			throw new PKNotFoundException(reSn+"에 해당하는 이력서가 없음.");
		
		PmemberVO member = resumeDAO.selectMember(reSn);
		List<EducationVO> educationList = resumeDAO.selectEducationList(reSn);
		List<CareerVO> careerList = resumeDAO.selectCareerList(reSn);
		List<ActivityVO> activityList = resumeDAO.selectActivityList(reSn);
		List<SchoolVO> schoolList = resumeDAO.selectSchoolList(reSn);
		ConditionsVO condi = resumeDAO.selectCondi(reSn);
		List<LanguageVO> languageList = resumeDAO.selectLanguageList(reSn);
		List<LicenseVO> licenseList = resumeDAO.selectLicenseList(reSn);
		
		resume.setMember(member);
		resume.setEducationList(educationList);
		resume.setCareerList(careerList);
		resume.setActivityList(activityList);
		resume.setSchoolList(schoolList);
		resume.setCondi(condi);
		resume.setLanguageList(languageList);
		resume.setLicenseList(licenseList);

		return resume;
	}

	@Override
	public List<ResumeVO> retrieveResumeList(PagingVO<ResumeVO> pagingVO) {
		int totalRecord = resumeDAO.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<ResumeVO> resume = resumeDAO.selectResumeList(pagingVO);
		pagingVO.setDataList(resume);
		return resume;
	}

	@Override
	public ServiceResult removeResume(int reSn) {
		int rowcnt = resumeDAO.deleteResume(reSn);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	

}
