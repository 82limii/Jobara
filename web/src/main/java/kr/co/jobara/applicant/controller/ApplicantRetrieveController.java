package kr.co.jobara.applicant.controller;

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

import kr.co.jobara.applicant.service.ApplicantService;
import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.MenuVO;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.jobboard.vo.JobBoardVO;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.proboard.vo.ProBoardVO;

/**
 * @author 전영준
 *	채용, 프로젝트 지원자 관리 => 지원자 리스트
 */
@Controller
@RequestMapping("/applicant")
public class ApplicantRetrieveController {
	
	@Inject
	private MenuService menuService;
	
	@Inject
	private ApplicantService service;
	
	// 채용지원자 관리 목록 - 기업회원
	@RequestMapping("com/jobApplicantList.do")
	public String jobApplicant(
		Model model
		, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @SessionAttribute(value="authMember", required=false) EmemberVO authMember
		, @ModelAttribute("detailSearch") JobBoardVO detailSearch
	) {
		List<MenuVO> menuList = menuService.retrieveMenu("compage_com");
		model.addAttribute("menuList", menuList);
		
		jobListToJson(currentPage, authMember, detailSearch, model);
		
		return "apply/applicant/jobApplicantList";
	}
	
	@RequestMapping(value="com/jobApplicantList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String jobListToJson(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @SessionAttribute(value="authMember", required=false) EmemberVO authMember
		, @ModelAttribute("detailSearch") JobBoardVO detailSearch
		, Model model
	){
		String ememId = authMember.getEmemId();
		detailSearch.setEmemId(ememId);
		
		PagingVO<JobBoardVO> pagingVO = new PagingVO<>(5,5);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailSearch(detailSearch);
		
		List<JobBoardVO> list = service.retrievejobApplicantList(pagingVO);
		model.addAttribute("list", list);
		
		List<JobBoardVO> list2 = service.retrievejobApplicantListEnd(pagingVO);
		model.addAttribute("list2", list2);
		
		model.addAttribute("paging", pagingVO);
		
		return "jsonView";
	}
	
	// 채용지원자 관리 목록 - 기업회원
	@RequestMapping("com/proApplicantList.do")
	public String proApplicant(
		Model model
		, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @SessionAttribute(value="authMember", required=false) EmemberVO authMember
		, @ModelAttribute("detailSearch") ProBoardVO detailSearch
	) {
		List<MenuVO> menuList = menuService.retrieveMenu("compage_com");
		model.addAttribute("menuList", menuList);
		
		proListToJson(currentPage, authMember, detailSearch, model);
		
		return "apply/applicant/proApplicantList";
	}
	
	@RequestMapping(value="com/proApplicantList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String proListToJson(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @SessionAttribute(value="authMember", required=false) EmemberVO authMember
		, @ModelAttribute("detailSearch") ProBoardVO detailSearch
		, Model model
	){
		String ememId = authMember.getEmemId();
		detailSearch.setEmemId(ememId);
		
		PagingVO<ProBoardVO> pagingVO = new PagingVO<>(5,5);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailSearch(detailSearch);
		
		List<ProBoardVO> list = service.retrieveproApplicantList(pagingVO);
		model.addAttribute("list", list);
		
		List<ProBoardVO> list2 = service.retrieveproApplicantListEnd(pagingVO);
		model.addAttribute("list2", list2);
		
		model.addAttribute("paging", pagingVO);
		
		return "jsonView";
	}
	
	@RequestMapping("com/jobApplicantManList.do")
	public String jobApplicantMan(
		@RequestParam("what") int jobSn
		, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute("detailSearch") JobBoardVO detailSearch
		, Model model
	) {
		PagingVO<JobBoardVO> pagingVO = jobManlistToJson(currentPage, jobSn, detailSearch);
		model.addAttribute("pagingVO", pagingVO);
		
		List<MenuVO> menuList = menuService.retrieveMenu("compage_com");
		model.addAttribute("menuList", menuList);
		
		JobBoardVO jobapp = service.retrieveJobApplicant(jobSn);
		model.addAttribute("jobapp", jobapp);
		
		return "apply/applicant/jobApplicantManList";
	}
	
	@ResponseBody
	@RequestMapping(value="com/jobApplicantManList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PagingVO<JobBoardVO> jobManlistToJson(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @RequestParam("what") int jobSn
		, @ModelAttribute("detailSearch") JobBoardVO detailSearch
	){
		detailSearch.setJobSn(jobSn);
		
		PagingVO<JobBoardVO> pagingVO = new PagingVO<>(5,5);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailSearch(detailSearch);
		
		service.retrievejobApplicantManList(pagingVO);
		
		return pagingVO;
	}
	
	@RequestMapping("com/proApplicantManList.do")
	public String proApplicantMan(
		@RequestParam("what") int probSn
		, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute("detailSearch") ProBoardVO detailSearch
		, Model model
	) {
		PagingVO<ProBoardVO> pagingVO = proManlistToJson(currentPage, probSn, detailSearch);
		model.addAttribute("pagingVO", pagingVO);
		
		List<MenuVO> menuList = menuService.retrieveMenu("compage_com");
		model.addAttribute("menuList", menuList);
		
		ProBoardVO proapp = service.retrieveProApplicant(probSn);
		model.addAttribute("proapp", proapp);
		
		return "apply/applicant/proApplicantManList";
	}
	
	@ResponseBody
	@RequestMapping(value="com/proApplicantManList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PagingVO<ProBoardVO> proManlistToJson(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @RequestParam("what") int probSn
		, @ModelAttribute("detailSearch") ProBoardVO detailSearch
	){
		detailSearch.setProbSn(probSn);
		
		PagingVO<ProBoardVO> pagingVO = new PagingVO<>(5,5);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailSearch(detailSearch);
		
		service.retrieveproApplicantManList(pagingVO);
		
		return pagingVO;
	}
	
	// 채용 지원자 정보 상세조회
	@RequestMapping("com/jobApplicantView.do")
	public String jobApplicantManView(
		@RequestParam("what") int reSn
		, Model model
	) {
		List<MenuVO> menuList = menuService.retrieveMenu("compage_com");
		model.addAttribute("menuList", menuList);
		
		JobBoardVO jobApplicantMan = service.retrieveJobApplicantMan(reSn);
		model.addAttribute("jobApplicantMan", jobApplicantMan);
		
		return "apply/applicant/jobApplicantManView";
	}
	
	// 프로젝트 지원자 정보 상세조회
	@RequestMapping("com/proApplicantView.do")
	public String proApplicantManView(
		@RequestParam("what") int reSn
		, Model model
	) {
		List<MenuVO> menuList = menuService.retrieveMenu("compage_com");
		model.addAttribute("menuList", menuList);
		
		ProBoardVO proApplicantMan = service.retrieveProApplicantMan(reSn);
		model.addAttribute("proApplicantMan", proApplicantMan);
		
		return "apply/applicant/proApplicantManView";
	}
	
}
