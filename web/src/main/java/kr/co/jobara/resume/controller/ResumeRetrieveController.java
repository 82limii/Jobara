package kr.co.jobara.resume.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.MenuVO;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.resume.service.ResumeService;
import kr.co.jobara.resume.vo.ResumeVO;

@Controller
public class ResumeRetrieveController {
	@Inject
	private MenuService menuService; 
	
	@Inject
	private ResumeService service;

	@RequestMapping(value="/myPage/resumeList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String listToJson(
		@SessionAttribute("authMember") PmemberVO auth 
		, @ModelAttribute("resume") ResumeVO resume
		, Model model
	) {
		PagingVO<ResumeVO> paging = new PagingVO<>();
		String pmemId = auth.getPmemId();
		paging.setPmemId(pmemId);
		
		service.retrieveResumeList(paging);
		
		model.addAttribute("pagingVO", paging);
		
		return "jsonView";
	}
	
	@RequestMapping("/myPage/resumeList.do")
	public String list(
			@SessionAttribute("authMember") PmemberVO auth 
			, @ModelAttribute("resume") ResumeVO resume
			, Model model
			) {
		List<MenuVO> menuList = menuService.retrieveMenu("mypage_per");
		model.addAttribute("menuList", menuList);
		listToJson(auth, resume, model);
		return "resume/resumeList";
	}
	
	@RequestMapping("/myPage/resumeView.do")
	public String view(
			@RequestParam("what") int reSn
			, Model model
		) {
		ResumeVO resume = service.retrieveResume(reSn);
		model.addAttribute("resume", resume);
		return "resume/resumeView";
	}
	
	@RequestMapping("/resume/com/resumeView.do")
	public String viewCom(
			@RequestParam("what") int reSn
			, Model model
			) {
		ResumeVO resume = service.retrieveResume(reSn);
		model.addAttribute("resume", resume);
		return "resume/resumeView";
	}
}
