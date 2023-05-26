package kr.co.jobara.resume.vo;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="licSn")
public class LicenseVO {
	
	private Integer licSn;
	private Integer reSn;
	@NotBlank
	private String licNm;
	@NotBlank
	private String licInt;
	@NotBlank
	private String licDate;

	// 검증
	public boolean validate() {
		if(this.licNm == null) {
			return false;
		}
		if(this.licInt == null) {
			return false;
		}
		if(this.licDate == null) {
			return false;
		}
		
		return true; 
	}
}
