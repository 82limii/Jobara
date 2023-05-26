package kr.co.jobara.jobboard.controller;

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
import kr.co.jobara.jobboard.service.JobBoardService;
import kr.co.jobara.jobboard.vo.JobBoardVO;
import kr.co.jobara.member.vo.EmemberVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/jobboard")
@Slf4j
public class JobBoardUpdateController {
	
	@Inject
	private JobBoardService service;
	
	@Inject
	private AttatchService attatchService;

	@Inject
	private LogService logService;

	@RequestMapping("com/jobBoardUpdate.do")
	public String form(
			@RequestParam(value="what", required=true) int jobSn
			, Model model
	) {
		JobBoardVO jobBoard = service.retrieveJobBoard(jobSn);
		model.addAttribute("jobBoard", jobBoard);
		
		return "jobboard/jobboardForm";
	}
	
	@RequestMapping(value="com/jobBoardUpdate.do", method=RequestMethod.POST)
	public String process(
			@Validated @ModelAttribute("jobBoard") JobBoardVO jobBoard
			, BindingResult errors
			, Model model
			, @SessionAttribute("authMember") EmemberVO auth 
			, HttpServletRequest req
	) {
		String viewName = null;
		ServiceResult result = null;
		// 공고의 작성자가 로그인한 사용자와 일치하는지 확인
		String ememId = auth.getEmemId();
		if(!jobBoard.getEmemId().equals(ememId)) {
			result = ServiceResult.INVALIDUSER;
			viewName = "redirect:/";
			return viewName;
		}
		// 확인작업 끝
		
		
		
		MultipartFile[] files = jobBoard.getJobFiles();
		String biztype = "jobboard";
		if (!errors.hasErrors()) {
			String attSn = attatchService.processAttatchFile(files, biztype);
			jobBoard.setAttSn(attSn);
			result = service.modifyJobBoard(jobBoard);
			switch (result) {
			case OK:
				// 로그
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().ememId(ememId).logAdd(url.toString()+"?what="+jobBoard.getJobSn()).build();
				logService.createLog(log);
				
				viewName = "redirect:/jobboard/com/jobBoardView.do?what="+jobBoard.getJobSn();
				break;

			default:
				model.addAttribute("message", "서버오류");
				viewName = "jobboard/jobboardForm";
				break;
			}
		} else {
			viewName = "jobboard/jobboardForm";
		}
		
		return viewName;
	}
	
}
