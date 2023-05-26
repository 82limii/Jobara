package kr.co.jobara.qna.controller;

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

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.MenuVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.vo.MemberVO;
import kr.co.jobara.qna.service.QnaService;
import kr.co.jobara.qna.vo.QnaVO;
import kr.co.jobara.validate.hints.UpdateGroup;

@Controller
@RequestMapping("/qna/qnaUpdate.do")
public class QnaUpdateController {
	
	@Inject
	private MenuService menuService;
	
	@Inject
	private QnaService service;
	
	@Inject
	private LogService logService;
	
	// QNA 수정
	@RequestMapping
	public String qnaUpdateForm(
		@RequestParam(value="what", required=true) int qnaSn
		, Model model
	) {
		QnaVO qna = service.retrieveQna(qnaSn);
		model.addAttribute("qna", qna);
		return "qna/qnaEdit";
	}
	
	// QNA 수정
	@RequestMapping(method=RequestMethod.POST)
	public String qnaUpdateProcess(
		@Validated(UpdateGroup.class) @ModelAttribute("qna") QnaVO qna
		, Errors errors
		, Model model
		, HttpServletRequest req
		, @SessionAttribute("authMember") MemberVO auth
	) {
		String viewName = null;
		if(errors.hasErrors()) {
			viewName = "qna/qnaEdit";
		}else {
			ServiceResult result = service.modifyQna(qna);
			switch (result) {
			case FAIL:
				viewName = "qna/qnaEdit";
				model.addAttribute("message", "서버 오류");
				break;

			default:
				// log
				String id = auth.getId();
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().ememId(id).logAdd(url.toString()+"?what="+qna.getQnaSn()).build();
				logService.createLog(log);
				
				viewName = "redirect:/qna/qnaView.do?what="+qna.getQnaSn();
				break;
			}
		}
		List<MenuVO> menuList = menuService.retrieveMenu("compage_per");
		model.addAttribute("menuList", menuList);
		
		return viewName;
	}
	
}
