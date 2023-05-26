package kr.co.jobara.statistics.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.statistics.dao.StatisticsDAO;
import kr.co.jobara.statistics.vo.StatisticsVO;

/**
 * @author 이상림
 * 최초작성일 2022.03.27
 */
@Service
public class StatisticsService {
	@Inject
	private StatisticsDAO dao;
	
	public List<StatisticsVO> retrieveEduList(int sn, String view) {
		List<StatisticsVO> list = dao.selectEduDiv();
		for (StatisticsVO vo : list) {
			vo.setEdiCnt(0);
		}
		
		List<StatisticsVO> tmp = null;
		if("job".equals(view)) {
			tmp = dao.selectEdiSnJobList(sn);
		} else if("pro".equals(view)) {
			tmp = dao.selectEdiSnProList(sn);
		}
		
		for (StatisticsVO vo : list) {
			for (StatisticsVO tmpVO : tmp) {
				if(vo.getEdiSn().equals(tmpVO.getEdiSn())) {
					vo.setEdiCnt(tmpVO.getEdiCnt());
				}
			}
		}
		
		return list;
	}
	
	public List<StatisticsVO> retrieveCarList(int sn, String view) {
		StatisticsVO less1 = StatisticsVO.builder().yearDiv("신입(1년미만)").build();
		StatisticsVO less3 = StatisticsVO.builder().yearDiv("1~3년").build();
		StatisticsVO less5 = StatisticsVO.builder().yearDiv("3~5년").build();
		StatisticsVO less10 = StatisticsVO.builder().yearDiv("5~10년").build();
		StatisticsVO over10 = StatisticsVO.builder().yearDiv("10년 이상").build();
		
		int less1Cnt = 0;
		int less3Cnt = 0;
		int less5Cnt = 0;
		int less10Cnt = 0;
		int over10Cnt = 0;
		
		if("job".equals(view)) {
			List<StatisticsVO> yearCnt = dao.selectYearCntJob(sn);
			for (StatisticsVO vo : yearCnt) {
				Integer cnt = vo.getYearCnt();
				if(cnt < 1) {
					less1Cnt++;
				} else if(cnt < 3) {
					less3Cnt++;
				} else if(cnt < 5) {
					less5Cnt++;
				} else if(cnt < 10) {
					less10Cnt++;
				} else {
					over10Cnt++;
				}
			}
		} else if("pro".equals(view)) {
			List<StatisticsVO> yearCnt = dao.selectYearCntPro(sn);
			for (StatisticsVO vo : yearCnt) {
				Integer cnt = vo.getYearCnt();
				if(cnt < 1) {
					less1Cnt++;
				} else if(cnt < 3) {
					less3Cnt++;
				} else if(cnt < 5) {
					less5Cnt++;
				} else if(cnt < 10) {
					less10Cnt++;
				} else {
					over10Cnt++;
				}
			}
		}
		
		if(less1Cnt==0 && less3Cnt==0 && less5Cnt==0 && less10Cnt==0 && over10Cnt==0) {
			less1Cnt = 1;
		}
		List<StatisticsVO> list = new ArrayList<>();
		
		less1.setCarCnt(less1Cnt);
		less3.setCarCnt(less3Cnt);
		less5.setCarCnt(less5Cnt);
		less10.setCarCnt(less10Cnt);
		over10.setCarCnt(over10Cnt);
		
		list.add(less1);
		list.add(less3);
		list.add(less5);
		list.add(less10);
		list.add(over10);
		
		return list;
	}
	
	public StatisticsVO retrieveAppCnt(int sn, String view) {
		StatisticsVO appCntVO = null;
		if ("job".equals(view)) {
			appCntVO = dao.selectApplyCntJob(sn);
		} else if ("pro".equals(view)) {
			appCntVO = dao.selectApplyCntPro(sn);
		}
		return appCntVO;
	}

}
