package kr.co.jobara.scrap.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.scrap.service.ScrapPostService;
import kr.co.jobara.scrap.vo.ScrapPostVO;
import kr.co.jobara.validate.hints.InsertGroup;

@Controller
@RequestMapping("/scrap")
public class ScrapCompanyInsertController {
	
	@Inject
	private ScrapPostService service;
	
	@Inject
	private LogService logService;
		
		@RequestMapping(value="scrapEnterInsert.do")
		public String process(
				@Validated(InsertGroup.class) @ModelAttribute("scrapPost") ScrapPostVO scrapPost
				, BindingResult errors
				, Model model
				, @SessionAttribute("authMember") PmemberVO auth 
				, @RequestParam(value="what", required=true) String ememId
				, HttpServletRequest req
		) {
			String viewName = null;
			String pmemId = auth.getPmemId();
			scrapPost.setPmemId(pmemId);
			scrapPost.setEmemId(ememId);
			if(!errors.hasErrors()) {
				ServiceResult result = service.createScrapPost(scrapPost);
				if(ServiceResult.OK == result) {
					// log
					StringBuffer url = req.getRequestURL();
					LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+scrapPost.getScrapSn()).build();
					logService.createLog(log);
					
					viewName = "redirect:/scrap/scrapEnter.do";
				} else {
					model.addAttribute("message", "서버오류");
					viewName = "info/infoView";  // 기업 페이지 변경
				}
			} else {
				viewName = "info/infoView";
			}
			return viewName;
		}
}
