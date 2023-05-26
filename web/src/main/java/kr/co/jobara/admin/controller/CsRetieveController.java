package kr.co.jobara.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cs")
public class CsRetieveController {
	
	@RequestMapping("admin/faqList.do")
	public String faqList() {
		return "admin/cs/faqList";
	}
	
	@RequestMapping("admin/helpsList.do")
	public String helpsList() {
		return "admin/cs/helpsList";
	}

	@RequestMapping("admin/helpsView.do")
	public String helpsView() {
		return "admin/cs/helpsView";
	}
	
	@RequestMapping("admin/noticeList.do")
	public String noticeList() {
		return "admin/cs/noticeList";
	}
	@RequestMapping("admin/noticeView.do")
	public String noticeView() {
		return "admin/cs/noticeView";
	}
}
