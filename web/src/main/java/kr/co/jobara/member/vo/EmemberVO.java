package kr.co.jobara.member.vo;

import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import kr.co.jobara.info.vo.InfoVO;
import kr.co.jobara.validate.hints.LoginGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 장인슬
 * @author PC-17
 */
@Data
@EqualsAndHashCode(of="ememId")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class EmemberVO extends MemberVO{

	private int rnum;
	private int res;
	@NotBlank(groups= {LoginGroup.class, Default.class})	
	private String ememId;
	@NotBlank(groups= {LoginGroup.class, Default.class})
	@Size(min=4, max=16, groups= {LoginGroup.class, Default.class})
	private String ememPass;
	@NotBlank
	@Size(min=10, max=10)
	private String ememNum;
	@NotBlank
	private String ememNm;
	@NotBlank
	private String ememCeo;
	@NotBlank
	@Size(min=5, max=6)
	private String ememZip;
	@NotBlank
	private String ememBadd;
	@NotBlank
	private String ememDadd;
	@NotBlank
	@Size(min=9, max=11)
	private String ememTel;
	private String ememFax;
	@NotBlank
	@Email
	private String ememEmail;
	private String ememMan;
	@Size(min=10, max=11)
	private String ememMantel;
	private String ememPic;
	private String ememAi;
	
	// 기업검색 결과 정보 보여주기
	private InfoVO info;
	
	
	private String ememRole;
	
	//입력된파일을 담을 변수생성
	private MultipartFile ememImg;
	public void setEmemImg(MultipartFile ememImg) {
		this.ememImg = ememImg;
		//데이터가 존재하면 고유아이디로 변수에 삽입
		if(ememImg != null && !ememImg.isEmpty()) {
			this.ememPic = UUID.randomUUID().toString();
			log.info(this.ememPic);
		}		
	}

	@Override
	public String getUserType() {
		return "Emember";
	}

	@Override
	public String getId() {
		return this.ememId;
	}
	
	
}
