package kr.co.jobara.info.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.MenuVO;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.info.service.InfoService;
import kr.co.jobara.info.vo.InfoVO;
import kr.co.jobara.jobboard.vo.JobBoardVO;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.statisticsInfo.service.StatisticsInfoService;
import kr.co.jobara.statisticsInfo.vo.StatisticsInfoVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/info")
public class InfoRetrieveController {
	@Inject
	private MenuService menuService;
	
	@Inject
	private InfoService service;
	
	@Inject
	private StatisticsInfoService staService;
	
	// 기업 정보 조회 - 기업회원
	@RequestMapping("info.do")
	public String info(
		@RequestParam("what") String ememId
		, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute("detailSearch") JobBoardVO detailSearch
		, Model model
	) {
		List<MenuVO> menuList = menuService.retrieveMenu("compage_per");
		model.addAttribute("menuList", menuList);
		
		InfoVO infoe = service.retrieveInfo(ememId);
		model.addAttribute("infoe", infoe);
		
		List<StatisticsInfoVO> infoYear = staService.retrieveYearList(ememId);
		model.addAttribute("infoYear", infoYear);
		
		List<StatisticsInfoVO> infoMoney = staService.retrieveMoneyList(ememId);
		model.addAttribute("infoMoney", infoMoney);
		
		return "info/infoView";
	}
	
	// 기업 정보 조회 - 개인회원
	@ResponseBody
	@RequestMapping(value="info.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PagingVO<JobBoardVO> listToJson(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @RequestParam("what") String ememId
		, @ModelAttribute("detailSearch") JobBoardVO detailSearch
	){
		detailSearch.setEmemId(ememId);
		
		PagingVO<JobBoardVO> pagingVO = new PagingVO<>(2,2);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailSearch(detailSearch);
		
		service.retrieveInfoList(pagingVO);
		
		return pagingVO;
	}
	
	// 기업 정보 조회 - 기업회원
	@RequestMapping("com/info.do")
	public String infoCom(
		@SessionAttribute(value="authMember", required=false) EmemberVO authMember
		, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute("detailSearch") JobBoardVO detailSearch
		, Model model
	) {
		PagingVO<JobBoardVO> pagingVO = listToJsonCom(currentPage, authMember, detailSearch);
		model.addAttribute("pagingVO", pagingVO);
		
		String ememId = authMember.getEmemId();
		List<MenuVO> menuList = menuService.retrieveMenu("compage_com");
		model.addAttribute("menuList", menuList);
		
		InfoVO infoe = service.retrieveInfo(ememId);
		model.addAttribute("infoe", infoe);
		
		log.info("ememId : " + ememId);
		
		//연령통계
		List<StatisticsInfoVO> infoYear = staService.retrieveYearList(ememId);
		model.addAttribute("infoYear", infoYear);
		
		List<StatisticsInfoVO> infoMoney = staService.retrieveMoneyList(ememId);
		model.addAttribute("infoMoney", infoMoney);
		
		return "info/infoView";
	}
	
	// 기업 정보 조회 - 기업회원
	@ResponseBody
	@RequestMapping(value="com/info.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PagingVO<JobBoardVO> listToJsonCom(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @SessionAttribute(value="authMember", required=false) EmemberVO authMember
		, @ModelAttribute("detailSearch") JobBoardVO detailSearch
	){
		String ememId = authMember.getEmemId();
		detailSearch.setEmemId(ememId);
		
		PagingVO<JobBoardVO> pagingVO = new PagingVO<>(2,2);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailSearch(detailSearch);
		
		service.retrieveInfoList(pagingVO);
		
		return pagingVO;
	}
	
}
