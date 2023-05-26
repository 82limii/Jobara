package kr.co.jobara.pms.project.controller;

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
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.pms.project.service.ProTaskService;
import kr.co.jobara.pms.project.vo.ProTaskVO;
import kr.co.jobara.validate.hints.UpdateGroup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ProjectTaskUpdateController {

	@Inject
	private ProTaskService service;
	
	@Inject
	private LogService logService;
	
	@RequestMapping("/project/projTaskUpdate.do")
	public String form(
			@RequestParam(value="jobara", required=false) int protSn
			, @RequestParam("what") int proSn
			, Model model
			) {
		ProTaskVO task = service.retrieveTaskBoard(protSn);
		List<ProTaskVO> memList = service.retrieveProMemList();
		log.info("memList.size() : " + memList.size());
		model.addAttribute("task",task);
		model.addAttribute("memList",memList);
		return "project/projectTaskForm";
	}
	
	@RequestMapping(value="/project/projTaskUpdate.do", method=RequestMethod.POST)
	public String process(
			@Validated(UpdateGroup.class) @ModelAttribute("task") ProTaskVO task
			,Errors errors
			, @RequestParam("what") int proSn
			,Model model
			, HttpServletRequest req
			, @SessionAttribute("authMember") PmemberVO auth
			) {
		String viewName = null;
		if (errors.hasErrors()) {
			ServiceResult result = service.modifyTaskBoard(task);
			if(ServiceResult.OK == result) {
				// log
				String pmemId = auth.getPmemId();
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+task.getProtSn()).build();
				logService.createLog(log);
				
				viewName = "redirect:/project/projTaskView.do?jobara=" + task.getProtSn() +"&what=" + proSn;
			} else {
				model.addAttribute("message", "서버오류");
				viewName = "project/projectTaskForm";
			}
		} else {
			viewName = "project/projectTaskForm";
		}
		
		
		
		
		
		return viewName;
	}
	
	
	
	
}












