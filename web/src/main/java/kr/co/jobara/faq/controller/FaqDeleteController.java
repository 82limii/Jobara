package kr.co.jobara.faq.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.faq.service.FaqService;

@Controller
@RequestMapping("/faq")
public class FaqDeleteController {
	
	@Inject
	private FaqService service;
	
	@RequestMapping("admin/faqDelete.do")
	public String faqDelete(
		@RequestParam(value="what", required=true) int faqSn
		, Model model
	) {
		String viewName = null;
		ServiceResult result = service.removeFaq(faqSn);
		if(ServiceResult.OK.equals(result)) {
			viewName = "redirect:/faq/admin/faqList.do";
		} else {
			model.addAttribute("message", "서버오류");
			viewName = "redirect:/faq/admin/faqList.do";
		}
		
		return viewName;
	}
	
}
