package kr.co.jobara.resume.vo;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="condiSn")
public class ConditionsVO {

	private Integer condiSn;
	private Integer reSn;
	@NotBlank
	private String condiShape;
	@NotBlank
	private String condiPlace;
	@NotBlank
	private String condiDuty;
	
	// 공통코드
	private String shapeNm;
	private String dutyNm;
	private String placeNm;
}
