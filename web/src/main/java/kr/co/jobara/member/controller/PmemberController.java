package kr.co.jobara.member.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.service.PmemberService;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.validate.hints.InsertGroup;
import lombok.extern.slf4j.Slf4j;

/**
 * 장인슬
 * @author PC-17
 *
 */
@Controller
@Slf4j
public class PmemberController {
	
//	@Inject
//	private CommonCodeService commonService;
	
	@Inject
	private PmemberService service;
	
	@Inject
	private LogService logService;
	
	@ModelAttribute("command")
	public String command() {
		return "INSERT";
	}
	
	@ModelAttribute("pmember")
	public PmemberVO pmember() {
		return new PmemberVO();
	}
	
	@RequestMapping("/member/pmemberForm.do")
	public String pmemberForm(Model model) {
		return "member/pMemberForm";
	}
	
	@RequestMapping(value="/member/pmemberForm.do", method=RequestMethod.POST)
	public String process(@Validated(InsertGroup.class) @ModelAttribute("pmember") PmemberVO pmember
						, BindingResult errors
						, Model model
						, HttpServletRequest req
	) throws IOException{
		log.info("pmember : " + pmember.toString());
		
		
		String viewName = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.createPmember(pmember);
			switch (result) {
			case OK:
				// log
				String pmemId = pmember.getPmemId();
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+pmemId).build();
				logService.createLog(log);
				
				viewName = "redirect:/";
				break;
			case PKDUPLICATED:
				viewName = "member/pMemberForm";
				break;
			default:
				viewName = "member/pMemberForm";
				model.addAttribute("message", "서버 오류로 인해 등록되지 않았습니다. 잠시후 다시 시도해 주세요.");
				break;
			}
		}else {
			viewName = "member/pMemberForm";
		}
		return viewName;		
	}
	
	
}
