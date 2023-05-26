package kr.co.jobara.fitme.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.MenuVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.fitme.service.ComReccomendService;
import kr.co.jobara.fitme.vo.RecommendVO;
import kr.co.jobara.fitme.vo.SurveyVO;
import kr.co.jobara.member.vo.PmemberVO;

@Controller
@RequestMapping("/fitme")
public class FitMeRetrieveController {
	@Inject
	private MenuService menuService;
	
	@Inject
	private ComReccomendService service;
	
	@Inject
	private LogService logService;
	
	// 설문조사 페이지 이동
	@RequestMapping("survey.do")
	public String survey(
			Model model
			, @SessionAttribute("authMember") PmemberVO auth
	) {
		String pmemId = auth.getPmemId();
		SurveyVO survey = service.retrieveTodaysSurvey(pmemId);
		model.addAttribute("survey", survey);
		
		List<MenuVO> menuList = menuService.retrieveMenu("mypage_per");
		model.addAttribute("menuList", menuList);
		return "fitme/survey";
	}
	
	// 설문조사 등록 프로세스
	@RequestMapping("/surveyInsert.do")
	public String surveyProcess(
			@SessionAttribute("authMember") PmemberVO auth
			, @ModelAttribute("survey") SurveyVO survey
			, BindingResult errors
			, Model model
			, HttpServletRequest req
	) {
		String viewName = null;
		String pmemId = auth.getPmemId();
		survey.setPmemId(pmemId);
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.createSurvey(survey);
			if(ServiceResult.OK == result) {
				// log
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+survey.getSurSn()).build();
				logService.createLog(log);
				
				viewName = "redirect:/fitme/survey.do";
			} else {
				model.addAttribute("message", "서버 오류");
			}
		} else {
			viewName = "fitme/survey";
		}
		return viewName;
	}

	// 기업추천 페이지 이동
	@RequestMapping("recco.do")
	public String reccomendComp(
			Model model
			, @SessionAttribute("authMember") PmemberVO auth
	) {
		String pmemId = auth.getPmemId();
		RecommendVO recommend = service.retrieveRecommend(pmemId);
		model.addAttribute("recommend", recommend);
		
		List<MenuVO> menuList = menuService.retrieveMenu("mypage_per");
		model.addAttribute("menuList", menuList);
		return "fitme/reccomend";
	}
}
