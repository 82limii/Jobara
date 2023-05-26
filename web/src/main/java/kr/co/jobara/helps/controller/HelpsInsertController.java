package kr.co.jobara.helps.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.commons.service.AttatchService;
import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.MenuVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.helps.service.HelpsService;
import kr.co.jobara.helps.vo.HelpsVO;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.member.vo.PmemberVO;

@Controller
@RequestMapping("/helps")
public class HelpsInsertController {
	@Inject
	private MenuService menuService;
	
	@Inject
	private HelpsService service;
	
	@Inject
	private AttatchService attatchService;
	
	@Inject
	private LogService logService;
	
	@RequestMapping("helpsInsert.do")
	public String helpsInsertForm(
		@ModelAttribute("helps") HelpsVO helps
		, Model model
	) {
		List<MenuVO> menuList = menuService.retrieveMenu("cscenter_per");
		model.addAttribute("menuList", menuList);
		
		return "helps/helpsForm";
	}
	
	@RequestMapping(value="helpsInsert.do", method=RequestMethod.POST)
	public String helpsInsertProcess(
		@SessionAttribute(value="authMember", required=false) PmemberVO authMember
		, @Validated @ModelAttribute("helps") HelpsVO helps
		, Errors errors
		, Model model
		, HttpServletRequest req
	) {
		String viewName = null;
		String pmemId = authMember.getPmemId();
		helps.setHelpsId(pmemId);
		MultipartFile[] files = helps.getHelpsFiles();
		String biztype = "helps";
		boolean valid = !errors.hasErrors();
		if(valid) {
			String attSn = attatchService.processAttatchFile(files, biztype);
			helps.setAttSn(attSn);
			ServiceResult result = service.createHelps(helps);
			if(ServiceResult.OK.equals(result)) {
				// log
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+helps.getHelpsSn()).build();
				logService.createLog(log);
				
				viewName = "redirect:/helps/helpsView.do?what="+helps.getHelpsSn();
			}else {
				model.addAttribute("message", "서버 오류, 조금 있다 다시 시도하세요.");
				viewName = "helps/helpsForm";
			}
		}else {
			viewName = "helps/helpsForm";
		}
		
		
		
		return viewName;
	}
	
	@RequestMapping("com/helpsInsert.do")
	public String comHelpsInsertForm(
		@ModelAttribute("helps") HelpsVO helps
		, Model model
	) {
		List<MenuVO> menuList = menuService.retrieveMenu("cscenter_com");
		model.addAttribute("menuList", menuList);
		
		return "helps/helpsForm";
	}
	
	@RequestMapping(value="com/helpsInsert.do", method=RequestMethod.POST)
	public String comHelpsInsertProcess(
		@SessionAttribute(value="authMember", required=false) EmemberVO authMember
		, @Validated @ModelAttribute("helps") HelpsVO helps
		, Errors errors
		, Model model
		, HttpServletRequest req
	) {
		String viewName = null;
		String ememId = authMember.getEmemId();
		helps.setHelpsId(ememId);
		MultipartFile[] files = helps.getHelpsFiles();
		String biztype = "jobboard";
		boolean valid = !errors.hasErrors();
		if(valid) {
			String attSn = attatchService.processAttatchFile(files, biztype);
			helps.setAttSn(attSn);
			ServiceResult result = service.createHelps(helps);
			if(ServiceResult.OK.equals(result)) {
				// log
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().ememId(ememId).logAdd(url.toString()+"?what="+helps.getHelcoSn()).build();
				logService.createLog(log);
				
				viewName = "redirect:/helps/com/helpsView.do?what="+helps.getHelpsSn();
			}else {
				model.addAttribute("message", "서버 오류, 조금 있다 다시 시도하세요.");
				viewName = "helps/helpsForm";
			}
		}else {
			viewName = "helps/helpsForm";
		}
		return viewName;
	}
	
	@RequestMapping("admin/helpsInsert.do")
	public String helpsInsertFormAdmin(
		@ModelAttribute("helps") HelpsVO helps
		, Model model
	) {
		helps.setHelpsId("ajava");
		return "admin/cs/helpsView";
	}
	
	@RequestMapping(value="admin/helpsInsert.do", method=RequestMethod.POST)
	public String helpsInsertProcessAdmin(
		@RequestParam("what") int helpsSn2
		, @Validated @ModelAttribute("helpsAns") HelpsVO helps
		, Errors errors
		, Model model
	) {
		helps.setHelpsSn2(helpsSn2);
		helps.setHelpsId("ajava");
		String viewName = null;
		
		// 첨부파일
		MultipartFile[] files = helps.getHelpsFiles();
		String biztype = "helps";
		
		//원본정보 찾기
		HelpsVO helpsOri = service.retrieveHelps(helpsSn2);
		model.addAttribute("helps", helpsOri);
		
		// 에러처리
		boolean valid = !errors.hasErrors();
		if(valid) {
			String attSn = attatchService.processAttatchFile(files, biztype);
			helps.setAttSn(attSn);
			ServiceResult result = service.createHelps(helps);
			if(ServiceResult.OK.equals(result)) {
				viewName = "redirect:/helps/admin/helpsView.do?what="+helps.getHelpsSn();
			}else {
				model.addAttribute("message", "서버 오류, 조금 있다 다시 시도하세요.");
				viewName = "admin/cs/helpsView";
			}
		}else {
			viewName = "admin/cs/helpsView";
		}
		return viewName;
	}
	
}
