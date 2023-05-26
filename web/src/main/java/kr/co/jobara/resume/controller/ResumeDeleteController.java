package kr.co.jobara.resume.controller;

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
import kr.co.jobara.resume.service.ResumeService;
import kr.co.jobara.resume.vo.ResumeVO;

/**
 * @author 이상림
 * 최초작성 2022.03.28
 */
@Controller
@RequestMapping("/myPage")
public class ResumeDeleteController {
	@Inject
	private ResumeService service;
	
	@Inject
	private LogService logService;
	
	@RequestMapping("resumeDelete.do")
	public String process(
			@SessionAttribute("authMember") PmemberVO auth
			, @RequestParam("what") int reSn
			, Model model
			, HttpServletRequest req
	) {
		String viewName = null;
		
		ResumeVO resume = service.retrieveResume(reSn);
		String pmemId = auth.getPmemId();
		
		if(!pmemId.equals(resume.getPmemId())) {
			model.addAttribute("message", "일치하지 않는 사용자입니다.");
			viewName = "redirect:/index.do";
		} else {
			ServiceResult result = service.removeResume(reSn);
			if(ServiceResult.OK == result) {
				// 로그
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+reSn).build();
				logService.createLog(log);
				
				viewName = "redirect:/myPage/resumeList.do";
			} else {
				model.addAttribute("message", "서버 오류");
				viewName = "redirect:/myPage/resumeView.do?what="+reSn;
			}
		}
		
		
		return viewName;
	}
}
