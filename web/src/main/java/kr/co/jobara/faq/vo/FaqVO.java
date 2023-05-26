package kr.co.jobara.faq.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="faqSn")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FaqVO implements Serializable{
	
	private int rnum;
	
	private Integer faqSn;
	private String adminId;
	@NotBlank
	private String faqTitle;
	@NotBlank
	private String faqContents;
	@NotBlank
	private String faqReq;
	private String combSn;
	private String faqDate;
	private String faqEdit;
	
	// 공통코드 조인
	private String combSnNm;
}
