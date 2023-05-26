package kr.co.jobara.helps.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.jobara.commons.service.AttatchService;
import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.AttatchVO;
import kr.co.jobara.commons.vo.MenuVO;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.helps.service.HelpsService;
import kr.co.jobara.helps.vo.HelpsVO;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.searchboard.vo.SearchVO;

@Controller
@RequestMapping("/helps")
public class HelpsRetrieveController {
	@Inject
	private MenuService menuService;
	
	@Inject
	private HelpsService service;
	
	@Inject
	private AttatchService attatchService;
	
	@RequestMapping("helpsView.do")
	public String helpsView(
		@RequestParam("what") int helpsSn
		, Model model
	) {
		List<MenuVO> menuList = menuService.retrieveMenu("cscenter_per");
		model.addAttribute("menuList", menuList);
		
		HelpsVO helps = service.retrieveHelps(helpsSn);
		model.addAttribute("helps", helps);
		
		// 답글불러오기
		HelpsVO helpsAns = service.retrieveHelpsAns(helpsSn);
		// 답글이 존재하면 객체전달
		if(helpsAns!=null) {
			model.addAttribute("helpsAns", helpsAns);
		}
		
		String attSn = helps.getAttSn();
		if(attSn != null) {
			List<AttatchVO> attatchList = attatchService.retrieveAttatchList(attSn);
			model.addAttribute("attatchList", attatchList);
		}
		
		return "helps/helpsView";
	}
	
	@RequestMapping("com/helpsView.do")
	public String helpsViewCom(
		@RequestParam("what") int helpsSn
		, Model model
	) {
		List<MenuVO> menuList = menuService.retrieveMenu("cscenter_com");
		model.addAttribute("menuList", menuList);
		
		HelpsVO helps = service.retrieveHelps(helpsSn);
		model.addAttribute("helps", helps);
		
		// 답글불러오기
		HelpsVO helpsAns = service.retrieveHelpsAns(helpsSn);
		// 답글이 존재하면 객체전달
		if(helpsAns!=null) {
			model.addAttribute("helpsAns", helpsAns);
		}
		
		String attSn = helps.getAttSn();
		if(attSn != null) {
			List<AttatchVO> attatchList = attatchService.retrieveAttatchList(attSn);
			model.addAttribute("attatchList", attatchList);
		}
		
		return "helps/helpsView";
	}
	
	// 일대일 문의 조회 및 답변등록 - 관리자
	@RequestMapping("admin/helpsView.do")
	public String helpsViewAdmin(
		@RequestParam("what") int helpsSn
		, Model model
	) {
		model.addAttribute("helpsAns", new HelpsVO());
		HelpsVO helps = service.retrieveHelps(helpsSn);
		model.addAttribute("helps", helps);
		
		// 답글불러오기
		HelpsVO helpsAns = service.retrieveHelpsAns(helpsSn);
		// 답글이 존재하면 객체전달
		if(helpsAns!=null) {
			model.addAttribute("helpsAns", helpsAns);
		}
		
		String attSn = helps.getAttSn();
		if(attSn != null) {
			List<AttatchVO> attatchList = attatchService.retrieveAttatchList(attSn);
			model.addAttribute("attatchList", attatchList);
		}
		
		return "admin/cs/helpsView";
	}
	
