package kr.co.jobara.qna.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.jobara.commons.service.AttatchService;
import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.AttatchVO;
import kr.co.jobara.commons.vo.MenuVO;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.qna.service.QnaService;
import kr.co.jobara.qna.vo.QnaVO;
import kr.co.jobara.searchboard.vo.SearchVO;

@Controller
@RequestMapping("/qna")
public class QnaRetrieveController {
	@Inject
	private MenuService menuService;
	
	@Inject
	private QnaService service;
	
	@Inject
	private AttatchService attatchService;
	
	@RequestMapping("qnaView.do")
	public String qnaRetrieve(
		@RequestParam("what") int qnaSn
		, Model model
	) {
		List<MenuVO> menuList = menuService.retrieveMenu("compage_per");
		model.addAttribute("menuList", menuList);
		
		QnaVO qna = service.retrieveQna(qnaSn);
		model.addAttribute("qna", qna);
		
		String attSn = qna.getAttSn();
		if(attSn != null) {
			List<AttatchVO> attatchList = attatchService.retrieveAttatchList(attSn);
			model.addAttribute("attatchList", attatchList);
		}
		
		return "qna/qnaView";
	}
	@RequestMapping("com/qnaView.do")
	public String qnaRetrieveCom(
		@RequestParam("what") int qnaSn
		, Model model
	) {
		List<MenuVO> menuList = menuService.retrieveMenu("compage_com");
		model.addAttribute("menuList", menuList);
		
		QnaVO qna = service.retrieveQna(qnaSn);
		model.addAttribute("qna", qna);
		
		String attSn = qna.getAttSn();
		if(attSn != null) {
			List<AttatchVO> attatchList = attatchService.retrieveAttatchList(attSn);
			model.addAttribute("attatchList", attatchList);
		}
		
		return "qna/qnaView";
	}
	
	@RequestMapping(value="qnaList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String listToJson(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @RequestParam("what") String ememId
		, @ModelAttribute("simpleSearch") SearchVO simpleSearch
		, Model model
	) {
		
		PagingVO<QnaVO> paging = new PagingVO<>();
		paging.setCurrentPage(currentPage);
		paging.setSimpleSearch(simpleSearch);
		
		service.retrieveQnaList(paging);
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	@RequestMapping("qnaList.do")
	public String qnaList(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @RequestParam("what") String ememId
		, @ModelAttribute("simpleSearch") SearchVO simpleSearch
		, Model model
	) {
		model.addAttribute("ememId",ememId);
		
		List<MenuVO> menuList = menuService.retrieveMenu("compage_per");
		model.addAttribute("menuList", menuList);
		
		listToJson(currentPage, ememId, simpleSearch, model);
		
		return "qna/qnaList";
	}
	
	@RequestMapping(value="com/qnaList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String comListToJson(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
//		, @SessionAttribute(value="authMember", required=false) EmemberVO authMember
		, @ModelAttribute("simpleSearch") SearchVO simpleSearch
//		, @ModelAttribute("detailSearch") QnaVO detailSearch
		, Model model
	) {
//		String ememId = authMember.getEmemId();
//		detailSearch.setEmemId(ememId);
		
		PagingVO<QnaVO> paging = new PagingVO<>();
		paging.setCurrentPage(currentPage);
		paging.setSimpleSearch(simpleSearch);
		
		service.retrieveQnaList(paging);
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	@RequestMapping("com/qnaList.do")
	public String qnaListCom(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
//		, @SessionAttribute(value="authMember", required=false) EmemberVO authMember
		, @ModelAttribute("simpleSearch") SearchVO simpleSearch
//		, @ModelAttribute("detailSearch") QnaVO detailSearch
		, Model model
	) {
		List<MenuVO> menuList = menuService.retrieveMenu("compage_com");
		model.addAttribute("menuList", menuList);
		
		comListToJson(currentPage, simpleSearch, model);
		
		return "qna/qnaListCom";
	}
}