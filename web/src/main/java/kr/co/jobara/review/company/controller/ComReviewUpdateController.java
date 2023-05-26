package kr.co.jobara.review.company.controller;

import java.io.IOException;

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
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.review.company.service.ComReviewService;
import kr.co.jobara.review.company.vo.ComReviewVO;
import kr.co.jobara.validate.hints.UpdateGroup;

@Controller
@RequestMapping("/reviewUpdate.do")
public class ComReviewUpdateController {
	@Inject
	private ComReviewService service;
	
	@Inject
	private LogService logService;
	
	@RequestMapping
	public String form(Model model, @RequestParam(value="what", required=true) int reviewSn){
		
		ComReviewVO comReview = service.retrieveComReview(reviewSn);

		model.addAttribute("ComReview", comReview);

		return "review/company/companyReviewForm";

	}

	@RequestMapping(method=RequestMethod.POST)
	public String process(
			@Validated(UpdateGroup.class) @ModelAttribute("comReview") ComReviewVO comReview
			, Errors errors
			, Model model
			, HttpServletRequest req
			, @SessionAttribute("authMember") PmemberVO auth
	) throws IOException{
		
		String viewName = null;
		if (errors.hasErrors()) {
			ServiceResult result = service.modifyComReview(comReview);
			if (ServiceResult.OK == result) {
				// log
				String pmemId = auth.getPmemId();
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+comReview.getReviewSn()).build();
				logService.createLog(log);
				
				viewName = "redirect:review/company/companyReviewView.do?what=" + comReview.getReviewSn();
			} else {
				model.addAttribute("message", "서버 오류");
				viewName = "review/company/companyReviewForm";
			}
		} else {
			viewName = "review/company/companyReviewForm";
		}

		return viewName;
	}
}
