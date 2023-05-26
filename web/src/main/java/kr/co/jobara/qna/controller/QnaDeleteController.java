package kr.co.jobara.qna.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.qna.service.QnaService;

@Controller
@RequestMapping("/qna")
public class QnaDeleteController {

	@Inject
	private QnaService service;
	
	@Inject
	private LogService logService;
	
	@RequestMapping("qnaDelete.do")
	public String qnaDelete(
		@RequestParam(value="what", required=true) int qnaSn
		, Model model
		, HttpServletRequest req
		, @SessionAttribute("authMember") PmemberVO auth
	) {
		String viewName = null;
		ServiceResult result = service.removeQna(qnaSn);
		if(ServiceResult.OK.equals(result)) {
			// log
			String pmemId = auth.getPmemId();
			StringBuffer url = req.getRequestURL();
			LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+qnaSn).build();
			logService.createLog(log);
			
			viewName = "redirect:/qna/qnaList.do";
		} else {
			model.addAttribute("message", "서버오류");
			viewName = "redirect:/qna/qnaList.do";
		}
		
		return viewName;
	}
	
	@RequestMapping("com/qnaDelete.do")
	public String qnaDeleteCom(
		@RequestParam(value="what", required=true) int qnaSn
		, Model model
		, HttpServletRequest req
		, @SessionAttribute("authMember") EmemberVO auth
	) {
		String viewName = null;
		ServiceResult result = service.removeQna(qnaSn);
		if(ServiceResult.OK.equals(result)) {
			// log
			String ememId = auth.getEmemId();
			StringBuffer url = req.getRequestURL();
			LogVO log = LogVO.builder().ememId(ememId).logAdd(url.toString()+"?what="+qnaSn).build();
			logService.createLog(log);

			viewName = "redirect:/qna/com/qnaList.do";
		} else {
			model.addAttribute("message", "서버오류");
			viewName = "redirect:/qna/com/qnaList.do";
		}
		
		return viewName;
	}
	
}
