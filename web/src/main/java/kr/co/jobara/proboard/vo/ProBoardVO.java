package kr.co.jobara.proboard.vo;

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
 *
 */
@Data
@EqualsAndHashCode(of="probSn")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProBoardVO {
	private int rnum;
	private String proOrder;
	
	private Integer probSn;
	private String ememId;
	@NotBlank
	private String probTitle;
	@NotBlank
	private String probEduCd;
	@NotBlank
	private String probCarCd;
	private String probPref;
	@NotBlank
	private String probPay;
	@NotBlank
	private String probLocCd;
	@NotBlank
	private String probStartd;
	@NotBlank
	private String probEndd;
	private String probPosiCd;
	@NotBlank
	private String probDeptCd;
	@NotBlank
	private String probDetail;
	@NotBlank
	private String probRstartd;
	@NotBlank
	private String probRendd;
	private String probPage;
	private String attSn;
	@NotBlank
	private String probWay;
	@NotBlank(groups=InsertGroup.class)
	private String probSkill;
	private String probDate;
	private String probEdit;
	
	// 테이블 join
	private String ememNm;
	private String probEdu;
	private String probCar;
	private String probEmp;
	private String probLoc;
	private String probCity;
	private String probPosi;
	private String probDept;
	
	// 테이블 join(지원자 리스트)
	private String ediSn;
	private Integer jobYear;
	private Integer jobMonth;
	private String reTitle;
	private Integer appSn;
	
	// 메뉴 구분
	private String pageDiv;
	// 기업정보
	private String ememPic;
	private String comBussiness;
	private String comHeadcnt;
	
	// 첨부파일
	private MultipartFile[] probFiles;
	
	private AttatchVO attatch;

	// pms
	private String pmemId;
	private String pmemNm;
	private String pmemEmail;
	private String pmemTel;
	private String reSn;
	private String proSn;
	
	
}
