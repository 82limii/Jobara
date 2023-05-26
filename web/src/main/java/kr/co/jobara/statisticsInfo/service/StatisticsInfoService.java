package kr.co.jobara.statisticsInfo.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.statisticsInfo.dao.StatisticsInfoDAO;
import kr.co.jobara.statisticsInfo.vo.StatisticsInfoVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StatisticsInfoService {

	@Inject
	private StatisticsInfoDAO dao;
	
	/**
	 * 연령 통계
	 * @param ememId
	 * @return
	 */
	public List<StatisticsInfoVO> retrieveYearList(String ememId) {
		StatisticsInfoVO less20 = StatisticsInfoVO.builder().empYearDiv("20대").build();
		StatisticsInfoVO less30 = StatisticsInfoVO.builder().empYearDiv("30대").build();
		StatisticsInfoVO less40 = StatisticsInfoVO.builder().empYearDiv("40대").build();
		StatisticsInfoVO less50 = StatisticsInfoVO.builder().empYearDiv("50대").build();
		StatisticsInfoVO over50 = StatisticsInfoVO.builder().empYearDiv("50대 이상").build();
		
		int less20Cnt = 0;
		int less30Cnt = 0;
		int less40Cnt = 0;
		int less50Cnt = 0;
		int over50Cnt = 0;
		
		log.info("ememId : " + ememId);
		
		List<StatisticsInfoVO> yearCnt = dao.selectEmpYearList(ememId);
		for (StatisticsInfoVO vo : yearCnt) {
			Integer cnt = vo.getYearCnt();
			if(cnt < 30) {
				less20Cnt++;
			} else if(cnt < 40) {
				less30Cnt++;
			} else if(cnt < 50) {
				less40Cnt++;
			} else if(cnt < 60) {
				less50Cnt++;
			} else {
				over50Cnt++;
			}
		}
		
		List<StatisticsInfoVO> list = new ArrayList<>();
		
		less20.setYearCnt(less20Cnt);
		less30.setYearCnt(less30Cnt);
		less40.setYearCnt(less40Cnt);
		less50.setYearCnt(less50Cnt);
		over50.setYearCnt(over50Cnt);
		
		list.add(less20);
		list.add(less30);
		list.add(less40);
		list.add(less50);
		list.add(over50);
		
		return list;
	}
	
	/**
	 * 연봉 정보
	 * @param ememId
	 * @return
	 */
	public List<StatisticsInfoVO> retrieveMoneyList(String ememId) {
		StatisticsInfoVO less3 = StatisticsInfoVO.builder().empMoneyDiv("2천만원대").build();
		StatisticsInfoVO less4 = StatisticsInfoVO.builder().empMoneyDiv("3천만원대").build();
		StatisticsInfoVO less5 = StatisticsInfoVO.builder().empMoneyDiv("4천만원대").build();
		StatisticsInfoVO less6 = StatisticsInfoVO.builder().empMoneyDiv("5천만원대").build();
		StatisticsInfoVO over6 = StatisticsInfoVO.builder().empMoneyDiv("6천만원대 이상").build();
		
		int less3Cnt = 0;
		int less4Cnt = 0;
		int less5Cnt = 0;
		int less6Cnt = 0;
		int over6Cnt = 0;
		
		List<StatisticsInfoVO> empSalary = dao.selectEmpMoneyList(ememId);
		for (StatisticsInfoVO vo : empSalary) {
			Integer cnt = vo.getEmpSalary();
			if(cnt < 3000) {
				less3Cnt++;
			} else if(cnt < 4000) {
				less4Cnt++;
			} else if(cnt < 5000) {
				less5Cnt++;
			} else if(cnt < 6000) {
				less6Cnt++;
			} else {
				over6Cnt++;
			}
		}
		
		List<StatisticsInfoVO> list = new ArrayList<>();
		
		less3.setEmpSalary(less3Cnt);
		less4.setEmpSalary(less4Cnt);
		less5.setEmpSalary(less5Cnt);
		less6.setEmpSalary(less6Cnt);
		over6.setEmpSalary(over6Cnt);
		
		list.add(less3);
		list.add(less4);
		list.add(less5);
		list.add(less6);
		list.add(over6);
		
		return list;
	}
}
