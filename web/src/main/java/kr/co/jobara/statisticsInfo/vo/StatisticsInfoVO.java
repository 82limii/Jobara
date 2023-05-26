package kr.co.jobara.statisticsInfo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticsInfoVO {
	
	//연령 통계
	public Integer empSn; 
	public Integer empYearCnt; 
	public String empYearDiv;
	public int yearCnt;
	
	//연봉정보
	public Integer empMoneyCnt;
	public String empMoneyDiv;
	public int moneyCnt;
	
	public Integer empSalary;
	
	
}
