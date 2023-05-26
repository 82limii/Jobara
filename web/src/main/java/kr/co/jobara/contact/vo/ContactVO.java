package kr.co.jobara.contact.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of= {"contacSn", "ememId"})
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContactVO implements Serializable {
	
	private int rnum;
	
	private Integer contacSn;
	// 기업 ID
	private String ememId;
	// 거래처명
	@NotBlank
	private String contacBuyern;
	// 거래처 로고 -> 사용안함
	private String contacBuyerl;
	// 부서명
	@NotBlank
	private String contacName;
	// 이름
	@NotBlank
	private String contacPers;
	// 직급명
	@NotBlank
	private String contacPosiCd;
	// 이메일
	@NotBlank
	private String contacEmail;
	// 전화번호
	@NotBlank
	private String contacTel;
	// 거래처 주소
	private String contacBuyera;
	// 거래처 전화번호
	private String contacBuyert;
	// 거래처 홈페이지
	private String contacBuyerp;
	
	private Integer proSn;
	
}
