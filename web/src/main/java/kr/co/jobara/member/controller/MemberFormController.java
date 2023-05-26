package kr.co.jobara.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberFormController {
	@RequestMapping("/member/memberForm.do")
	public String member() {
		return "member/memberForm";
	}
}
