package kr.co.jobara.commons.dao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import kr.co.jobara.commons.vo.CommonCodeVO;
import kr.co.jobara.commons.vo.LocationVO;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:webapp/WEB-INF/spring/*-context.xml")
@WebAppConfiguration
@Slf4j
public class CommonCodeDAOTest {
	
	@Inject
	private CommonCodeDAO dao;
	
	@Test
	public void test() {
		List<CommonCodeVO> list = dao.selectCodeList();
		Set<String> nameSet = new HashSet<>();
		Map<String, List<CommonCodeVO>> map = new HashMap<>();
		for (CommonCodeVO commonCodeVO : list) {
			String combNm = commonCodeVO.getCombNm();
			nameSet.add(combNm);
			
		}
		for (String string : nameSet) {
		
		}
//		System.out.println(list);
	}
	
//	@Test
	public void testSelectBigCodeList() {
		List<CommonCodeVO> bigCodeList = dao.selectBigCodeList();
		log.info(bigCodeList.toString());
	}
	
//	@Test
	public void testSelectMiddleCodeList() {
		List<CommonCodeVO> middleCodeList = dao.selectMiddleCodeList("WAY");
		log.info(middleCodeList.toString());
	}

//	@Test
	public void testSelectSmallCodeList() {
		List<CommonCodeVO> smallCodeList = dao.selectSmallCodeList("STA05");
		log.info(smallCodeList.toString());
	}

//	@Test
	public void testSelectCityList() {
		List<LocationVO> list = dao.selectCityList();
		log.info(list.toString());
	}

//	@Test
	public void testSelectLocationList() {
		List<LocationVO> locList = dao.selectLocationList("31");
		log.info(locList.toString());
	}

}
