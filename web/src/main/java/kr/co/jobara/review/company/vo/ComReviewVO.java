package kr.co.jobara.review.company.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 작성일 2022.02.23
 * @author 김승현
 * 기업리뷰(CRUD)용 VO
 */

@Data
@EqualsAndHashCode(of="reviewSn")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ComReviewVO implements Serializable {
	
	private int rnum;
	
//	@NotNull(groups=UpdateGroup.class)
//	@Min(value=1, groups=UpdateGroup.class)
	private Integer reviewSn;
	private Integer coceSn;
	private String pmemId;
	private String ememId;
	private String reviewAdv;
	private String reviewDisadv;
	private String reviewComment;
	private String ratingSn;
	private String reviewDate;
	
	private String coceState;
	private String coceDeptCd;
	
	private String rat;
	private String stae;
	private String dept;
}
