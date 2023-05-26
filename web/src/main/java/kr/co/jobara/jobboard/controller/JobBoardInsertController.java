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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.commons.service.AttatchService;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.jobboard.service.JobBoardService;
import kr.co.jobara.jobboard.vo.JobBoardVO;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.validate.hints.InsertGroup;

@Controller
@RequestMapping("/jobboard")
public class JobBoardInsertController {
	
	@Inject
	private JobBoardService service;
	
	@Inject
	private AttatchService attatchService;
	
	@Inject
	private LogService logService;
	
	
	@RequestMapping("com/jobBoardInsert.do")
	public String form(Model model
		) {
		JobBoardVO jobBoard = new JobBoardVO();
		model.addAttribute("jobBoard", jobBoard);
		
		return "jobboard/jobboardForm";
	}
	
	@RequestMapping(value="com/jobBoardInsert.do", method=RequestMethod.POST)
	public String process(
			@Validated(InsertGroup.class) @ModelAttribute("jobBoard") JobBoardVO jobBoard
			, BindingResult errors
			, Model model
			, @SessionAttribute("authMember") EmemberVO auth 
			, HttpServletRequest req
	) {
		String viewName = null;
		String ememId = auth.getEmemId();

		
		jobBoard.setEmemId(ememId);
		MultipartFile[] files = jobBoard.getJobFiles();
		String biztype = "jobboard";
		if(!errors.hasErrors()) {
			String attSn = attatchService.processAttatchFile(files, biztype);
			jobBoard.setAttSn(attSn);
			ServiceResult result = service.createJobBoard(jobBoard);
			if(ServiceResult.OK == result) {
				// 로그
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().ememId(ememId).logAdd(url.toString()+"?what="+jobBoard.getJobSn()).build();
				logService.createLog(log);
				
				viewName = "redirect:/jobboard/com/jobBoardView.do?what="+jobBoard.getJobSn();
			} else {
				model.addAttribute("message", "서버오류");
				viewName = "jobboard/jobboardForm";
			}
		} else {
			viewName = "jobboard/jobboardForm";
		}
		return viewName;
	}
}
