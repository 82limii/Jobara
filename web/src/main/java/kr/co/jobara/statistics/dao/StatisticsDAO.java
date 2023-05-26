package kr.co.jobara.statistics.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.statistics.vo.StatisticsVO;

/**
 * @author 이상림
 * 최초작성일 2022.03.27
 */
@Mapper
public interface StatisticsDAO {
	// 학력정보 관련 통계 쿼리
	public List<StatisticsVO> selectEdiSnJobList(int jobSn);
	public List<StatisticsVO> selectEdiSnProList(int probSn);
	// 학력정보의 껍데기 정보
	public List<StatisticsVO> selectEduDiv();
	
	// 경력정보 관련 통계 쿼리
	public List<StatisticsVO> selectYearCntJob(int jobSn);
	public List<StatisticsVO> selectYearCntPro(int probSn);
	
	// 지원자 수
	public StatisticsVO selectApplyCntJob(int jobSn);
	public StatisticsVO selectApplyCntPro(int probn);
	
}
