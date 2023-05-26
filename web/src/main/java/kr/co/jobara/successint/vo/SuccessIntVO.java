package kr.co.jobara.successint.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SuccessIntVO implements Serializable {
	
	private int rnum;
	private Integer sinSn;
	private Integer certiSn;
	private String pmemId;
	private String ememId;
	private String sinTitle;
	private String sinConten;
	private String sinDate;
	private String sinSdate;
	private String sinDdate;
}
