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
import kr.co.jobara.commons.vo.CommonCodeVO;
import kr.co.jobara.commons.vo.MenuVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.service.PmemberService;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.validate.hints.UpdateGroup;
import lombok.extern.slf4j.Slf4j;

/**
 * 장인슬
 * @author PC-17
 * 2022-02-28	
 */
@Controller
@Slf4j
public class PmemberUpdateController {
	
	@Inject
	private MenuService menuService;
	
	@Inject
	private CommonCodeService commonService;
	
	@Inject
	private PmemberService service;
	
	@Inject
	private LogService logService;
	
	@RequestMapping("/member/pmemberUpdate.do")
	public String form(@SessionAttribute("authMember") PmemberVO authMember, Model model){
		
//		select박스 호출코드 간결화, applicationContextProvider 클래스 생성후 applicationContextAware 인터페이스 구현
//		utils에 필요한 코드를 넣어준다. 안될경우 app을 빈에 등록해줌. 
//		Map<String, List<CommonCodeVo>> codeMap = CmmnUtil.getCodeMap("TEN","STA","AAA");
//		model.addAllAttributes(codeMap);
		
		//select박스 불러오기
		List<CommonCodeVO> tenList     = commonService.retrieveMiddleCodeList("TEN");   
		List<CommonCodeVO> staDivList  = commonService.retrieveMiddleCodeList("STA");   
		List<CommonCodeVO> stackList   = commonService.retrieveSmallCodeList("STA");    
		
		model.addAttribute("tenList",tenList);
		model.addAttribute("staDivList",staDivList);
		model.addAttribute("stackList",stackList);
		
		
		// left메뉴 불러오기
		List<MenuVO> menuList = menuService.retrieveMenu("mypage_per");
		model.addAttribute("menuList", menuList);
		
		PmemberVO pmember = service.retrievePmember(authMember.getPmemId());
		// model 을 scope 를 통해 뷰로 전달
		model.addAttribute("pmember", pmember);
		// 뷰를 선택, 이동(logical view name)
		return "member/pmemUpdateForm";
		
	}
	@RequestMapping(value="/member/pmemberUpdate.do", method=RequestMethod.POST)
	public String process(
			@Validated(UpdateGroup.class) @ModelAttribute("pmember") PmemberVO pmember
			, BindingResult errors
			, Model model
			, HttpServletRequest req
	) throws IOException {
		
		//select박스 불러오기
		List<CommonCodeVO> tenList     = commonService.retrieveMiddleCodeList("TEN");   
		List<CommonCodeVO> staDivList  = commonService.retrieveMiddleCodeList("STA");   
		List<CommonCodeVO> stackList   = commonService.retrieveSmallCodeList("STA");    
		
		model.addAttribute("tenList",tenList);
		model.addAttribute("staDivList",staDivList);
		model.addAttribute("stackList",stackList);
		
		// left메뉴 불러오기
		List<MenuVO> menuList = menuService.retrieveMenu("mypage_per");
		model.addAttribute("menuList", menuList);
		
		
		String viewName = null;
		if(!errors.hasErrors()) {
//		4-1. 통과
//			5. 수정(logic 사용)
			
			ServiceResult result = service.updatePmember(pmember);
			switch (result) {
			case FAIL:	
				log.info("aaaFAILaaa");
//				2) FAIL : memberForm로 
				model.addAttribute("message", "서버 오류");
				viewName = "member/pmemUpdateForm";
				break;
			default:
				log.info("aaaOKaaa");
//				3) OK : myPage로 - redirect
				// log
				String pmemId = pmember.getPmemId();
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+pmemId).build();
				logService.createLog(log);
				
				viewName = "redirect:/member/pmyPage.do";
				break;
			}
			
		}else {
//		4-2. 불통
//			memberForm으로 (기존 데이터, 검증 데이터)
			log.info("aaaElseaaa");
			viewName = "member/pmemUpdateForm";
		}
		return viewName;
	}
}
