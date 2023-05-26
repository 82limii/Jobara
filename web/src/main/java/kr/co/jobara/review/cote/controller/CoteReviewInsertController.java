package kr.co.jobara.review.cote.controller;

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
import kr.co.jobara.review.cote.service.CoteService;
import kr.co.jobara.review.cote.vo.CoteVO;
import kr.co.jobara.validate.hints.InsertGroup;

@Controller
@RequestMapping("/review")
public class CoteReviewInsertController {
	@Inject
	private MenuService menuService;
	@Inject
	private CoteService service;
	@Inject
	private LogService logService;
	
	@RequestMapping("/coteReviewInsert.do")
	public String form(
			@ModelAttribute("cote") CoteVO cote
			, Model model
			, @RequestParam(value="what", required=true) String ememId
			) {
		cote.setEmemId(ememId);
		List<MenuVO> menuList = menuService.retrieveMenu("compage_per");
		model.addAttribute("menuList", menuList);
		return "review/cote/coteReviewForm";
	}
	
	@RequestMapping(value="/coteReviewInsert.do", method=RequestMethod.POST)
	public String process(
			@Validated(InsertGroup.class) @ModelAttribute("cote") CoteVO cote
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
		cote.setPmemId(pmemId);
		cote.setEmemId(ememId);
		cote.setCertiSn(certiSn);
		if(valid) {
			ServiceResult result = service.createCote(cote);
			if(ServiceResult.OK.equals(result)) {
				// log
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+cote.getCoteSn()).build();
				logService.createLog(log);
				
				viewName = "redirect:/review/cointReviewList.do?what=" + cote.getEmemId();
			}else {
				model.addAttribute("message", "서버 오류, 조금 있다 다시 시도하세요.");
				viewName = "review/cote/coteReviewForm";
			}
		}else {
			viewName = "review/cote/coteReviewForm";
		}
		
		List<MenuVO> menuList = menuService.retrieveMenu("compage_per");
		model.addAttribute("menuList", menuList);
		
		return viewName;
	}
	
}
