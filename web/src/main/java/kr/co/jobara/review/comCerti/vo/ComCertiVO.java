package kr.co.jobara.review.comCerti.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 작성일 2022.02.24
 * @author 김승현
 * 기업리뷰인증(CR)용 VO
 */

@Data
@EqualsAndHashCode(of="coceSn")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ComCertiVO implements Serializable {
	private Integer coceSn;
	private String pmemId;
	private String ememId;
	private String coceState;
	private String coceDeptCd;
	private String coceEmptype;
	private String coceCareer;
	private String coceArea;
	private String coceDate;
}
