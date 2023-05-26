package kr.co.jobara.login.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.WebApplicationContext;

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.listener.event.ELoginSuccessEvent;
import kr.co.jobara.listener.event.LoginSuccessEvent;
import kr.co.jobara.login.service.LoginService;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.member.vo.MemberVO;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.validate.hints.LoginGroup;


@Controller
@RequestMapping("/login")
public class LoginProcessController {

//	장인슬
	@Inject
	private WebApplicationContext context;
	
	@Inject
	private LoginService service;
	
	@Inject
	private LogService logService;
	
	@RequestMapping(value="logout.do", method=RequestMethod.POST)
	public String doPost(HttpSession session
			, HttpServletRequest req
			, @SessionAttribute("authMember") MemberVO auth
	){
		// log
		String id = auth.getId();
		StringBuffer url = req.getRequestURL();
		LogVO log = LogVO.builder().pmemId(id).logAdd(url.toString()+"?what="+id).build();
		logService.createLog(log);
		
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value="/ploginProcess.do", method=RequestMethod.POST)
	public String doPost(
			@Validated(LoginGroup.class) @ModelAttribute("inputData") PmemberVO inputData
			, BindingResult errors
			, HttpSession session
			, HttpServletRequest req
			) {
		
		String viewName = null;
		if(!errors.hasErrors()) {
			//		3-1. 통과
			//			4. 인증
						ServiceResult result = service.login(inputData);
						switch (result) {
						case NOTEXIST:
							//				4-1. 존재하지 않는 사용자
							//					message 공유
							//					loginForm 으로 이동.
							session.setAttribute("message", "존재하지 않는 아이디 입니다.");
							viewName = "redirect:/login/loginForm.do";
							
							break;
						case INVALIDPASSWORD:
							//				4-2. 비밀번호 오류
							//					message 공유
							//					loginForm 으로 이동.
							session.setAttribute("message", "비밀번호 오류 입니다.");
							viewName = "redirect:/login/loginForm.do";
							break;

						default:
							//				4-3. 인증 통과 (redirect)
							//					메인 페이지로 이동.
							context.publishEvent(new LoginSuccessEvent(inputData));
							
							// log
							String pmemId = inputData.getPmemId();
							StringBuffer url = req.getRequestURL();
							LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+pmemId).build();
							logService.createLog(log);
							
							viewName = "redirect:/";
							session.setAttribute("authMember", inputData);
							break;
						}
				}else {
			//		3-2. 불통
			//			message 공유
			//			loginForm 으로 이동.
					session.setAttribute("message", "모두 입력하여 주십시오.");
					viewName = "redirect:/login/loginForm.do";
				}
		

		return viewName; 
	}	
	
	@RequestMapping(value="/eloginProcess.do", method=RequestMethod.POST)
	public String doPost(
			@Validated(LoginGroup.class) @ModelAttribute("inputData") EmemberVO inputData
			, BindingResult errors
			, HttpSession session
			, HttpServletRequest req
			) {
		
		String viewName = null;
		if(!errors.hasErrors()) {
			//		3-1. 통과
			//			4. 인증
			ServiceResult result = service.elogin(inputData);
			switch (result) {
			case NOTEXIST:
				//				4-1. 존재하지 않는 사용자
				//					message 공유
				//					loginForm 으로 이동.
				session.setAttribute("message", "존재하지 않는 아이디 입니다.");
				viewName = "redirect:/login/loginForm.do";
				
				break;
			case INVALIDPASSWORD:
				//				4-2. 비밀번호 오류
				//					message 공유
				//					loginForm 으로 이동.
				session.setAttribute("message", "비밀번호 오류 입니다.");
				viewName = "redirect:/login/loginForm.do";
				break;
				
			default:
				//				4-3. 인증 통과 (redirect)
				//					메인 페이지로 이동.
				context.publishEvent(new ELoginSuccessEvent(inputData));
				
				// log
				String ememId = inputData.getEmemId();
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().ememId(ememId).logAdd(url.toString()+"?what="+ememId).build();
				logService.createLog(log);
				
				viewName = "redirect:/indexCom.do";
				session.setAttribute("authMember", inputData);
				break;
			}
		}else {
			//		3-2. 불통
			//			message 공유
			//			loginForm 으로 이동.
			session.setAttribute("message", "모두 입력하여 주십시오.");
			viewName = "redirect:/login/loginForm.do";
		}
		return viewName; 
	}	
}
