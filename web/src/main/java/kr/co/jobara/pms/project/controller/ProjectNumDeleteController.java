package kr.co.jobara.pms.project.controller;

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
import kr.co.jobara.contact.vo.ContactVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.vo.MemberVO;

@Controller
public class ProjectNumDeleteController {
	
	@Inject
	private ContactService service;
	
	@Inject
	private LogService logService;
	
	@RequestMapping("/project/com/projPhoneNumDelete.do")
	public String contactDelete(
		@RequestParam(value="what", required=true) int contacSn
		, Model model
		, @SessionAttribute("authMember") MemberVO auth
		, HttpServletRequest req
	) {
		ContactVO contact = service.retrieveContact(contacSn);
		String viewName = null;
		ServiceResult result = service.removeContact(contacSn);
		if(ServiceResult.OK.equals(result)) {
			// log
			String id = auth.getId();
			StringBuffer url = req.getRequestURL();
			LogVO log = LogVO.builder().ememId(id).logAdd(url.toString()+"?what="+contacSn).build();
			logService.createLog(log);
			
			viewName = "redirect:/project/com/projPhoneNum.do?what="+contact.getProSn();
		} else {
			model.addAttribute("message", "서버오류");
			viewName = "redirect:/project/com/projPhoneNum.do?what="+contact.getProSn();
		}
		
		return viewName;
	}
	
}
