package kr.co.jobara.notice.controller;

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
import kr.co.jobara.notice.service.NoticeService;
import kr.co.jobara.notice.vo.NoticeVO;
import kr.co.jobara.validate.hints.UpdateGroup;

@Controller
@RequestMapping("/notice/admin/noticeUpdate.do")
public class NoticeUpdateController {
	
	@Inject
	private NoticeService service;
	
	@RequestMapping
	public String noticeUpdateForm(
		@RequestParam(value="what", required=true) int noticeSn
		, Model model
	) {
		NoticeVO notice = service.retrieveNotice(noticeSn);
		model.addAttribute("notice", notice);
		return "admin/cs/noticeEdit";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String noticeUpdateProcess(
		@Validated(UpdateGroup.class) @ModelAttribute("notice") NoticeVO notice
		, Errors errors
		, Model model
	) {
		String viewName = null;
		if(errors.hasErrors()) {
			viewName = "admin/cs/noticeEdit";
		}else {
			ServiceResult result = service.modifyNotice(notice);
			switch (result) {
			case FAIL:
				viewName = "admin/cs/noticeEdit";
				model.addAttribute("message", "서버 오류");
				break;

			default:
				viewName = "redirect:/notice/admin/noticeView.do?what="+notice.getNoticeSn();
				break;
			}
		}
		
		return viewName;
	}
}
