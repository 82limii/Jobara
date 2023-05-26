package kr.co.jobara.resume.vo;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="carSn")
public class CareerVO {

	private Integer carSn;
	private Integer reSn;
	@NotBlank
	private String carEnter;
	@NotBlank
	private String carDepartment;
	@NotBlank
	private String carStartd;
	@NotBlank
	private String carDeptCd;
	private String carPosiCd;
	private String carPay;
	@NotBlank
	private String carTask;
	private String carDescription;
	@NotBlank
	private String carEndd;
	
	// 공통코드
	private String carDept;
	private String carPosi;
	
	public boolean validate() {
		if(this.carEnter == null) {
			return false;
		}
		if(this.carDepartment == null) {
			return false;
		}
		if(this.carStartd == null) {
			return false;
		}
		if(this.carDeptCd == null) {
			return false;
		}
		if(this.carTask == null) {
			return false;
		}
		if(this.carEndd == null) {
			return false;
		}
		
		return true; 
	}
}
