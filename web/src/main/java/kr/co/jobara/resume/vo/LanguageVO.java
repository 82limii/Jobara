package kr.co.jobara.resume.vo;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="langSn")
public class LanguageVO {

	private Integer langSn;
	private Integer reSn;
	@NotBlank
	private String langDiviCd;
	@NotBlank
	private String langNm;
	private String langTest;
	private String langScore;
	private String langDate;
	private String langConCd;
	
	// 공통코드
	private String divNm;
	private String langCon;
	
	// 검증
	public boolean validate() {
		if(this.langDiviCd == null) {
			return false;
		}
		if(this.langNm == null) {
			return false;
		}
		
		return true; 
	}
}
