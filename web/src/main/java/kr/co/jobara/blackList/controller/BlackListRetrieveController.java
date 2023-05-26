package kr.co.jobara.blackList.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blackList")
public class BlackListRetrieveController {
	
	@RequestMapping("admin/pblackList.do")
	public String PblackList() {
		return "admin/blackList/pblackList";
	}
	
	@RequestMapping("admin/eblackList.do")
	public String EblackList() {
		return "admin/blackList/eblackList";
	}
}
