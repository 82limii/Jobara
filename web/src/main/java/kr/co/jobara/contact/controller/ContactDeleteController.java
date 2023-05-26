package kr.co.jobara.contact.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.contact.service.ContactService;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.vo.EmemberVO;

@Controller
@RequestMapping("/contact")
public class ContactDeleteController {
	
	@Inject
	private ContactService service;
	
	@Inject
	private LogService logService;
	
	@RequestMapping("com/contactDelete.do")
	public String contactDelete(
		@RequestParam(value="what", required=true) int contacSn
		, Model model
		, HttpServletRequest req
		, @SessionAttribute("authMember") EmemberVO auth
	) {
		String viewName = null;
		ServiceResult result = service.removeContact(contacSn);
		if(ServiceResult.OK.equals(result)) {
			// log
			String ememId = auth.getEmemId();
			StringBuffer url = req.getRequestURL();
			LogVO log = LogVO.builder().ememId(ememId).logAdd(url.toString()+"?what="+contacSn).build();
			logService.createLog(log);
			
			viewName = "redirect:/contact/com/contactList.do";
		} else {
			model.addAttribute("message", "서버오류");
			viewName = "redirect:/contact/com/contactList.do";
		}
		
		return viewName;
	}
	
}
