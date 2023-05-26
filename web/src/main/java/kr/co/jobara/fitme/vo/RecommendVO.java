package kr.co.jobara.fitme.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 이상림
 * 최초작성일 2022.03.24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendVO {
	private String pmemId;
	private String ememId1;
	private String ememId2;
	private String ememId3;
	private String ememId4;
	private String ememId5;
	private String ememId6;
	private String ememId7;
	private String ememId8;
	private String ememId9;
	private String ememId10;
	private String rate1;
	private String rate2;
	private String rate3;
	private String rate4;
	private String rate5;
	private String rate6;
	private String rate7;
	private String rate8;
	private String rate9;
	private String rate10;
	private String surDate;
	
	// 기업명
	private String ememNm1;
	private String ememNm2;
	private String ememNm3;
	private String ememNm4;
	private String ememNm5;
	private String ememNm6;
	private String ememNm7;
	private String ememNm8;
	private String ememNm9;
	private String ememNm10;
	
	// null일 경우 메세지용
	private String recMsg;
}
