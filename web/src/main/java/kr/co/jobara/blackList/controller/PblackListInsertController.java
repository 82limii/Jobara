package kr.co.jobara.blackList.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blackList")
public class PblackListInsertController {
	@RequestMapping("admin/pblackListInsert.do")
	public String PblackListInsert() {
		return "admin/blackList/pblackListForm";
	}
}
