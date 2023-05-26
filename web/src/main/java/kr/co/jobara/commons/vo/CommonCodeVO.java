package kr.co.jobara.commons.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 이상림
 * 최초작성 2022.02.22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonCodeVO {
	private String combCd;
	private String combNm;
	private String commCd;
	private String commNm;
	private String comsCd;
	private String comsNm;
}
