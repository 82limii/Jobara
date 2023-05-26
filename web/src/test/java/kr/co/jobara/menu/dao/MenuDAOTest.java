package kr.co.jobara.menu.dao;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import kr.co.jobara.commons.dao.MenuDAO;
import kr.co.jobara.commons.vo.MenuVO;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:webapp/WEB-INF/spring/*-context.xml")
@WebAppConfiguration
public class MenuDAOTest {

	@Inject
	private MenuDAO dao;
	
	@Test
	public void testSelectMenu() {
		List<MenuVO> menuList = dao.selectMenu("jobboard_com");
		System.out.println(menuList);
		
	}

}
