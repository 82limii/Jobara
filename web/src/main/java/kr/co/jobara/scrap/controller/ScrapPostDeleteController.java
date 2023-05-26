package kr.co.jobara.scrap.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.scrap.service.ScrapPostService;


@Controller
@RequestMapping("/scrap")
public class ScrapPostDeleteController {

	@Inject
	private ScrapPostService service;
	
	@Inject
	private LogService logService;

	@RequestMapping("/scrapPostDelete.do")
	public String view(
			@RequestParam(value="what", required=true) int scrapSn
			, Model model
			, HttpServletRequest req
			, @SessionAttribute("authMember") PmemberVO auth
	) {
		String viewName = null;
		ServiceResult result = service.removeScrapPost(scrapSn);
		if(ServiceResult.OK == result) {
			// log
			String pmemId = auth.getPmemId();
			StringBuffer url = req.getRequestURL();
			LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+scrapSn).build();
			logService.createLog(log);
			
			viewName = "redirect:/scrap/scrapPost.do";
		} else {
			model.addAttribute("message", "서버오류");
			viewName = "redirect:/scrap/scrapPost.do";
		}
		
		return viewName;
	}
}
