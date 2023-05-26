package kr.co.jobara.admin.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.AdminVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.listener.event.ALoginSuccessEvent;
import kr.co.jobara.login.service.LoginService;
import kr.co.jobara.validate.hints.LoginGroup;

@Controller
public class AdminLoginController {

//	장인슬
	@Inject
	private WebApplicationContext context;
	
	@Inject
	private LoginService service;
	
	@Inject
	private LogService logService;
	
	@RequestMapping("/admin/logout.do")
	public String doPost(HttpSession session){
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/admin/login.do")
	public String login(){
		return "admin/login";
	}
	
	@RequestMapping(value="/admin/loginProcess.do", method=RequestMethod.POST)
	public String doPost(
			@Validated(LoginGroup.class) @ModelAttribute("inputData") AdminVO inputData
			, BindingResult errors
			, HttpSession session
			) {
		
		String viewName = null;
		if(!errors.hasErrors()) {
			//		3-1. 통과
			//			4. 인증
						ServiceResult result = service.alogin(inputData);
						switch (result) {
						case NOTEXIST:
							//				4-1. 존재하지 않는 사용자
							//					message 공유
							//					loginForm 으로 이동.
							session.setAttribute("message", "존재하지 않는 아이디 입니다.");
							viewName = "redirect:/admin/login.do";
							
							break;
						case INVALIDPASSWORD:
							//				4-2. 비밀번호 오류
							//					message 공유
							//					loginForm 으로 이동.
							session.setAttribute("message", "비밀번호 오류 입니다.");
							viewName = "redirect:/admin/login.do";
							break;

						default:
							//				4-3. 인증 통과 (redirect)
							//					메인 페이지로 이동.
							context.publishEvent(new ALoginSuccessEvent(inputData));
							viewName = "redirect:/indexAdmin.do";
							session.setAttribute("authMember", inputData);
							break;
						}
				}else {
			//		3-2. 불통
			//			message 공유
			//			loginForm 으로 이동.
					session.setAttribute("message", "모두 입력하여 주십시오.");
					viewName = "redirect:/admin/login.do";
				}
		

		return viewName; 
	}		
	
}
