package kr.co.jobara.review.inter.controller;

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
import kr.co.jobara.review.inter.service.InterService;
import kr.co.jobara.review.inter.vo.InterVO;
import kr.co.jobara.validate.hints.InsertGroup;

@Controller
@RequestMapping("/review")
public class InterReviewInsertController {
	@Inject
	private MenuService menuService;
	
	@Inject
	private InterService service;
	
	@Inject
	private LogService logService;
	
	@RequestMapping("interReviewInsert.do")
	public String form(
			@ModelAttribute("inter") InterVO inter
			, Model model
			, @RequestParam(value="what", required=true) String ememId
			) {
		inter.setEmemId(ememId);
		List<MenuVO> menuList = menuService.retrieveMenu("compage_per");
		model.addAttribute("menuList", menuList);
		return "review/inter/interReviewForm";
	}
	
	@RequestMapping(value="/interReviewInsert.do", method=RequestMethod.POST)
	public String process(
			@Validated(InsertGroup.class) @ModelAttribute("inter") InterVO inter
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
		inter.setPmemId(pmemId);
		inter.setEmemId(ememId);
		inter.setCertiSn(certiSn);
		if(valid) {
			ServiceResult result = service.createInter(inter);
			if(ServiceResult.OK.equals(result)) {
				// log
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+inter.getInterSn()).build();
				logService.createLog(log);
				
				viewName = "redirect:/review/cointReviewList.do?what=" + inter.getEmemId();
			}else {
				model.addAttribute("message", "서버 오류, 조금 있다 다시 시도하세요.");
				viewName = "review/inter/interReviewForm";
			}
		}else {
			viewName = "review/inter/interReviewForm";
		}
		
		List<MenuVO> menuList = menuService.retrieveMenu("compage_per");
		model.addAttribute("menuList", menuList);
		
		return viewName;
	}
}
