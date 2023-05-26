package kr.co.jobara.board.shareit.controller;

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
import kr.co.jobara.board.shareit.service.ShareItService;
import kr.co.jobara.board.shareit.vo.ShareItVO;
import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.MenuVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.validate.hints.InsertGroup;

@Controller
public class ShareItInsertController {
	
	@Inject
	private MenuService menuService;
	
	@Inject
	private ShareItService service;
	
	@Inject
	private LogService logService;

	@RequestMapping("/board/shareItInsert.do")
	public String Form(
			@ModelAttribute("shareIt") ShareItVO shareItBoard
			, Model model) {
		List<MenuVO> menuList = menuService.retrieveMenu("board");
		model.addAttribute("menuList", menuList);
		return "board/shareIt/shareItForm";
	}
	
	@RequestMapping(value="/board/shareItInsert.do", method=RequestMethod.POST)
	public String process(
			@SessionAttribute(value="authMember", required=true) PmemberVO pMem
			,@Validated(InsertGroup.class) @ModelAttribute("shareIt") ShareItVO shareItBoard
			,BindingResult errors
			,Model model
			, HttpServletRequest req
			) {
		String viewName = null;
		String pmemId = pMem.getPmemId();
		shareItBoard.setPmemId(pmemId);
		if(!errors.hasErrors()) {
			ServiceResult result = service.createShareItBoard(shareItBoard);
			if(ServiceResult.OK == result) {
				// log
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+shareItBoard.getRebSn()).build();
				logService.createLog(log);
				
				viewName = "redirect:/board/shareItView.do?what=" + shareItBoard.getRebSn();
			} else {
				model.addAttribute("message", "서버 오류");
				viewName = "board/shareIt/shareItForm";
			}
		} else {
			viewName = "board/shareIt/shareItForm";
		}
		return viewName;
	}
	
}
