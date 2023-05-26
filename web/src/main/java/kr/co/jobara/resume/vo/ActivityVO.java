package kr.co.jobara.resume.vo;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="actSn")
public class ActivityVO {
	
	private Integer actSn;
	private Integer reSn;
	@NotBlank
	private String actComb;
	@NotBlank
	private String actNm;
	@NotBlank
	private String actStartd;
	@NotBlank
	private String actEndd;
	@NotBlank
	private String actContents;
	
	// 공통코드
	private String actDiv;

	// 검증
	public boolean validate() {
		if(this.actComb == null) {
			return false;
		}
		if(this.actNm == null) {
			return false;
		}
		if(this.actStartd == null) {
			return false;
		}
		if(this.actEndd == null) {
			return false;
		}
		if(this.actContents == null) {
			return false;
		}
		
		return true; 
	}
}
