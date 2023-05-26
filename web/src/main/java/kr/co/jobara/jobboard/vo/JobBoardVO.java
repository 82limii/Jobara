package kr.co.jobara.jobboard.vo;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import kr.co.jobara.commons.vo.AttatchVO;
import kr.co.jobara.validate.hints.InsertGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author 이상림
 * 최초작성 2022.02.22
 */
@Data
@EqualsAndHashCode(of="jobSn")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobBoardVO {
	private int rnum;
	private String jobOrder;
	
	private Integer jobSn;
	private String ememId;
	@NotBlank
	private String jobTitle;
	@NotBlank
	private String jobEduCd;
	@NotBlank
	private String jobCarCd;
	private String jobPref;
	@NotBlank
	private String jobEmpCd;
	@NotBlank
	private String jobPay;
	@NotBlank
	private String jobLocCd;
	private String jobTime;
	private String jobPosiCd;
	@NotBlank
	private String jobDeptCd;
	@NotBlank
	private String jobDetail;
	@NotBlank
	private String jobStartd;
	@NotBlank
	private String jobEndd;
	private String jobPage;
	private String attSn;
	@NotBlank
	private String jobWay;
	@NotBlank(groups=InsertGroup.class)
	private String jobSkill;
	private String jobDate;
	private String jobEdit;
	
	// 테이블 join
	private String ememNm;
	private String jobEdu;
	private String jobCar;
	private String jobEmp;
	private String jobLoc;
	private String jobCity;
	private String jobPosi;
	private String jobDept;
	
	// 테이블 join(지원자 리스트)
	private String pmemNm;
	private String ediSn;
	private Integer jobYear;
	private Integer jobMonth;
	private String reTitle;
	private Integer reSn;
	private String pmemId;
	private Integer appSn;
	
	// 메뉴 구분
	private String pageDiv;
	private String isMine;
	
	// 기업정보
	private String ememPic;
	private String comBussiness;
	private String comHeadcnt;
	
	// 첨부파일
	private MultipartFile[] jobFiles;

	private List<AttatchVO> attatchList;
	
	
	
}
