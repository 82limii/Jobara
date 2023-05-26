package kr.co.jobara.jobboard.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import kr.co.jobara.resume.service.ResumeService;
import kr.co.jobara.resume.vo.ResumeVO;
import kr.co.jobara.statistics.service.StatisticsService;
import kr.co.jobara.statistics.vo.StatisticsVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 이상림
 * 채용공고 목록 조회, 상세보기 => 기업회원, 개인회원, 비로그인
 *
 */
@Controller
@Slf4j
@RequestMapping("/jobboard")
public class JobBoardRetrieveController {
	
	@Inject
	private MenuService menuService;
	
	@Inject
	private AttatchService attatchService;
	
	@Inject
	private JobBoardService service;
	
	@Inject
	private ResumeService resumeService;
	
	@Inject
	private StatisticsService staService;
	
	// 공고 목록 조회
	@RequestMapping("jobBoardList.do")
	public String list(
			Model model
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("detailSearch") JobBoardVO detailSearch
	) {
		
		PagingVO<JobBoardVO> pagingVO = listToJson(currentPage, detailSearch);
		model.addAttribute("pagingVO", pagingVO);
		
		List<MenuVO> menuList = menuService.retrieveMenu("jobboard_per");
		model.addAttribute("menuList", menuList);
		return "jobboard/jobboardList";
	}
	
	// 공고 목록 조회 - 기업회원
	@RequestMapping("com/jobBoardList.do")
	public String listCom(
			Model model
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("detailSearch") JobBoardVO detailSearch
	) {
		
		PagingVO<JobBoardVO> pagingVO = listToJson(currentPage, detailSearch);
		model.addAttribute("pagingVO", pagingVO);
		
		List<MenuVO> menuList = menuService.retrieveMenu("jobboard_com");
		model.addAttribute("menuList", menuList);
		return "jobboard/jobboardList";
	}
	
	
	// 전체공고 목록 json
	@RequestMapping(value={"jobBoardList.do", "com/jobBoardList.do"}
					, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<JobBoardVO> listToJson(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, JobBoardVO detailSearch
	) {
		detailSearch.setPageDiv("y");
		PagingVO<JobBoardVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailSearch(detailSearch);
		service.retrieveJobBoardList(pagingVO);
		return pagingVO;
	}
	
	
	// 채용공고 상세보기
	@RequestMapping({"jobBoardView.do", "com/jobBoardView.do"})
	public String view(
			@RequestParam(value="what", required=true) int jobSn
			, Model model
			, HttpSession session
//			, @SessionAttribute("authMember") MemberVO member
	) {
		JobBoardVO jobBoard = service.retrieveJobBoard(jobSn);
		model.addAttribute("jobBoard", jobBoard);
		
		// 통계 - 학력정보
		String view = "job";
		List<StatisticsVO> eduSta = staService.retrieveEduList(jobSn, view);
		model.addAttribute("eduSta", eduSta);
		// 통계 - 경력정보
		List<StatisticsVO> carSta = staService.retrieveCarList(jobSn, view);
		model.addAttribute("carSta", carSta);
		// 지원자 수
		StatisticsVO appCntVO = staService.retrieveAppCnt(jobSn, view);
		model.addAttribute("appCntVO", appCntVO);
		if (appCntVO.getAppCnt()==0) {
			// 지원자가 없을경우 메세지
			StatisticsVO sta = StatisticsVO.builder().msg("지원자가 없습니다.").build();
			model.addAttribute("sta", sta);
		}
		
		String attSn = jobBoard.getAttSn();
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
		
		return "jobboard/jobboardView";
	}
	
	@RequestMapping(value="jobBoardView.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String resumeListJson(
			PagingVO<ResumeVO> paging
			, int currentPage
			, Model model
	) {
		resumeService.retrieveResumeList(paging);
		return "jsonView";
	}
	
	// 기업별 공고 모아보기
	@RequestMapping("myJobBoard.do")
	public String jobBoard(
			Model model
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @RequestParam(value="what", required=true) String ememId
			, @ModelAttribute("detailSearch") JobBoardVO detailSearch
			, HttpSession session
	) {
		detailSearch.setEmemId(ememId);
		PagingVO<JobBoardVO> pagingVO = myListToJsonPer(currentPage, detailSearch, ememId);
		model.addAttribute("pagingVO", pagingVO);
		
		List<MenuVO> menuList = menuService.retrieveMenu("compage_per");
		model.addAttribute("menuList", menuList);
		return "jobboard/myjobboard";
	}
	
	@RequestMapping("com/myJobBoard.do")
	public String myJobBoard(
			Model model
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("detailSearch") JobBoardVO detailSearch
			, @SessionAttribute("authMember") EmemberVO auth
	) {
		detailSearch.setEmemId(auth.getEmemId());
		PagingVO<JobBoardVO> pagingVO = myListToJson(currentPage, detailSearch, auth);
		model.addAttribute("pagingVO", pagingVO);
		
		List<MenuVO> menuList = menuService.retrieveMenu("jobboard_com");
		model.addAttribute("menuList", menuList);
		return "jobboard/myjobboard";
	}
	
	// 기업별 공고 목록 json
	@RequestMapping(value="myJobBoard.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<JobBoardVO> myListToJsonPer(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, JobBoardVO detailSearch
		, @RequestParam("what") String ememId
	) {
		detailSearch.setEmemId(ememId);
		PagingVO<JobBoardVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailSearch(detailSearch);
		service.retreiveMyJobBoard(pagingVO);
		return pagingVO;
	}
	@RequestMapping(value="com/myJobBoard.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<JobBoardVO> myListToJson(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, JobBoardVO detailSearch
			, @SessionAttribute("authMember") EmemberVO auth
			) {
		detailSearch.setEmemId(auth.getEmemId());
		PagingVO<JobBoardVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailSearch(detailSearch);
		service.retreiveMyJobBoard(pagingVO);
		return pagingVO;
	}
}
