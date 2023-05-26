package kr.co.jobara.notice.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.notice.service.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeDeleteController {

	@Inject
	private NoticeService service;
	
	@RequestMapping("admin/noticeDelete.do")
	public String noticeDelete(
		@RequestParam(value="what", required=true) int noticeSn
		, Model model
	) {
		String viewName = null;
		ServiceResult result = service.removeNotice(noticeSn);
		if(ServiceResult.OK.equals(result)) {
			viewName = "redirect:/notice/admin/noticeList.do";
		} else {
			model.addAttribute("message", "서버오류");
			viewName = "redirect:/notice/admin/noticeList.do";
		}
		
		return viewName;
	}
	
	
}
