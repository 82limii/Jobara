package kr.co.jobara.review.inter.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterVO {
	
	private String interTitle;
	private Integer interSn;
	private Integer certiSn;
	private String pmemId;
	private String ememId;
	private String interComb;
	private String interLater;
	private String interQna;
	private String interDate;
	private String interAns;
	
	private String commNm;

}
