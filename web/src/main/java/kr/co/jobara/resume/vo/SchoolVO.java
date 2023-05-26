package kr.co.jobara.resume.vo;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="schSn")
public class SchoolVO {

	private Integer schSn;
	private Integer reSn;
	@NotBlank
	private String schNm;
	@NotBlank
	private String schInt;
	@NotBlank
	private String schStartd;
	@NotBlank
	private String schEndd;
	@NotBlank
	private String schContents;
	
	// 검증
	public boolean validate() {
		if(this.schNm == null) {
			return false;
		}
		if(this.schInt == null) {
			return false;
		}
		if(this.schStartd == null) {
			return false;
		}
		if(this.schEndd == null) {
			return false;
		}
		if(this.schContents == null) {
			return false;
		}
		
		return true; 
	}
}
