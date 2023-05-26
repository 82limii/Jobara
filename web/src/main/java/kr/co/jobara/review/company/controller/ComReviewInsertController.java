package kr.co.jobara.review.company.controller;

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
import kr.co.jobara.review.company.service.ComReviewService;
import kr.co.jobara.review.company.vo.ComReviewVO;
import kr.co.jobara.validate.hints.InsertGroup;

/**
 * 작성일 2022.02.23
 * @author 김승현
 * 기업리뷰(CRUD)용 insertCoontroller
 */

@Controller
@RequestMapping("/review")
public class ComReviewInsertController {
	@Inject
	private MenuService menuService;
	@Inject
	private ComReviewService service;
	@Inject
	private LogService logService;
	
	@RequestMapping("/reviewInsert.do")
	public String form(
			@ModelAttribute("comReview") ComReviewVO comReview
			,Model model
			, @RequestParam(value="what", required=true) String ememId
			) {
		comReview.setEmemId(ememId);
		List<MenuVO> menuList = menuService.retrieveMenu("compage_per");
		model.addAttribute("menuList", menuList);
		return "review/company/companyReviewForm";
	}
	
	@RequestMapping(value="/reviewInsert.do", method=RequestMethod.POST)
	public String process(
			@Validated(InsertGroup.class) @ModelAttribute("comReview") ComReviewVO comReview
			, Errors errors
			, Model model
			, @RequestParam(value="what", required=true) String ememId
			, @RequestParam(value="num", required=true) int coceSn
			, @SessionAttribute("authMember") PmemberVO auth 
			, HttpServletRequest req
	) {
		boolean valid = !errors.hasErrors();
		String viewName = null;
		String pmemId = auth.getPmemId();
		comReview.setPmemId(pmemId);
		comReview.setEmemId(ememId);
		comReview.setCoceSn(coceSn);
		if(valid) {
			ServiceResult result = service.createComReview(comReview);
			if(ServiceResult.OK.equals(result)) {
				// log 
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+comReview.getReviewSn()).build();
				logService.createLog(log);
				
				viewName = "redirect:/review/reviewList.do?what=" + comReview.getEmemId();
			}else {
				model.addAttribute("message", "서버 오류, 조금 있다 다시 시도하세요.");
				viewName = "review/company/companyReviewForm";
			}
		}else {
			viewName = "review/company/companyReviewForm";
		}
		
		List<MenuVO> menuList = menuService.retrieveMenu("compage_per");
		model.addAttribute("menuList", menuList);
		
		return viewName;
	}
}
