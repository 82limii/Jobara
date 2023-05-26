package kr.co.jobara.statistics.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticsVO {
	// 학력정보 통계
	public String ediSn;
	public String ediNm;
	public Integer ediCnt;
	
	// 경력정보 통계
	public Integer reSn;
	public Integer yearCnt;
	public String yearDiv;
	public int carCnt;
	
	// 지원자 명수
	public int appCnt;
	
	// 정보가 없을시 메세지
	public String msg;
}
