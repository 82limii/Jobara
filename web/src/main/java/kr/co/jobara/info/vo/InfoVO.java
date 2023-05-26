package kr.co.jobara.info.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="ememId")
@ToString
public class InfoVO implements Serializable {
	
	private String ememId;
	@NotBlank
	private String comHeadcnt;
	@NotBlank
	private String comFounddate;
	@NotBlank
	private String comCapital;
	@NotBlank
	private String comTake;
	@NotBlank
	private String comBussiness;
	@NotBlank
	private String comAdd;
	@NotBlank
	private String comHome;
	private String comWelfare;
	
	// 기업정보
	private String ememPic;
	private String ememNm;
}
