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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.commons.service.AttatchService;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.proboard.service.ProBoardService;
import kr.co.jobara.proboard.vo.ProBoardVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/proboard")
@Slf4j
public class ProBoardUpdateController {
	
	@Inject
	private ProBoardService service;
	
	@Inject
	private AttatchService attatchService;
	
	@Inject
	private LogService logService;
	
	@RequestMapping("com/proBoardUpdate.do")
	public String form(
			@RequestParam(value="what", required=true) int probSn
			, Model model
	) {
		ProBoardVO proBoard = service.retrieveProBoard(probSn);
		model.addAttribute("proBoard", proBoard);
		
		return "proboard/proboardForm";
	}
	
	@RequestMapping(value="com/proBoardUpdate.do", method=RequestMethod.POST)
	public String process(
		@Validated @ModelAttribute("proBoard") ProBoardVO proBoard
		, BindingResult errors
		, Model model
		, @SessionAttribute("authMember") EmemberVO auth
		, HttpServletRequest req
			) {
		String viewName = null;
		ServiceResult result = null;
		// 공고의 작성자가 로그인한 사용자와 일치하는지 확인
		String ememId = auth.getEmemId();
		if(!proBoard.getEmemId().equals(ememId)) {
			result = ServiceResult.INVALIDUSER;
			viewName = "redirect:/";
			return viewName;
		}
		// 확인작업 끝
		
		MultipartFile[] files = proBoard.getProbFiles();
		String biztype = "proboard";
		if(!errors.hasErrors()) {
			String attSn = attatchService.processAttatchFile(files, biztype);
			proBoard.setAttSn(attSn);
			result = service.modifyProBoard(proBoard);
			switch (result) {
			case OK:
				// 로그
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().ememId(ememId).logAdd(url.toString()+"?what="+proBoard.getProbSn()).build();
				logService.createLog(log);

				viewName = "redirect:/proboard/com/proBoardView.do?what="+proBoard.getProbSn();
				break;

			default:
				model.addAttribute("message", "서버오류");
				viewName = "proboard/proboardForm";
				break;
			}
		} else {
			viewName = "proboard/proboardForm";
		}
		return viewName;
	}
	
}
