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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.commons.service.AttatchService;
import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.MenuVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.qna.service.QnaService;
import kr.co.jobara.qna.vo.QnaVO;
import kr.co.jobara.validate.hints.InsertGroup;

@Controller
@RequestMapping("/qna")
public class QnaInsertController {
	
	@Inject
	private MenuService menuService;
	
	@Inject
	private QnaService service;
	
	@Inject
	private AttatchService attatchService;
	
	@Inject
	private LogService logService;
	
	//QNA 등록
	@RequestMapping("qnaInsert.do")
	public String qnaInsertForm(
		@ModelAttribute("qna") QnaVO qna
		, Model model
	) {
		List<MenuVO> menuList = menuService.retrieveMenu("compage_per");
		model.addAttribute("menuList", menuList);

		return "qna/qnaForm";
	}
	
	//QNA 등록
	@RequestMapping(value="qnaInsert.do", method=RequestMethod.POST)
	public String qnaInsertProcess(
		@SessionAttribute(value="authMember", required=false) PmemberVO authMember
		, @Validated(InsertGroup.class) @ModelAttribute("qna") QnaVO qna
		, Errors errors
		, Model model
		, HttpServletRequest req
	) {
		String viewName = null;
		String pmemId = authMember.getPmemId();
		qna.setPmemId(pmemId);
		MultipartFile[] files = qna.getQnaFiles();
		String biztype = "qna";
		if(!errors.hasErrors()) {
			String attSn = attatchService.processAttatchFile(files, biztype);
			qna.setAttSn(attSn);
			ServiceResult result = service.createQna(qna);
			if(ServiceResult.OK.equals(result)) {
				// log
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+qna.getQnaSn()).build();
				logService.createLog(log);
				
				viewName = "redirect:/qna/qnaView.do?what="+qna.getQnaSn();
			}else {
				model.addAttribute("message", "서버 오류, 조금 있다 다시 시도하세요.");
				viewName = "qna/qnaForm";
			}
		}else {
			viewName = "qna/qnaForm";
		}
		
		
		return viewName;
	}
	
	//답변 등록
	@RequestMapping("com/qnaInsert.do")
	public String qnaInsertFormCom(
		@ModelAttribute("qna") QnaVO qna
		, Model model
	) {
		List<MenuVO> menuList = menuService.retrieveMenu("compage_per");
		model.addAttribute("menuList", menuList);
		
		return "qna/qnaForm";
	}
	
	//답변 등록
	@RequestMapping(value="com/qnaInsert.do", method=RequestMethod.POST)
	public String qnaInsertProcessCom(
		@SessionAttribute(value="authMember", required=false) EmemberVO authMember
		, @Validated(InsertGroup.class) @ModelAttribute("qna") QnaVO qna
		, Errors errors
		, Model model
		, HttpServletRequest req
	) {
		String viewName = null;
		String ememId = authMember.getEmemId();
		qna.setEmemId(ememId);
		MultipartFile[] files = qna.getQnaFiles();
		String biztype = "qna";
		if(!errors.hasErrors()) {
			String attSn = attatchService.processAttatchFile(files, biztype);
			qna.setAttSn(attSn);
			ServiceResult result = service.createQna(qna);
			if(ServiceResult.OK.equals(result)) {
				// log
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().ememId(ememId).logAdd(url.toString()+"?what="+qna.getQnaSn()).build();
				logService.createLog(log);
				
				viewName = "redirect:/qna/com/qnaView.do?what="+qna.getQnaSn();
			}else {
				model.addAttribute("message", "서버 오류, 조금 있다 다시 시도하세요.");
				viewName = "qna/qnaForm";
			}
		}else {
			viewName = "qna/qnaForm";
		}
		
		
		return viewName;
	}
	
}
