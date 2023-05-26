package kr.co.jobara.controller;

import static org.junit.Assert.assertNotNull;
//가짜 request 를 만들어 줌
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
//가짜 request 를 만들어서 테스트케이스를 실행했을 때 돌아오는 결과가 있는데 그결과를 테스트 그때 동작하는게 RM
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.test.controller.ColumnSchemaController;
import kr.co.jobara.test.dao.ColumnSchemaDAO;
import kr.co.jobara.test.service.ColumnSchemaService;
import kr.co.jobara.test.vo.ColumnSchemaVO;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextHierarchy({
	@ContextConfiguration("file:webapp/WEB-INF/spring/*-context.xml")
	, @ContextConfiguration("file:webapp/WEB-INF/spring/appServlet/servlet-context.xml")
})
@Slf4j
public class ContextHierarchyInitializeTest {
	
	@Inject
	private ColumnSchemaDAO dao;
	
	@Inject
	private ColumnSchemaService service;
	
	@Inject
	private ColumnSchemaController controller;
	
	private MockMvc mockMVC;
	
	@Before
	public void before() {
		mockMVC = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void testInjection() {
		log.info("dao on root : {}", dao);
		assertNotNull(dao);
		log.info("service on root : {}", service);
		assertNotNull(service);
		log.info("controller on child : {}", controller);
		assertNotNull(controller);
	}
	
	@Test
	public void testMapperProxy() {
		PagingVO<ColumnSchemaVO> pagingVO = new PagingVO<>();
		List<ColumnSchemaVO> list = dao.selectColumnSchemaList(pagingVO);
		assertNotNull(list);
	}

	@Test
	public void testController() throws Exception {
		mockMVC.perform(get("/cols/schemas.do"))
			.andExpect(model().attributeExists("pagingVO"))
			.andExpect(view().name("schemaView"))
			.andDo(log())
			.andReturn();
	}
}
