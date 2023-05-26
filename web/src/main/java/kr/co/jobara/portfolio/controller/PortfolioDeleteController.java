package kr.co.jobara.portfolio.controller;

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
import kr.co.jobara.portfolio.service.PortfolioService;


@Controller
@RequestMapping("/myPage")
public class PortfolioDeleteController {

	@Inject
	private PortfolioService service;
	
	@Inject
	private LogService logService;

	@RequestMapping("/portfolioDelete.do")
	public String view(
			@RequestParam(value="what", required=true) int portSn
			, Model model
			, @SessionAttribute("authMember") PmemberVO auth
			, HttpServletRequest req
	) {
		String viewName = null;
		ServiceResult result = service.removePortfolio(portSn);
		if(ServiceResult.OK == result) {
			// log 
			String pmemId = auth.getPmemId();
			StringBuffer url = req.getRequestURL();
			LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+portSn).build();
			logService.createLog(log);
			
			viewName = "redirect:/myPage/portfolioList.do";
		} else {
			model.addAttribute("message", "서버오류");
			viewName = "redirect:/myPage/portfolioList.do";
		}
		
		return viewName;
	}
}
