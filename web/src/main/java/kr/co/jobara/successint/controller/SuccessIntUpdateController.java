package kr.co.jobara.successint.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.successint.service.SuccessIntService;

@Controller
@RequestMapping("/successInt")
public class SuccessIntUpdateController {
	
	@Inject
	private MenuService menuService;
	
	@Inject
	private SuccessIntService service;
	
	@Inject
	private LogService logService;
	
	@RequestMapping("successIntDelete.do")
	public String UpdateForm(
		@RequestParam(value="what", required=true) int sinSn
		, Model model
		, HttpServletRequest req
		, @SessionAttribute("authMember") PmemberVO auth
	) {
//		SuccessIntVO successInt = service.retrieveSuccessInt(sinSn);
		
		// log
		String pmemId = auth.getPmemId();
		StringBuffer url = req.getRequestURL();
		LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+sinSn).build();
		logService.createLog(log);
		
		service.removeSuccessInt(sinSn);
//		model.addAttribute("successInt", successInt);
		return "redirect:/successInt/successIntList.do";
	}
	
//	@RequestMapping(value="successIntDelete.do", method=RequestMethod.POST)
//	public String UpdateProcess(
//		@Validated(UpdateGroup.class) @ModelAttribute("successInt") SuccessIntVO successInt
//		, Errors errors
//		, Model model
//	) {
//		String viewName = null;
//		if(errors.hasErrors()) {
//			viewName = "successInt/successIntList";
//		}else {
//			ServiceResult result = service.modifySuccessInt(successInt);
//			switch (result) {
//			case FAIL:
//				viewName = "successInt/successIntList";
//				model.addAttribute("message", "서버 오류");
//				break;
//
//			default:
//				viewName = "redirect:/successInt/successIntList.do";
//				break;
//			}
//		}
//		List<MenuVO> menuList = menuService.retrieveMenu("compage_per");
//		model.addAttribute("menuList", menuList);
//		
//		return viewName;
//	}
	
	
}
