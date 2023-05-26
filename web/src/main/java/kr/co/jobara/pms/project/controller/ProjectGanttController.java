package kr.co.jobara.pms.project.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.pms.project.service.ProTaskService;
import kr.co.jobara.pms.project.vo.GanttVO;

@Controller
@RequestMapping("/project")
public class ProjectGanttController {
	
	@Inject
	private ProTaskService service;
	
	@RequestMapping(value="projGantt.do", method=RequestMethod.GET)
	public String getList() {
		return "project/projectGantt";
	}
	
	@ResponseBody
	@RequestMapping(value="projGantt.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<GanttVO> chart(
		@SessionAttribute("authMember") PmemberVO auth
		, Model model
	) {
		String pmemId = auth.getPmemId();
		List<GanttVO> list = service.retrieveTaskList(pmemId);
		return list;
	}
	
	@RequestMapping(value="com/projGantt.do", method=RequestMethod.GET)
	public String getListCom() {
		return "project/projectGantt";
	}
	
	@ResponseBody
	@RequestMapping(value="com/projGantt.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<GanttVO> chartCom(
		@SessionAttribute("authMember") EmemberVO auth
		, Model model
	) {
		String ememId = auth.getEmemId();
		List<GanttVO> list = service.retrieveTaskListCom(ememId);
		return list;
	}
}
