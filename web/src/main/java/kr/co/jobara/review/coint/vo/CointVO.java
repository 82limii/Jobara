package kr.co.jobara.review.coint.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CointVO implements Serializable {
	private Integer certiSn;
	private String pmemId;
	private String ememId;
	
	private int rnum;
	private String cote;
	
	private String coteTitle;
	private Integer coteSn;
	private String coteHigh;
	private String coteReview;
	private String coteDate;
	
	private String interTitle;
	private Integer interSn;
	private Integer interComb;
	private String interLater;
	private String interQna;
	private String interDate;
	private String interAns;
}
