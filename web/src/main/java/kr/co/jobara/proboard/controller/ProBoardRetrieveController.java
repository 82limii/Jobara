package kr.co.jobara.proboard.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.jobara.commons.service.AttatchService;
import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.AttatchVO;
import kr.co.jobara.commons.vo.MenuVO;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.jobboard.service.JobBoardService;
import kr.co.jobara.jobboard.vo.JobBoardVO;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.member.vo.MemberVO;
import kr.co.jobara.proboard.service.ProBoardService;
import kr.co.jobara.proboard.vo.ProBoardVO;
import kr.co.jobara.resume.service.ResumeService;
import kr.co.jobara.resume.vo.ResumeVO;
import kr.co.jobara.statistics.service.StatisticsService;
import kr.co.jobara.statistics.vo.StatisticsVO;

/**
 * @author 이상림
 * 프로젝트 공고 목록 조회, 상세보기 => 기업회원, 개인회원, 비로그인
 *
 */
@Controller
@RequestMapping("/proboard")
public class ProBoardRetrieveController {
	
	@Inject
	private MenuService menuService;
	
	@Inject
	private AttatchService attatchService;
	
	@Inject
	private ProBoardService service;
	
	@Inject
	private ResumeService resumeService;
	
	@Inject
	private StatisticsService staService;
	
	// 공고 목록 조회
	@RequestMapping("proBoardList.do")
	public String list(
			Model model
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("detailSearch") ProBoardVO detailSearch
	) {
		
		PagingVO<ProBoardVO> pagingVO = listToJson(currentPage, detailSearch);
		model.addAttribute("pagingVO", pagingVO);
		
		List<MenuVO> menuList = menuService.retrieveMenu("proboard_per");
		model.addAttribute("menuList", menuList);
		return "proboard/proboardList";
	}
	
	// 공고 목록 조회 - 기업회원
	@RequestMapping("com/proBoardList.do")
	public String listCom(
			Model model
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("detailSearch") ProBoardVO detailSearch
	) {
		
		PagingVO<ProBoardVO> pagingVO = listToJson(currentPage, detailSearch);
		model.addAttribute("pagingVO", pagingVO);
		List<MenuVO> menuList = menuService.retrieveMenu("proboard_com");
		model.addAttribute("menuList", menuList);
		return "proboard/proboardList";
	}
	
	
	// 목록 json
	@RequestMapping(value={"proBoardList.do", "com/proBoardList.do", "myProBoard.do", "com/myProBoard.do"}
					, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<ProBoardVO> listToJson(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, ProBoardVO detailSearch
	) {
		detailSearch.setPageDiv("y");
		PagingVO<ProBoardVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailSearch(detailSearch);
		if(detailSearch.getEmemId()==null) {
			service.retrieveProBoardList(pagingVO);
		} else {
			service.retrieveMyProBoard(pagingVO);
		}
		return pagingVO;
	}
	
	// 프로젝트 공고 상세보기	
	@RequestMapping({"proBoardView.do", "com/proBoardView.do"})
	public String view(
			@RequestParam(value="what", required=true) int probSn
			, Model model
			, HttpSession session
	) {
		ProBoardVO proBoard = service.retrieveProBoard(probSn);
		model.addAttribute("proBoard", proBoard);
		
		// 통계 - 학력정보
		String view = "pro";
		List<StatisticsVO> eduSta = staService.retrieveEduList(probSn, view);
		model.addAttribute("eduSta", eduSta);
		// 통계 - 경력정보
		List<StatisticsVO> carSta = staService.retrieveCarList(probSn, view);
		model.addAttribute("carSta", carSta);
		// 지원자 수
		StatisticsVO appCntVO = staService.retrieveAppCnt(probSn, view);
		model.addAttribute("appCntVO", appCntVO);
		if (appCntVO.getAppCnt()==0) {
			// 지원자가 없을경우 메세지
			StatisticsVO sta = StatisticsVO.builder().msg("지원자가 없습니다.").build();
			model.addAttribute("sta", sta);
		}
		
		String attSn = proBoard.getAttSn();
		if(attSn != null) {
			List<AttatchVO> attatchList = attatchService.retrieveAttatchList(attSn);
			model.addAttribute("attatchList", attatchList);
		}
		MemberVO member = (MemberVO) session.getAttribute("authMember");
		if(member != null) {
			PagingVO<ResumeVO> paging = new PagingVO<>();
			
			if(member.getUserType().equals("Pmember")) {
				String id = member.getId();
				paging.setPmemId(id);
				int currentPage = 1;
				resumeListJson(paging, currentPage, model);
				model.addAttribute("paging", paging);
			}
					
		}
		return "proboard/proboardView";
	}
	
	@RequestMapping(value="proBoardView.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String resumeListJson(
			PagingVO<ResumeVO> paging
			, int currentPage
			, Model model
	) {
		resumeService.retrieveResumeList(paging);
		return "jsonView";
	}
	
	// 기업별 공고 모아보기
	@RequestMapping("myProBoard.do")
	public String proBoard(
			Model model
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @RequestParam(value="what", required=true) String ememId
			, @ModelAttribute("detailSearch") ProBoardVO detailSearch
	) {
		detailSearch.setEmemId(ememId);
		PagingVO<ProBoardVO> pagingVO = listToJson(currentPage, detailSearch);
		model.addAttribute("pagingVO", pagingVO);
		
		List<MenuVO> menuList = menuService.retrieveMenu("compage_per");
		model.addAttribute("menuList", menuList);
		return "proboard/myproboard";
	}
	@RequestMapping("com/myProBoard.do")
	public String myProBoard(
			Model model
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("detailSearch") ProBoardVO detailSearch
			, @SessionAttribute("authMember") EmemberVO auth
	) {
		String ememId = auth.getEmemId();
		detailSearch.setEmemId(ememId);
		PagingVO<ProBoardVO> pagingVO = listToJson(currentPage, detailSearch);
		model.addAttribute("pagingVO", pagingVO);
		
		List<MenuVO> menuList = menuService.retrieveMenu("proboard_com");
		model.addAttribute("menuList", menuList);
		return "proboard/myproboard";
	}
}
