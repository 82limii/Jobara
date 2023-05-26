package kr.co.jobara.applicant.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.applicant.service.ApplicantService;
import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.MenuVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.incumbent.vo.IncumbentVO;
import kr.co.jobara.jobboard.vo.JobBoardVO;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.proboard.vo.ProBoardVO;
import kr.co.jobara.validate.hints.InsertGroup;

@Controller
@RequestMapping("/applicant")
public class ApplicantInsertController {
	
	@Inject
	private MenuService menuService;
	
	@Inject
	private ApplicantService service;
	
	@Inject
	private LogService logService;
	
	@RequestMapping("com/jobApplicantInsert.do")
	public String jobApplicantInsertForm(
		@ModelAttribute("incumbent") IncumbentVO incumbent
		, Model model
	) {
		List<MenuVO> menuList = menuService.retrieveMenu("compage_com");
		model.addAttribute("menuList", menuList);
		
		return "apply/applicant/jobApplicantForm";
	}
	
	@RequestMapping(value="com/jobApplicantInsert.do", method=RequestMethod.POST)
	public String jobApplicantInsertProcess(
		@SessionAttribute(value="authMember", required=false) EmemberVO authMember
		, @RequestParam(value="what", required=true) int reSn
		, @Validated(InsertGroup.class) @ModelAttribute("incumbent") IncumbentVO incumbent
		, Errors errors
		, Model model
		, HttpServletRequest req
	) {
		boolean valid = !errors.hasErrors();
		String viewName = null;
		String ememId = authMember.getEmemId();
		incumbent.setEmemId(ememId);
		JobBoardVO jobApplicantMan = service.retrieveJobApplicantMan(reSn);
		int appSn = jobApplicantMan.getAppSn();
		incumbent.setPmemId(jobApplicantMan.getPmemId());
		if(valid) {
			ServiceResult result = service.createIncumbent(incumbent);
			if(ServiceResult.OK.equals(result)) {
				service.removeApplicantMan(appSn);
				
				// log
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().ememId(ememId).logAdd(url.toString()+"?what="+appSn).build();
				logService.createLog(log);
				
				viewName = "redirect:/incumbent/com/incumbentView.do?what="+incumbent.getEmpSn();
			}else {
				model.addAttribute("message", "서버 오류, 조금 있다 다시 시도하세요.");
				viewName = "apply/applicant/jobApplicantForm";
			}
		}else {
			viewName = "apply/applicant/jobApplicantForm";
		}
		
		return viewName;
	}
	
	@RequestMapping("com/proApplicantInsert.do")
	public String proApplicantInsertForm(
		@ModelAttribute("incumbent") IncumbentVO incumbent
		, Model model
	) {
		List<MenuVO> menuList = menuService.retrieveMenu("compage_com");
		model.addAttribute("menuList", menuList);
		
		return "apply/applicant/proApplicantForm";
	}
	
	@RequestMapping(value="com/proApplicantInsert.do", method=RequestMethod.POST)
	public String proApplicantInsertProcess(
		@SessionAttribute(value="authMember", required=false) EmemberVO authMember
		, @RequestParam(value="what", required=true) int reSn
		, @Validated(InsertGroup.class) @ModelAttribute("incumbent") IncumbentVO incumbent
		, Errors errors
		, Model model
		, HttpServletRequest req
	) {
		boolean valid = !errors.hasErrors();
		String viewName = null;
		String ememId = authMember.getEmemId();
		incumbent.setEmemId(ememId);
		ProBoardVO proApplicantMan = service.retrieveProApplicantMan(reSn);
		int appSn = proApplicantMan.getAppSn();
		incumbent.setPmemId(proApplicantMan.getPmemId());
		if(valid) {
			ServiceResult result = service.createIncumbent(incumbent);
			if(ServiceResult.OK.equals(result)) {
				service.removeApplicantMan(appSn);
				
				// log
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().ememId(ememId).logAdd(url.toString()+"?what="+appSn).build();
				logService.createLog(log);
				
				viewName = "redirect:/incumbent/com/incumbentView.do?what="+incumbent.getEmpSn();
			}else {
				model.addAttribute("message", "서버 오류, 조금 있다 다시 시도하세요.");
				viewName = "apply/applicant/proApplicantForm";
			}
		}else {
			viewName = "apply/applicant/proApplicantForm";
		}
		
		return viewName;
	}
	
}
