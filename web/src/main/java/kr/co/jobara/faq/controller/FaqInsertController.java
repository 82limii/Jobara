package kr.co.jobara.faq.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.faq.service.FaqService;
import kr.co.jobara.faq.vo.FaqVO;

@Controller
@RequestMapping("/faq/admin/faqInsert.do")
public class FaqInsertController {
	
	@Inject
	private FaqService service;
	
	@RequestMapping
	public String faqInsertForm(
		@ModelAttribute("faq") FaqVO faq
		, Model model
	) {
		return "admin/cs/faqForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String faqInsertProcess(
		@Validated @ModelAttribute("faq") FaqVO faq
		, Errors errors
		, Model model
	) {
		String viewName = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.createFaq(faq);
			if(ServiceResult.OK.equals(result)) {
				viewName = "redirect:/faq/admin/faqList.do";
			}else {
				model.addAttribute("message", "서버 오류, 조금 있다 다시 시도하세요.");
				viewName = "admin/cs/faqForm";
			}
		}else {
			viewName = "admin/cs/faqForm";
		}
		
		return viewName;
	}
}
