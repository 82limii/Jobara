package kr.co.jobara.commons.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.jobara.commons.service.MainService;
import kr.co.jobara.commons.vo.MainVO;
import kr.co.jobara.jobboard.vo.JobBoardVO;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.member.vo.MemberVO;
import kr.co.jobara.pms.project.service.ProCreateService;
import kr.co.jobara.pms.project.vo.CalendarVO;
import kr.co.jobara.proboard.vo.ProBoardVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IndexController {
	
	@Inject
	private ProCreateService service;
	
	@Inject
	private MainService mainService;
	
	@RequestMapping("/index.do")
	public String index(
			HttpSession session
			, Model model
	) {
		List<JobBoardVO> jobList = mainService.mainJobBoard();
		model.addAttribute("jobList", jobList);
		
		List<ProBoardVO> proList = mainService.mainProBoard();
		model.addAttribute("proList", proList);
		
		List<MainVO> reviewList1 = mainService.mainReview1(); 
		List<MainVO> reviewList2 = mainService.mainReview2();
				
		model.addAttribute("reviewList1", reviewList1);
		model.addAttribute("reviewList2", reviewList2);
		
		String viewName = null;
		MemberVO auth = (MemberVO) session.getAttribute("authMember");
		if (auth == null) {
			viewName = "index";
		} else {
			log.info("auth : " + auth);
			String userType = auth.getUserType();
			if("Pmember".equals(userType)) {
				viewName = "index";
			} else if("Emember".equals(userType)) {
				viewName = "redirect:/indexCom.do";
			}			
		}
		return viewName;
//		return "index";
	}
	
	@RequestMapping(value="/indexCom.do", method=RequestMethod.GET)
	public String getList() {
		return "indexCom";
	}
	
	@ResponseBody
	@RequestMapping(value="/indexCom.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<CalendarVO> indexCom(
		 @SessionAttribute("authMember") EmemberVO auth
		, Model model
	) {
		String ememId = auth.getEmemId();
		List<CalendarVO> list = service.retrieveProjectListCom(ememId);
		return list;
	}
	
}
