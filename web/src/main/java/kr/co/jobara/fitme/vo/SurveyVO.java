package kr.co.jobara.fitme.vo;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 이상림
 * 최초작성일 2022.03.23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyVO {
	private Integer surSn;
	private String pmemId;
	@NotBlank
	private String sur1;
	@NotBlank
	private String sur2;
	@NotBlank
	private String sur3;
	@NotBlank
	private String sur4;
	@NotBlank
	private String sur5;
	@NotBlank
	private String sur6;
	@NotBlank
	private String sur7;
	@NotBlank
	private String sur8;
	@NotBlank
	private String sur9;
	@NotBlank
	private String sur10;
	@NotBlank
	private String sur11;
	@NotBlank
	private String sur12;
	@NotBlank
	private String sur13;
	@NotBlank
	private String sur14;
	@NotBlank
	private String sur15;
	@NotBlank
	private String sur16;
	@NotBlank
	private String sur17;
	@NotBlank
	private String sur18;
	@NotBlank
	private String sur19;
	@NotBlank
	private String sur20;
	private String surDate;
	
	// null일 경우 메세지용
	private String surMsg;
}
