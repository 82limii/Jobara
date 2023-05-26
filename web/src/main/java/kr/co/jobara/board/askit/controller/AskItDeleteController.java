package kr.co.jobara.board.askit.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.board.askit.service.AskItService;
import kr.co.jobara.board.askit.vo.AskItVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.vo.PmemberVO;

/**
 * @author 최인수
 * Ask It 게시글 삭제 로직
 */
@Controller
@RequestMapping("/board/askItDelete.do")
public class AskItDeleteController {
	
	@Inject
	private AskItService askItService;
	
	@Inject
	private LogService logService;
	
	@RequestMapping(method=RequestMethod.POST)
	public String process(
			@ModelAttribute("askIt") AskItVO askIt
			, Errors errors
			, Model model
			, HttpServletRequest req
			, @SessionAttribute("authMember") PmemberVO auth
			) {
		String viewName = null;
		int boardSn = askIt.getBoardSn();
		if(!errors.hasErrors()) {
			ServiceResult result = askItService.removeAskItBoard(boardSn);
			if(ServiceResult.OK== result) {
				//log
				String pmemId = auth.getPmemId();
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+askIt.getBoardSn()).build();
				logService.createLog(log);
				
				viewName = "redirect:/board/askItList.do";
			} else {
				model.addAttribute("message", "서버 오류");
				viewName = "board/askItView";
			}
		} else {
			viewName = "board/askItView";
		}
		return viewName;
	}
	
	
	
	
}
