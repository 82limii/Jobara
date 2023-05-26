package kr.co.jobara.applicant.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.applicant.service.ApplicantService;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.vo.EmemberVO;

@Controller
@RequestMapping("/incumbent")
public class ApplicantDeleteController {
	
	@Inject
	private ApplicantService service;
	
	@Inject
	private LogService logService;
	
	@RequestMapping("com/jobIncumbentDelete.do")
	public String jobApplicantManDelete(
		@RequestParam(value="what", required=true) int appSn
		, Model model
		, HttpServletRequest req
	) {
		String viewName = null;
		ServiceResult result = service.removeApplicantMan(appSn);
		if(ServiceResult.OK.equals(result)) {
			// log
			EmemberVO auth = (EmemberVO) req.getSession().getAttribute("authMember");
			String ememId = auth.getEmemId();
			StringBuffer url = req.getRequestURL();
			LogVO log = LogVO.builder().ememId(ememId).logAdd(url.toString()+"?what="+appSn).build();
			logService.createLog(log);
			
			viewName = "redirect:/incumbent/com/jobApplicantList.do";
		} else {
			model.addAttribute("message", "서버오류");
			viewName = "redirect:/incumbent/com/jobApplicantList.do";
		}
		
		return viewName;
	}
	
	@RequestMapping("com/proIncumbentDelete.do")
	public String proApplicantManDelete(
		@RequestParam(value="what", required=true) int appSn
		, Model model
		, HttpServletRequest req
	) {
		String viewName = null;
		ServiceResult result = service.removeApplicantMan(appSn);
		if(ServiceResult.OK.equals(result)) {
			// log
			EmemberVO auth = (EmemberVO) req.getSession().getAttribute("authMember");
			String ememId = auth.getEmemId();
			StringBuffer url = req.getRequestURL();
			LogVO log = LogVO.builder().ememId(ememId).logAdd(url.toString()+"?what="+appSn).build();
			logService.createLog(log);

			viewName = "redirect:/incumbent/com/proApplicantList.do";
		} else {
			model.addAttribute("message", "서버오류");
			viewName = "redirect:/incumbent/com/proApplicantList.do";
		}
		
		return viewName;
	}
	
}
