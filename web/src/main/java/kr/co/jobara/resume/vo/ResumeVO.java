package kr.co.jobara.resume.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.portfolio.vo.PortfolioVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="reSn")
public class ResumeVO {
	private int rnum;

	private Integer reSn;
	private String pmemId;
	private String reTitle;
	private String reStack;
	private String reInt;
	private String reDate;
	private String photoUrl;
	private String reDel;
	private Integer portSn;
	
	// 이력서 항목들
	private PmemberVO member;
	//학력추가
	private List<EducationVO> educationList;
	private List<CareerVO> careerList;
	private List<ActivityVO> activityList;
	private List<SchoolVO> schoolList;
	private List<LanguageVO> languageList;
	private List<LicenseVO> licenseList;
	private PortfolioVO portfolio;
	private ConditionsVO condi;
	
	
}
