package kr.co.jobara.blackList.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blackList")
public class EblackListInsertController {
	@RequestMapping("admin/eblackListInsert.do")
	public String EblackListInsert() {
		return "admin/blackList/eblackListForm";
	}
}
