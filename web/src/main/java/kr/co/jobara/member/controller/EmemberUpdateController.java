package kr.co.jobara.member.controller;

import java.io.IOException;
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

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.commons.service.CommonCodeService;
import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.MenuVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.service.EmemberService;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.validate.hints.UpdateGroup;
import lombok.extern.slf4j.Slf4j;

/**
 * 장인슬
 * @author PC-17
 * 2022-03-02
 */
@Controller
@Slf4j
@RequestMapping("/member")
public class EmemberUpdateController {
	
	@Inject
	public EmemberService service;
	
	@Inject
	private CommonCodeService commonService;
	
	@Inject
	private MenuService menuService;
	
	@Inject
	private LogService logService;
	
	@RequestMapping("com/ememberUpdate.do")
	public String form(@SessionAttribute("authMember") EmemberVO authMember, Model model) {
		//left 메뉴 가져오기
		List<MenuVO> menuList = menuService.retrieveMenu("compage_com");
		model.addAttribute("menuList", menuList);
		
		EmemberVO emember = service.retrieveEmember(authMember.getEmemId());
		model.addAttribute("emember", emember);
		
		return "member/ememUpdateForm";
		
	}
	@RequestMapping(value="com/ememberUpdate.do", method=RequestMethod.POST)
	public String process(
			@Validated(UpdateGroup.class) @ModelAttribute("emember") EmemberVO emember
			, BindingResult errors
			, Model model
			, HttpServletRequest req
	) throws IOException {
		
		
		//left 메뉴 가져오기
		List<MenuVO> menuList = menuService.retrieveMenu("compage_com");
		model.addAttribute("menuList", menuList);
		
		
		String viewName = null;
		if(!errors.hasErrors()) {
//		4-1. 통과
//			5. 수정(logic 사용)
			
			ServiceResult result = service.updateEmember(emember);
			switch (result) {
			case FAIL:	
				log.info("aaaFAILaaa");
//				2) FAIL : memberForm로 
				model.addAttribute("message", "서버 오류");
				viewName = "member/ememUpdateForm";
				break;
			default:
				log.info("aaaOKaaa");
//				3) OK : myPage로 - redirect
				// log
				String ememId = emember.getEmemId();
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().ememId(ememId).logAdd(url.toString()+"?what="+ememId).build();
				logService.createLog(log);
				
				viewName = "redirect:emyPage.do";
				break;
			}
			
		}else {
//		4-2. 불통
//			memberForm으로 (기존 데이터, 검증 데이터)
			log.info("aaaElseaaa");
			viewName = "member/ememUpdateForm";
		}
		return viewName;
	}

	
}
