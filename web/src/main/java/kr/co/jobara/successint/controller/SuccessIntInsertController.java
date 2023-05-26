package kr.co.jobara.successint.controller;

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
import kr.co.jobara.successint.service.SuccessIntService;
import kr.co.jobara.successint.vo.SuccessIntVO;
import kr.co.jobara.validate.hints.InsertGroup;

@Controller
@RequestMapping("/successInt")
public class SuccessIntInsertController {
	@Inject
	private MenuService menuService;
	
	@Inject
	private SuccessIntService service;
	
	@Inject
	private LogService logService;
	
	@RequestMapping("/successIntInsert.do")
	public String list(
			@ModelAttribute("successInt") SuccessIntVO successInt
			, Model model
			, @RequestParam(value="what", required=true) String ememId
			) {
		successInt.setEmemId(ememId);
		List<MenuVO> menuList = menuService.retrieveMenu("compage_per");
		model.addAttribute("menuList", menuList);
		return "successInt/successIntForm";
	}
	
	@RequestMapping(value="successIntInsert.do", method=RequestMethod.POST)
	public String InsertProcess(
		@Validated(InsertGroup.class) @ModelAttribute("successInt") SuccessIntVO successInt
		, Errors errors
		, Model model
		, @RequestParam(value="what", required=true) String ememId
		, @RequestParam(value="num", required=true) int certiSn
		, @SessionAttribute("authMember") PmemberVO auth 
		, HttpServletRequest req
	) {
		boolean valid = !errors.hasErrors();
		String viewName = null;
		String pmemId = auth.getPmemId();
		successInt.setPmemId(pmemId);
		successInt.setEmemId(ememId);
		successInt.setCertiSn(certiSn);
		if(valid) {
			ServiceResult result = service.createSuccessInt(successInt);
			if(ServiceResult.OK.equals(result)) {
				// log
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+successInt.getSinSn()).build();
				logService.createLog(log);
				
				viewName = "redirect:/successInt/successIntList.do?what="+successInt.getSinSn();
			}else {
				model.addAttribute("message", "서버 오류, 조금 있다 다시 시도하세요.");
				viewName = "successInt/successIntForm";
			}
		}else {
			viewName = "successInt/successIntForm";
		}
		
		List<MenuVO> menuList = menuService.retrieveMenu("compage_per");
		model.addAttribute("menuList", menuList);
		
		return viewName;
	}
}
