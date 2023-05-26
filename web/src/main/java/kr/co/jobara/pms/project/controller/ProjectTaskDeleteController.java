package kr.co.jobara.pms.project.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.pms.project.service.ProTaskService;
import kr.co.jobara.pms.project.vo.ProTaskVO;

@Controller
@RequestMapping("/project/projTaskDelete.do")
public class ProjectTaskDeleteController {
	
	@Inject
	private ProTaskService proTaskService;
	
	@Inject
	private LogService logService;
	
	@RequestMapping(method=RequestMethod.POST)
	public String process(
			@ModelAttribute("task") ProTaskVO task
			, Errors errors
			, @RequestParam("what") int proSn
			, Model model
			, HttpServletRequest req
			, @SessionAttribute("authMember") PmemberVO auth
			) {
		String viewName = null;
		int protSn = task.getProtSn();
			if(!errors.hasErrors()) {
			ServiceResult result = proTaskService.removeTaskBoard(protSn);
			if(ServiceResult.OK== result) {
				// log
				String pmemId = auth.getPmemId();
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+task.getProtSn()).build();
				logService.createLog(log);
				
				viewName = "redirect:/project/projTaskList.do?what=" + proSn;
			} else {
				model.addAttribute("message", "서버 오류");
				viewName = "project/projectTaskView";
			}
		} else {
			viewName = "project/projectTaskView";
		}
		
		return viewName;
	}
	
}
