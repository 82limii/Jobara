package kr.co.jobara.pms.project.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import kr.co.jobara.validate.hints.InsertGroup;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 최인수
 * PMS 작업등록 로직
 */

@Controller
@Slf4j
public class ProjectTaskInsertController {

	@Inject
	private ProTaskService service;
	
	@Inject
	private LogService logService;
	
	@RequestMapping(value="/project/projTaskInsert.do", method=RequestMethod.GET)
	public String form(
			@ModelAttribute("task") ProTaskVO task
			, Model model
			) {
		
		List<ProTaskVO> memList = service.retrieveProMemList();
		model.addAttribute("memList", memList);
		return "project/projectTaskForm";
	}
	
	@ModelAttribute("task")
	public ProTaskVO task() {
		return new ProTaskVO();
	}
	
	// 중요 - BindingResult 매개변수는  무조건! 전송받을 객체 다음에 위치해야 함
	@RequestMapping(value="/project/projTaskInsert.do", method=RequestMethod.POST)
	public String taskProcess(
			@Validated(InsertGroup.class) @ModelAttribute("task") ProTaskVO task
			, BindingResult errors
			, @SessionAttribute(value="authMember", required=false) PmemberVO pMem
			, @RequestParam("what") int proSn
			, Model model
			, HttpServletRequest req
			) {
		String pmemId = pMem.getPmemId();
		task.setPmemId(pmemId);
		task.setProSn(proSn);
		String viewName = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.createTaskBoard(task);
			if(ServiceResult.OK == result) {
				// log
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+task.getProtSn()).build();
				logService.createLog(log);
				
				viewName = "redirect:/project/projTaskList.do?what="+proSn;
			} else {
				model.addAttribute("message", "서버 오류");
				viewName = "project/projectTaskForm";
			}
		} else {
			viewName = "project/projectTaskForm";
		}
		return viewName;
	}
	
	
	
}













