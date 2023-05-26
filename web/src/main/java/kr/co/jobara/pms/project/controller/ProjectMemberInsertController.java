package kr.co.jobara.pms.project.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.vo.MemberVO;
import kr.co.jobara.pms.project.service.ProMemberService;
import kr.co.jobara.pms.project.vo.ProMemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ProjectMemberInsertController {
	
	@Inject
	private ProMemberService service;
	
	@Inject
	private LogService logService;
	
	@RequestMapping(value="/project/projMemInsert.do", method=RequestMethod.POST)
	public String process(
			@ModelAttribute("proMember") ProMemberVO proMember
			, @RequestParam String[] checkboxName
			, BindingResult errors
			, Model model
			, HttpServletRequest req
			, @SessionAttribute("authMember") MemberVO auth
			) {
		String viewName = null;
		
		log.info("checkboxName : " + checkboxName);
		
		if(!errors.hasErrors()) {
				
				ServiceResult result = service.insertMemberListAdd(proMember, checkboxName);
			if(ServiceResult.OK == result) {
				// log
				String id = auth.getId();
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().ememId(id).logAdd(url.toString()).build();
				logService.createLog(log);
				
				viewName = "redirect:/project/com/projMemList.do";
			} else {
				model.addAttribute("message", "서버오류");
			}
		} else {
			model.addAttribute("message", "서버오류");
		}
		return viewName;
	}
}
