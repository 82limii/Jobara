package kr.co.jobara.board.askit.controller;

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
import kr.co.jobara.board.askit.service.AskItService;
import kr.co.jobara.board.askit.vo.AskItVO;
import kr.co.jobara.commons.service.AttatchService;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.validate.hints.UpdateGroup;

/**
 * @author 최인수
 * Ask It 게시글 수정 로직
 */
@Controller
public class AskItUpdateController {
	
	@Inject
	private AskItService service;
	
	@Inject
	private AttatchService attatchService;
	
	@Inject
	private LogService logService;
	
	@RequestMapping("/board/askItUpdate.do")
	public String form(
			@RequestParam(value="what", required=false) int boardSn
			, Model model
			) {
		AskItVO askIt = service.retrieveAskItBoard(boardSn);
		model.addAttribute("askIt", askIt);
		
		return "board/askIt/askItForm";
	}
	
	@RequestMapping(value="/board/askItUpdate.do", method=RequestMethod.POST)
	public String process(
			@Validated(UpdateGroup.class) @ModelAttribute("askIt") AskItVO askItBoard
			, Errors errors
			, Model model
			, HttpServletRequest req
			, @SessionAttribute("authMember") PmemberVO auth
			) {
		String viewName = null;
		MultipartFile[] files = askItBoard.getAskItFiles();
		String biztype = "askit";
		if(!errors.hasErrors()) {
			String attSn = attatchService.processAttatchFile(files, biztype);
			askItBoard.setAttSn(attSn);
			ServiceResult result = service.modifyAskItBoard(askItBoard);
			if(ServiceResult.OK == result) {
				// log
				String pmemId = auth.getPmemId();
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+askItBoard.getBoardSn()).build();
				logService.createLog(log);
				
				viewName = "redirect:/board/askItView.do?what=" + askItBoard.getBoardSn();
			} else {
				model.addAttribute("message", "서버오류");
				viewName = "board/askItForm";
			}
		} else {
			viewName = "board/askItForm";
		}
		
		
		
		return viewName;
	}
	
	
	
	
	
	
	
	
}
