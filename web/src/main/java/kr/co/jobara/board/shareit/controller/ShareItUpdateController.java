package kr.co.jobara.board.shareit.controller;

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

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.board.shareit.service.ShareItService;
import kr.co.jobara.board.shareit.vo.ShareItVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.validate.hints.UpdateGroup;

@Controller
public class ShareItUpdateController {

	@Inject
	private ShareItService service;
	
	@Inject
	private LogService logService;
	
	@RequestMapping("/board/shareItUpdate.do")
	public String form(
			@RequestParam(value="what", required=false) int rebSn
			, Model model
			) {
		ShareItVO shareIt = service.retrieveShareItBoard(rebSn);
		model.addAttribute("shareIt", shareIt);
		
		return "board/shareIt/shareItForm";
		
	}
	
	@RequestMapping(value="/board/shareItUpdate.do", method=RequestMethod.POST)
	public String process(
			@Validated(UpdateGroup.class) @ModelAttribute("shareIt") ShareItVO shareItBoard
			,Errors errors
			,Model model
			, HttpServletRequest req
			, @SessionAttribute("authMember") PmemberVO auth
		) {
		
		String viewName = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyShareItBoard(shareItBoard);
			if(ServiceResult.OK == result) {
				// log
				String pmemId = auth.getPmemId();
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+shareItBoard.getRebSn()).build();
				logService.createLog(log);
				
				viewName = "redirect:/board/shareItView.do?what="+shareItBoard.getRebSn();
			} else {
				model.addAttribute("message","서버오류");
				viewName = "board/shareItForm";
			}
			
		} else {
			viewName = "board/shareItForm";
		}
		return viewName;
	}
	
	
	
	
	
	
	
	
	
	
}
