	// 일대일 문의 리스트 조회 json - 개인회원
	@RequestMapping(value="helpsList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String listToJson(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @SessionAttribute(value="authMember", required=false) PmemberVO authMember
		, @ModelAttribute("detailSearch") HelpsVO detailSearch
		, @ModelAttribute("simpleSearch") SearchVO simpleSearch
		, Model model	
	) {
		String pmemId = authMember.getPmemId();
		detailSearch.setHelpsId(pmemId);
		
		PagingVO<HelpsVO> paging = new PagingVO<>();
		paging.setCurrentPage(currentPage);
		paging.setSimpleSearch(simpleSearch);
		paging.setDetailSearch(detailSearch);
		
//		service.retrieveHelpsList(paging);
//		model.addAttribute("paging", paging);
		
		List<HelpsVO> list = service.retrieveHelpsList(paging);
		for (HelpsVO vo : list) {
			Integer helpsSn = vo.getHelpsSn();
			HelpsVO vo2 = service.retrieveHelpsAns(helpsSn);
			if (vo2==null) {
				vo.setAns(false);
			} else {
				vo.setAns(true);
			}
		}
		model.addAttribute("list", list);
		
//		service.retrieveHelpsList(paging);
//		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	// 일대일 문의 리스트 조회 - 개인회원
	@RequestMapping("helpsList.do")
	public String helpsList(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @SessionAttribute(value="authMember", required=false) PmemberVO authMember
		, @ModelAttribute("detailSearch") HelpsVO detailSearch
		, @ModelAttribute("simpleSearch") SearchVO simpleSearch
		, Model model
	) {
		List<MenuVO> menuList = menuService.retrieveMenu("cscenter_per");
		model.addAttribute("menuList", menuList);
		
		listToJson(currentPage, authMember, detailSearch, simpleSearch, model);
		
		return "helps/helpsList";
	}
	
	// 일대일 문의 리스트 조회 json - 기업회원
	@RequestMapping(value="com/helpsList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String comListToJson(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @SessionAttribute(value="authMember", required=false) EmemberVO authMember
		, @ModelAttribute("detailSearch") HelpsVO detailSearch
		, @ModelAttribute("simpleSearch") SearchVO simpleSearch
		, Model model	
	) {
		String ememId = authMember.getEmemId();
		detailSearch.setHelpsId(ememId);
		
		PagingVO<HelpsVO> paging = new PagingVO<>();
		paging.setCurrentPage(currentPage);
		paging.setSimpleSearch(simpleSearch);
		paging.setDetailSearch(detailSearch);
		
		List<HelpsVO> list = service.retrieveHelpsList(paging);
		for (HelpsVO vo : list) {
			Integer helpsSn = vo.getHelpsSn();
			HelpsVO vo2 = service.retrieveHelpsAns(helpsSn);
			if (vo2==null) {
				vo.setAns(false);
			} else {
				vo.setAns(true);
			}
		}
		model.addAttribute("list", list);
		
//		service.retrieveHelpsList(paging);
//		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	// 일대일 문의 리스트 조회 - 기업회원
	@RequestMapping("com/helpsList.do")
	public String helpsListCom(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @SessionAttribute(value="authMember", required=false) EmemberVO authMember
		, @ModelAttribute("detailSearch") HelpsVO detailSearch
		, @ModelAttribute("simpleSearch") SearchVO simpleSearch
		, Model model
	) {
		List<MenuVO> menuList = menuService.retrieveMenu("cscenter_com");
		model.addAttribute("menuList", menuList);
		
		comListToJson(currentPage, authMember, detailSearch, simpleSearch, model);
		
		return "helps/helpsListCom";
	}
	
	// 일대일 문의 리스트 조회 json -관리자
	@RequestMapping(value="admin/helpsList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String adminListToJson(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute("simpleSearch") SearchVO simpleSearch
		, Model model	
	) {
		PagingVO<HelpsVO> paging = new PagingVO<>();
		paging.setCurrentPage(currentPage);
		paging.setSimpleSearch(simpleSearch);
		
		List<HelpsVO> list = service.retrieveHelpsListAdmin(paging);
		for (HelpsVO vo : list) {
			Integer helpsSn = vo.getHelpsSn();
			HelpsVO vo2 = service.retrieveHelpsAns(helpsSn);
			if (vo2==null) {
				vo.setAns(false);
			} else {
				vo.setAns(true);
			}
		}
		
		model.addAttribute("list", list);
		
//		service.retrieveHelpsList(paging);
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	// 일대일 문의 리스트 조회 -관리자
	@RequestMapping("admin/helpsList.do")
	public String helpsListAdmin(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute("simpleSearch") SearchVO simpleSearch
		, Model model
	) {
		
		adminListToJson(currentPage, simpleSearch, model);
		
		return "admin/cs/helpsList";
	}
}
