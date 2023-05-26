package kr.co.jobara.board.askit.controller;

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
import org.springframework.web.multipart.MultipartFile;

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.board.askit.service.AskItService;
import kr.co.jobara.board.askit.vo.AskItVO;
import kr.co.jobara.commons.service.AttatchService;
import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.MenuVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.validate.hints.InsertGroup;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 최인수
 * Ask It 게시글 등록 로직
 */
@Controller
@Slf4j
public class AskItInsertController {
	
	@Inject
	private MenuService menuService;

	@Inject
	private AskItService service;
	
	@Inject
	private AttatchService attatchService;
	
	@Inject
	private LogService logService;
	
	@RequestMapping("/board/askItInsert.do")
	public String form(
			@ModelAttribute("askIt") AskItVO askItBoard
			,Model model) {
		List<MenuVO> menuList = menuService.retrieveMenu("board");
		model.addAttribute("menuList", menuList);
		return "board/askIt/askItForm";
	}
	
	@RequestMapping(value="/board/askItInsert.do", method=RequestMethod.POST)
	public String process(
			@SessionAttribute(value="authMember", required=false) PmemberVO pMem
			, @Validated(InsertGroup.class) @ModelAttribute("askIt") AskItVO askItBoard
			, BindingResult errors
			, Model model
			, HttpServletRequest req
			) {
		String viewName = null;
		String pmemId = pMem.getPmemId();
		askItBoard.setPmemId(pmemId);
		MultipartFile[] files = askItBoard.getAskItFiles();
		String biztype = "askit";
		log.info("errors : " + errors);
		if(!errors.hasErrors()) {
			String attSn = attatchService.processAttatchFile(files, biztype);
			askItBoard.setAttSn(attSn);
			ServiceResult result = service.createAskItBoard(askItBoard);
			if(ServiceResult.OK == result) {
				// log
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+askItBoard.getBoardSn()).build();
				logService.createLog(log);
				
				viewName = "redirect:/board/askItList.do";
			} else {
				model.addAttribute("message", "서버 오류");
				viewName = "board/askIt/askItForm";
			} 
		} else {
			log.info("혹시 여기로?");
			viewName = "board/askIt/askItForm";
		}
		return viewName; 
	}
	
	
}
