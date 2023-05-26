package kr.co.jobara.helps.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.helps.service.HelpsService;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.member.vo.PmemberVO;

@Controller
@RequestMapping("/helps")
public class HelpsDeleteController {
	
	@Inject
	private HelpsService service;
	
	@Inject
	private LogService logService;
	
	@RequestMapping("helpsDelete.do")
	public String helpsDelete(
		@RequestParam(value="what", required=true) int helpsSn
		, Model model
		, HttpServletRequest req
		, @SessionAttribute("authMember") PmemberVO auth
	) {
		String viewName = null;
		ServiceResult result = service.removeHelps(helpsSn);
		if(ServiceResult.OK.equals(result)) {
			// log
			String pmemId = auth.getPmemId();
			StringBuffer url = req.getRequestURL();
			LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+helpsSn).build();
			logService.createLog(log);
			
			viewName = "redirect:/helps/helpsList.do";
		} else {
			model.addAttribute("message", "서버오류");
			viewName = "redirect:/helps/helpsList.do";
		}
		
		return viewName;
	}
	
	@RequestMapping("com/helpsDelete.do")
	public String helpsDeleteCom(
		@RequestParam(value="what", required=true) int helpsSn
		, Model model
		, HttpServletRequest req
		,@SessionAttribute("authMember") EmemberVO auth
	) {
		String viewName = null;
		ServiceResult result = service.removeHelps(helpsSn);
		if(ServiceResult.OK.equals(result)) {
			// log
			String ememId = auth.getEmemId();
			StringBuffer url = req.getRequestURL();
			LogVO log = LogVO.builder().ememId(ememId).logAdd(url.toString()+"?what="+helpsSn).build();
			logService.createLog(log);
			
			viewName = "redirect:/helps/com/helpsList.do";
		} else {
			model.addAttribute("message", "서버오류");
			viewName = "redirect:/helps/com/helpsList.do";
		}
		
		return viewName;
	}
	
}
