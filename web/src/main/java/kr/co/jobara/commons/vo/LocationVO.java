package kr.co.jobara.commons.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 이상림
 * 최초작성 2022.02.27
 * 주소 코드 VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationVO {
	private String locCd;
	private String locCityNm;
	private String locNm;
}
