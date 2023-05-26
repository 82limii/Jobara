package kr.co.jobara.proboard.controller;

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
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.proboard.service.ProBoardService;
import kr.co.jobara.proboard.vo.ProBoardVO;
import kr.co.jobara.validate.hints.InsertGroup;

@Controller
@RequestMapping("/proboard")
public class ProBoardInsertController {
	
	@Inject
	private ProBoardService service;
	
	@Inject
	private AttatchService attatchService;
	
	@Inject
	private LogService logService;
	
	@RequestMapping("com/proBoardInsert.do")
	public String form(
		Model model	
	) {
		ProBoardVO proBoard = new ProBoardVO();
		model.addAttribute("proBoard", proBoard);
		
		return "proboard/proboardForm";
	}
	
	@RequestMapping(value="com/proBoardInsert.do", method=RequestMethod.POST)
	public String process(
		@Validated(InsertGroup.class) @ModelAttribute("proBoard") ProBoardVO proBoard
		, BindingResult errors
		, Model model
		, @SessionAttribute("authMember") EmemberVO auth
		, HttpServletRequest req
	) {
		String viewName = null;
		String ememId = auth.getEmemId();
		
		proBoard.setEmemId(ememId);
		MultipartFile[] files = proBoard.getProbFiles();
		String biztype = "proboard";
		if(!errors.hasErrors()) {
			String attSn = attatchService.processAttatchFile(files, biztype);
			proBoard.setAttSn(attSn);
			ServiceResult result = service.createProBoard(proBoard);
			if(ServiceResult.OK == result) {
				// 로그
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().ememId(ememId).logAdd(url.toString()+"?what="+proBoard.getProbSn()).build();
				logService.createLog(log);
				
				viewName = "redirect:/proboard/com/proBoardView.do?what="+proBoard.getProbSn();
			} else {
				model.addAttribute("message", "서버오류");
				viewName = "proboard/proboardForm";
			}
		} else {
			viewName = "proboard/proboardForm";
		}
		
		return viewName;
	}
}
