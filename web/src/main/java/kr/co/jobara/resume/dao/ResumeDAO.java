package kr.co.jobara.resume.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.member.vo.PmemberVO;
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
 * 이력서(CRU)용 dao
 */
@Mapper
public interface ResumeDAO {
	
	/**
	 * 이력서 등록
	 * @param resume
	 * @return
	 */
	public int insertResume(ResumeVO resume);
	// 이력서 내 항목들 등록
	public int insertEducation(EducationVO education);
	public int insertCareer(CareerVO career);
	public int insertActivity(ActivityVO activity);
	public int insertSchool(SchoolVO school);
	public int insertConditions(ConditionsVO conditions);
	public int insertLanguage(LanguageVO language);
	public int insertLicense(LicenseVO license);
	
	/**
	 * 조건에 맞는 자소서 갯수
	 * @param reSn
	 * @return
	 */
	public int selectTotalRecord(PagingVO<ResumeVO> pagingVO);
	
	/**
	 * 이력서 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public List<ResumeVO> selectResumeList(PagingVO<ResumeVO> pagingVO);
	
	/**
	 * 이력서 상세조회
	 * @param successInt
	 * @return
	 */
	public ResumeVO selectResume(int reSn);
	// 이력서 항목내용 불러오기
	public PmemberVO selectMember(int reSn);
	public List<EducationVO> selectEducationList(int reSn);
	public List<CareerVO> selectCareerList(int reSn);
	public List<ActivityVO> selectActivityList(int reSn);
	public List<SchoolVO> selectSchoolList(int reSn);
	public ConditionsVO selectCondi(int reSn);
	public List<LanguageVO> selectLanguageList(int reSn);
	public List<LicenseVO> selectLicenseList(int reSn);
	
	/**
	 * 이력서 삭제
	 * @param successInt
	 * @return
	 */
	public int deleteResume(int reSn);
	
}
