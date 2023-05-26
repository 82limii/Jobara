package kr.co.jobara.statisticsInfo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.statisticsInfo.vo.StatisticsInfoVO;

@Mapper
public interface StatisticsInfoDAO {
	
	/**
	 * 연령 통계
	 * @param ememId
	 * @return
	 */
	public List<StatisticsInfoVO> selectEmpYearList(String ememId);
	
	/**
	 * 연봉 정보
	 * @param ememId
	 * @return
	 */
	public List<StatisticsInfoVO> selectEmpMoneyList(String ememId);
	
}
