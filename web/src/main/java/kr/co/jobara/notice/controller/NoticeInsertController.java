package kr.co.jobara.notice.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.notice.service.NoticeService;
import kr.co.jobara.notice.vo.NoticeVO;

@Controller
@RequestMapping("/notice")
public class NoticeInsertController {
	
	@Inject
	private NoticeService service;
	
	@RequestMapping("admin/noticeInsert.do")
	public String noticeInsertForm(
		@ModelAttribute("notice") NoticeVO notice
		, Model model
	) {
		return "admin/cs/noticeForm";
	}
	
	@RequestMapping(value="admin/noticeInsert.do", method=RequestMethod.POST)
	public String noticeInsertProcess(
		@Validated @ModelAttribute("notice") NoticeVO notice
		, Errors errors
		, Model model
	) {
		String viewName = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.createNotice(notice);
			if(ServiceResult.OK.equals(result)) {
				viewName = "redirect:/notice/admin/noticeView.do?what="+notice.getNoticeSn();
			}else {
				model.addAttribute("message", "서버 오류, 조금 있다 다시 시도하세요.");
				viewName = "admin/cs/noticeForm";
			}
		}else {
			viewName = "admin/cs/noticeForm";
		}
		
		return viewName;
	}
}
