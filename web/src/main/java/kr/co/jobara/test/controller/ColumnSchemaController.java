package kr.co.jobara.test.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.test.service.ColumnSchemaService;
import kr.co.jobara.test.vo.ColumnSchemaVO;

@Controller
@RequestMapping("/cols/schemas.do")
public class ColumnSchemaController{
	@Inject
	private ColumnSchemaService service;
	
	@ModelAttribute("tableNames")
	public List<String> tableNames(){
		return service.retrieveTableNames();
	}
	
	@ModelAttribute("pagingVO")
	public PagingVO<ColumnSchemaVO> pagingVO(){
		PagingVO<ColumnSchemaVO> pagingVO = new PagingVO<>();
		pagingVO.setDetailSearch(new ColumnSchemaVO());
		return pagingVO;
	}
	
	@RequestMapping
	public String colView(
		@RequestParam(required=false, defaultValue="1") int currentPage
		, @ModelAttribute("pagingVO") PagingVO<ColumnSchemaVO> pagingVO
		, Model model
	){
//		1. 요청 받기
//		2. 분석(파라미터, 헤더->검증)
//		3. 모델 생성(MVC 패턴, Layered Arche) - logic layer 로 부터 모델 생성.
		pagingVO.setCurrentPage(currentPage);
		service.retrieveColumnSchemaList(pagingVO);
		
//		5. 뷰 선택
		return "schemaView";
	}
}













