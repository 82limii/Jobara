package kr.co.jobara.member.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.service.EmemberService;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.validate.hints.InsertGroup;
import lombok.extern.slf4j.Slf4j;

/**
 * 장인슬
 * @author PC-17
 */
@Controller
@Slf4j
public class EmemberController {
	
	@Inject
	private EmemberService service;
	
	@Inject
	private LogService logService;
	
	@ModelAttribute("command")
	public String command() {
		return "INSERT";
	}
	
	@ModelAttribute("emember")
	public EmemberVO emember() {
		return new EmemberVO();
	}
		
	//회원가입 뷰
	@RequestMapping("/member/ememberForm.do")
	public String ememberForm() {
		return "member/eMemberForm";
	}
	
	@RequestMapping(value="/member/ememberForm.do", method=RequestMethod.POST)
	public String process(@Validated(InsertGroup.class) @ModelAttribute("emember") EmemberVO emember
						, BindingResult errors
						, Model model
						, HttpServletRequest req
		) throws IOException{
		
		
		
		String viewName = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.createEmember(emember);
			switch (result) {
			case OK:
				// log
				String ememId = emember.getEmemId();
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().ememId(ememId).logAdd(url.toString()+"?what="+ememId).build();
				logService.createLog(log);
				
				viewName = "login/loginForm";
				break;
			case PKDUPLICATED:
				viewName = "member/eMemberForm";
				model.addAttribute("message", "이미 사용중인 아이디 입니다.");
				break;
			default:
				viewName = "member/eMemberForm";
				model.addAttribute("message", "서버 오류로 인해 등록되지 않았습니다. 잠시후 다시 시도해 주세요.");
				break;
			}
		}else {
			viewName = "member/eMemberForm";
			log.info(errors.toString());
		}
		return viewName;		
	}	
}
