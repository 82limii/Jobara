package kr.co.jobara.contact.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.MenuVO;
import kr.co.jobara.contact.service.ContactService;
import kr.co.jobara.contact.vo.ContactVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.validate.hints.InsertGroup;

@Controller
public class ContactInsertController {
	@Inject
	private MenuService menuService;
	
	@Inject
	private ContactService service;
	
	@Inject
	private LogService logService;
	
	@RequestMapping("/contact/com/contactInsert.do")
	public String contactInsertForm(
		@ModelAttribute("contact") ContactVO contact
		, Model model
	) {
		List<MenuVO> menuList = menuService.retrieveMenu("compage_com");
		model.addAttribute("menuList", menuList);
		return "contact/contactForm";
	}
	
	
	@RequestMapping(value="/contact/com/contactInsert.do", method=RequestMethod.POST)
	public String contactInsertProcess(
		@Validated(InsertGroup.class) @ModelAttribute("contact") ContactVO contact
		, Errors errors
		, Model model
		, @SessionAttribute("authMember") EmemberVO auth
		, HttpServletRequest req
	) {
		String ememId = auth.getEmemId();
		contact.setEmemId(ememId);
		String viewName = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.createContact(contact);
			if(ServiceResult.OK.equals(result)) {
				// log
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().ememId(ememId).logAdd(url.toString()+"?what="+contact.getContacSn()).build();
				logService.createLog(log);
				
				viewName = "redirect:/contact/com/contactView.do?what="+contact.getContacSn();
			}else {
				model.addAttribute("message", "서버 오류, 조금 있다 다시 시도하세요.");
				viewName = "contact/contactForm";
			}
		}else {
			viewName = "contact/contactForm";
		}
		
		List<MenuVO> menuList = menuService.retrieveMenu("compage_com");
		model.addAttribute("menuList", menuList);
		
		return viewName;
	}
}
