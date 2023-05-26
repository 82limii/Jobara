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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.MenuVO;
import kr.co.jobara.contact.service.ContactService;
import kr.co.jobara.contact.vo.ContactVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.validate.hints.UpdateGroup;

@Controller
@RequestMapping("contact/com/contactUpdate.do")
public class ContactUpdateController {
	
	@Inject
	private MenuService menuService;
	
	@Inject
	private ContactService service;
	
	@Inject
	private LogService logService;
	
	// CONTACT 수정
	@RequestMapping
	public String contactUpdateForm(
		@RequestParam(value="what", required=true) int contacSn
		, Model model
		, @SessionAttribute("authMember") EmemberVO auth
	) {
		String viewName = null;
		String ememId = auth.getEmemId();
		
		List<MenuVO> menuList = menuService.retrieveMenu("compage_com");
		model.addAttribute("menuList", menuList);
		
		ContactVO contact = service.retrieveContact(contacSn);
		if(!contact.getEmemId().equals(ememId)) {
			viewName = "redirect:/index.do";
		}
		model.addAttribute("contact", contact);
		viewName = "contact/contactForm";
		return viewName;
	}
	
	// CONTACT 수정
	@RequestMapping(method=RequestMethod.POST)
	public String contactUpdateProcess(
		@Validated(UpdateGroup.class) @ModelAttribute("contact") ContactVO contact
		, Errors errors
		, Model model
		, HttpServletRequest req
		, @SessionAttribute("authMember") EmemberVO auth
	) {
		String viewName = null;
		if(errors.hasErrors()) {
			viewName = "contact/contactForm";
		}else {
			ServiceResult result = service.modifyContact(contact);
			switch (result) {
			case FAIL:
				viewName = "contact/contactForm";
				model.addAttribute("message", "서버 오류");
				break;

			default:
				// log
				String ememId = auth.getEmemId();
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().ememId(ememId).logAdd(url.toString()+"?what="+contact.getContacSn()).build();
				logService.createLog(log);
				viewName = "redirect:/contact/com/contactView.do?what="+contact.getContacSn();
				break;
			}
		}
		List<MenuVO> menuList = menuService.retrieveMenu("compage_com");
		model.addAttribute("menuList", menuList);
		
		return viewName;
	}
	
	
	
}
