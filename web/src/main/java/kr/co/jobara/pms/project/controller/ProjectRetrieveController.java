package kr.co.jobara.pms.project.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.pms.project.service.ProCreateService;
import kr.co.jobara.pms.project.vo.CalendarVO;

@Controller
@RequestMapping("/project")
public class ProjectRetrieveController {
	
	@Inject
	private ProCreateService service;
	
	@RequestMapping(value="projHome.do",method=RequestMethod.GET)
	public String getList() {
		return "project/projectHome";
	}
	
	@ResponseBody
	@RequestMapping(value="projHome.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<CalendarVO> list(
		@SessionAttribute("authMember") PmemberVO auth
		, Model model
	) {
		String pmemId = auth.getPmemId();
		List<CalendarVO> list = service.retrieveProjectList(pmemId);
		model.addAttribute("list", list);
		
		return list;
	}
	
	@RequestMapping(value="com/projHome.do",method=RequestMethod.GET)
	public String getListCom() {
		return "project/projectHome";
	}
	
	@ResponseBody
	@RequestMapping(value="com/projHome.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<CalendarVO> listCom(
		@SessionAttribute("authMember") EmemberVO auth
		, Model model
	) {
		String ememId = auth.getEmemId();
		List<CalendarVO> list = service.retrieveProjectListCom(ememId);
		model.addAttribute("list", list);
		return list; 
	}
	
				
}
