package kr.co.jobara.notice.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.MenuVO;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.notice.service.NoticeService;
import kr.co.jobara.notice.vo.NoticeVO;
import kr.co.jobara.searchboard.vo.SearchVO;

@Controller
@RequestMapping("/notice")
public class NoticeRetrieveController {
	@Inject
	private MenuService menuService;
	
	@Inject
	private NoticeService service;
	
	@RequestMapping("noticeView.do")
	public String noticeView(
		@RequestParam("what") int noticeSn
		, Model model
	) {
		List<MenuVO> menuList = menuService.retrieveMenu("cscenter_per");
		model.addAttribute("menuList", menuList);
		
		NoticeVO notice = service.retrieveNotice(noticeSn);
		model.addAttribute("notice", notice);
		
		return "notice/noticeView";
	}
	
	@RequestMapping("com/noticeView.do")
	public String noticeViewCom(
		@RequestParam("what") int noticeSn
		, Model model
	) {
		List<MenuVO> menuList = menuService.retrieveMenu("cscenter_com");
		model.addAttribute("menuList", menuList);
		
		NoticeVO notice = service.retrieveNotice(noticeSn);
		model.addAttribute("notice", notice);
		
		return "notice/noticeView";
	}
	
	@RequestMapping("admin/noticeView.do")
	public String noticeViewAdmin(
		@RequestParam("what") int noticeSn
		, Model model
	) {
		NoticeVO notice = service.retrieveNotice(noticeSn);
		model.addAttribute("notice", notice);
		
		return "admin/cs/noticeView";
	}
	
	@RequestMapping(value="noticeList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String listToJson(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute("simpleSearch") SearchVO simpleSearch
		, Model model
	) {
		PagingVO<NoticeVO> paging = new PagingVO<>();
		paging.setCurrentPage(currentPage);
		paging.setSimpleSearch(simpleSearch);
		
		service.retrieveNoticeList(paging);
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	@RequestMapping("noticeList.do")
	public String noticeList(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute("simpleSearch") SearchVO simpleSearch
		, Model model
	) {
		List<MenuVO> menuList = menuService.retrieveMenu("cscenter_per");
		model.addAttribute("menuList", menuList);
		
		listToJson(currentPage, simpleSearch, model);
		
		return "notice/noticeList";
	}
	
	@RequestMapping(value="com/noticeList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String comListToJson(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("simpleSearch") SearchVO simpleSearch
			, Model model
			) {
		PagingVO<NoticeVO> paging = new PagingVO<>();
		paging.setCurrentPage(currentPage);
		paging.setSimpleSearch(simpleSearch);
		
		service.retrieveNoticeList(paging);
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	@RequestMapping("com/noticeList.do")
	public String comNoticeList(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("simpleSearch") SearchVO simpleSearch
			, Model model
			) {
		List<MenuVO> menuList = menuService.retrieveMenu("cscenter_com");
		model.addAttribute("menuList", menuList);
		
		comListToJson(currentPage, simpleSearch, model);
		
		return "notice/noticeList";
	}
	@RequestMapping(value="admin/noticeList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String adminListToJson(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("simpleSearch") SearchVO simpleSearch
			, Model model
			) {
		PagingVO<NoticeVO> paging = new PagingVO<>();
		paging.setCurrentPage(currentPage);
		paging.setSimpleSearch(simpleSearch);
		
		service.retrieveNoticeList(paging);
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	@RequestMapping("admin/noticeList.do")
	public String adminNoticeList(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("simpleSearch") SearchVO simpleSearch
			, Model model
			) {
		comListToJson(currentPage, simpleSearch, model);
		
		return "admin/cs/noticeList";
	}
}
