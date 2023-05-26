package kr.co.jobara.member.vo;


import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import kr.co.jobara.validate.hints.InsertGroup;
import kr.co.jobara.validate.hints.LoginGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 장인슬
 * @author PC-17
 *
 */
@Data
@EqualsAndHashCode(of="pmemId")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PmemberVO extends MemberVO{
	//장인슬
	
	private int rnum;
//	@NotBlank(groups= {LoginGroup.class, Default.class})
	private String pmemId;
//	@NotBlank(groups= {LoginGroup.class, Default.class})
	@Size(min=4, max=15, groups= {LoginGroup.class, Default.class})
	private String pmemPass;
//	@NotBlank
	private String pmemNm;
	private String pmemBir;
//	@NotBlank
	private String pmemTel;
//	@NotBlank
	private String pmemZip;
//	@NotBlank
	private String pmemBadd;
//	@NotBlank
	private String pmemDadd;
	@Email
	private String pmemEmail;
	private String empStateCd;
	private String pmemSkill;
	private String pmemExpdate;
//	@NotBlank(groups=InsertGroup.class)
	private String pmemSex;
	private String pmemPic;
	
	//empInfo
	private int empSn;
	private String ememId;
	private String empStartd;
	private String empEndd;
	private String empPosiCd;
	private String empDeptCd;
	private String empSalary;
	
	
	private String pmemRole;
	
	
	// 공통코드 이름
	private String pmemSexNm;
	
	//입력된파일을 담을 변수생성
	private MultipartFile pmemImg;
	public void setPmemImg(MultipartFile pmemImg) {
		this.pmemImg = pmemImg;
		if(pmemImg != null && !pmemImg.isEmpty()) {
			this.pmemPic = UUID.randomUUID().toString();
			System.out.println("vo :"+this.pmemPic);
		}
	
	}
	
	// abstract
	@Override
	public String getUserType() {
		return "Pmember";
	}

	@Override
	public String getId() {
		return this.pmemId;
	}
}
