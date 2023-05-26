package kr.co.jobara.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pay")
public class PayRetrieveController {

	@RequestMapping("admin/payList.do")
	public String payList() {
		return "admin/payList";
	}
}
