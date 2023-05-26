package kr.co.jobara.review.certi.controller;

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
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.review.certi.service.CertiService;
import kr.co.jobara.review.certi.vo.CertiVO;
import kr.co.jobara.validate.hints.InsertGroup;

/**
 * 작성일 2022.03.04
 * @author 김승현
 * 리뷰인증(CRUD)용 InsertController
 */

@Controller
@RequestMapping("/review")
public class CertiInsertController {
	@Inject
	private MenuService menuService;
	
	@Inject
	private CertiService service;
	
	@Inject
	private AttatchService attatchService;
	
	@Inject
	private LogService logService;
	
	@RequestMapping("/certiInsert.do")
	public String form(
			@ModelAttribute("certi") CertiVO certi
			, Model model
			, @RequestParam(value="what", required=true) String ememId
			) {
		certi.setEmemId(ememId);
		List<MenuVO> menuList = menuService.retrieveMenu("compage_per");
		model.addAttribute("menuList", menuList);
		return "review/certi/certiForm";
	}
	
	@RequestMapping(value="certiInsert.do", method=RequestMethod.POST)
	public String InsertProcess(
		@Validated(InsertGroup.class) @ModelAttribute("certi") CertiVO certi
		, @SessionAttribute("authMember") PmemberVO auth 
		, Errors errors
		, Model model
		, @RequestParam(value="what", required=true) String ememId
		, HttpServletRequest req
	) {
		boolean valid = !errors.hasErrors();
		String viewName = null;
		String pmemId = auth.getPmemId();
		MultipartFile[] files = certi.getCertiFiles();
		String biztype = "certi";
		certi.setPmemId(pmemId);
		certi.setEmemId(ememId);
		String diviCd = certi.getCertiDiviCd();
		if(valid) {
			if(!errors.hasErrors()) {
				String attSn = attatchService.processAttatchFile(files, biztype);
				certi.setCertiFile(attSn);
				
				// log
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+certi.getCertiSn()).build();
				logService.createLog(log);
				
				ServiceResult result = service.createCerti(certi);
				if(ServiceResult.OK.equals(result)) {
					switch (diviCd) {
					case "REV01":
						viewName = "redirect:/review/coteReviewInsert.do?what=" + certi.getEmemId() + "&num=" + certi.getCertiSn();
						break;
					case "REV02":
						viewName = "redirect:/review/interReviewInsert.do?what=" + certi.getEmemId() + "&num=" + certi.getCertiSn();
						break;
					case "REV03":
						viewName = "redirect:/successInt/successIntInsert.do?what=" + certi.getEmemId() + "&num=" + certi.getCertiSn();
						break;
	
					default:
						break;
					}
				}else {
					model.addAttribute("message", "서버 오류, 조금 있다 다시 시도하세요.");
					viewName = "review/certi/certiForm";
				}
			}else {
				viewName = "review/certi/certiForm";
			}
			return viewName;
		}
		
		List<MenuVO> menuList = menuService.retrieveMenu("compage_per");
		model.addAttribute("menuList", menuList);
		
		return viewName;
	}
}
