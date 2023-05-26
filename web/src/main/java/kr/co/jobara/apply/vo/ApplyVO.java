package kr.co.jobara.apply.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyVO {
	private Integer appSn;
	private Integer jobSn;
	private Integer probSn;
	private Integer reSn;
	private String appDate;
	
	//추가vo
	private String pmemId;
	private String jobTitle;
	private String reTitle;
	private String probTitle;
	
}
