package kr.co.jobara.review.cote.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoteVO {
	private Integer coteSn;
	private Integer certiSn;
	private String pmemId;
	private String ememId;
	private String coteHigh;
	private String coteReview;
	private String coteDate;
	private String coteTitle;
	private String commNm;
	
	
	

}
