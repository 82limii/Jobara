package kr.co.jobara.pms.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
public class ProjectHistoryController {

	@RequestMapping("projHistory.do")
	public String list() {
		return "project/projectHisList";
	}
	@RequestMapping("com/projHistory.do")
	public String listCom() {
		return "project/projectHisList";
	}
}
