package kr.co.jobara.pay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pay")
public class PayFormController {
	@RequestMapping("payForm.do")
	public String payForm() {
		return "pay/payForm";
	}
	@RequestMapping("com/payForm.do")
	public String payFormCom() {
		return "pay/payForm";
	}
}
