package kr.co.jobara.faq.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.faq.service.FaqService;
import kr.co.jobara.faq.vo.FaqVO;
import kr.co.jobara.validate.hints.UpdateGroup;

@Controller
@RequestMapping("/faq/admin/faqUpdate.do")
public class FaqUpdateController {
	
	@Inject
	private FaqService service;
	
	@RequestMapping
	public String faqUpdateForm(
		@RequestParam(value="what", required=true) int faqSn
		, Model model
	) {
		FaqVO faq = service.retrieveFaq(faqSn);
		model.addAttribute("faq", faq);
		return "admin/cs/faqEdit";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String faqUpdateProcess(
		@Validated(UpdateGroup.class) @ModelAttribute("faq") FaqVO faq
		, Errors errors
		, Model model
	) {
		String viewName = null;
		if(errors.hasErrors()) {
			viewName = "admin/cs/faqEdit";
		}else {
			ServiceResult result = service.modifyFaq(faq);
			switch (result) {
			case FAIL:
				viewName = "admin/cs/faqEdit";
				model.addAttribute("message", "서버 오류");
				break;

			default:
				viewName = "redirect:/faq/admin/faqList.do";
				break;
			}
		}
		return viewName;
	}
}
