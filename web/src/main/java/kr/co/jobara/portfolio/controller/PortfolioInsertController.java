package kr.co.jobara.portfolio.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.commons.service.AttatchService;
import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.MenuVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.portfolio.service.PortfolioService;
import kr.co.jobara.portfolio.vo.PortfolioVO;
import kr.co.jobara.validate.hints.InsertGroup;

/**
 * 작성일 2022.03.10
 * @author 김승현
 * 포트폴리오(CRU)용 InsertController
 */
@Controller
@RequestMapping("/myPage")
public class PortfolioInsertController {
	
	@Inject
	private PortfolioService service;
	
	@Inject
	private AttatchService attatchService;
	
	@Inject
	private MenuService menuService;
	
	@Inject
	private LogService logService;
	
	@RequestMapping("portfolioInsert.do")
	public String list(Model model) {
		List<MenuVO> menuList = menuService.retrieveMenu("mypage_per");
		model.addAttribute("menuList", menuList);
		PortfolioVO portfolio = new PortfolioVO();
		model.addAttribute("portfolio", portfolio);
		return "portfolio/portfolioForm";
	}
	
	@RequestMapping(value="portfolioInsert.do", method=RequestMethod.POST)
	public String process(
			@Validated(InsertGroup.class) @ModelAttribute("portfolio") PortfolioVO portfolio
			, BindingResult errors
			, Model model
			, @SessionAttribute("authMember") PmemberVO auth 
			, HttpServletRequest req
	) {
		String viewName = null;
		String pmemId = auth.getPmemId();
		portfolio.setPmemId(pmemId);
		MultipartFile[] files = portfolio.getPortfolioFiles();
		String biztype = "portfolio";
		if(!errors.hasErrors()) {
			String attSn = attatchService.processAttatchFile(files, biztype);
			portfolio.setAttSn(attSn);
			ServiceResult result = service.createPortfolio(portfolio);
			if(ServiceResult.OK == result) {
				// log
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+portfolio.getPortSn()).build();
				logService.createLog(log);
				
				viewName = "redirect:/myPage/portfolioList.do";
			} else {
				model.addAttribute("message", "서버오류");
				viewName = "portfolio/portfolioForm";
			}
		} else {
			viewName = "portfolio/portfolioForm";
		}
		return viewName;
	}
}
