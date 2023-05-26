package kr.co.jobara.review.comCerti.controller;

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
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.review.comCerti.service.ComCertiService;
import kr.co.jobara.review.comCerti.vo.ComCertiVO;
import kr.co.jobara.validate.hints.InsertGroup;

@Controller
@RequestMapping("/review")
public class ComCertiInsertController {
	@Inject
	private MenuService menuService;
	@Inject
	private ComCertiService service;
	@Inject
	private LogService logService;
	
	@RequestMapping("/companyCertiInsert.do")
	public String form(
			@ModelAttribute("comCerti") ComCertiVO comCerti
			, Model model
			, @RequestParam(value="what", required=true) String ememId
			) {
		comCerti.setEmemId(ememId);
		List<MenuVO> menuList = menuService.retrieveMenu("compage_per");
		model.addAttribute("menuList", menuList);
		return "review/company/companyCertiForm";
	}
	@RequestMapping(value="/companyCertiInsert.do", method=RequestMethod.POST)
	public String process(
			@Validated(InsertGroup.class) @ModelAttribute("comCerti") ComCertiVO comCerti
			, @SessionAttribute("authMember") PmemberVO auth 
			, Errors errors
			, Model model
			, @RequestParam(value="what", required=true) String ememId
			, HttpServletRequest req
	) {
		boolean valid = !errors.hasErrors();
		String pmemId = auth.getPmemId();
		comCerti.setPmemId(pmemId);
		comCerti.setEmemId(ememId);
		String viewName = null;
		if(valid) {
			ServiceResult result = service.createComCerti(comCerti);
			if(ServiceResult.OK.equals(result)) {
				// log
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+comCerti.getCoceSn()).build();
				logService.createLog(log);
				
				viewName = "redirect:/review/reviewInsert.do?what=" + comCerti.getEmemId() + "&num=" + comCerti.getCoceSn();
			}else {
				model.addAttribute("message", "서버 오류, 조금 있다 다시 시도하세요.");
				viewName = "review/company/companyCertiForm";
			}
		}else {
			viewName = "review/company/companyCertiForm";
		}
		
		List<MenuVO> menuList = menuService.retrieveMenu("compage_per");
		model.addAttribute("menuList", menuList);
		
		return viewName;
	}
}
