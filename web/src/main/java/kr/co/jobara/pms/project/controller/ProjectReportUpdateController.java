package kr.co.jobara.pms.project.controller;

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
import kr.co.jobara.commons.service.AttatchService;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.pms.project.service.ReportService;
import kr.co.jobara.pms.project.vo.ReportVO;
import kr.co.jobara.validate.hints.UpdateGroup;

@Controller
public class ProjectReportUpdateController {

	@Inject
	private ReportService service;
	
	@Inject
	private LogService logService;
	
	@Inject
	private AttatchService attatchService;
	
	@RequestMapping("/project/projReportUpdate.do")
	public String form(
			@RequestParam(value="jobara", required=false) int repoSn
			, @RequestParam("what") int proSn
			, Model model
			) {
		ReportVO report = service.retrieveReport(repoSn);
		model.addAttribute("report", report);
		return "project/projectReportForm";
	}
	
	
	@RequestMapping(value="/project/projReportUpdate.do", method=RequestMethod.POST)
	public String process(
			@Validated(UpdateGroup.class) @ModelAttribute("report") ReportVO report
			, Errors errors
			, @RequestParam("what") int proSn
			, Model model
			, HttpServletRequest req
			, @SessionAttribute("authMember") PmemberVO auth
			) {
		String viewName = null;
		MultipartFile[] files = report.getRepoFiles();
		String biztype = "report";
		if(errors.hasErrors()) {
			String attSn = attatchService.processAttatchFile(files, biztype);
			report.setAttSn(attSn);
			ServiceResult result = service.modifyReport(report);
			if(ServiceResult.OK == result) {
				// log
				String pmemId = auth.getPmemId();
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+report.getRepoSn()).build();
				logService.createLog(log);
				
				viewName = "redirect:/project/projReportView.do?jobara=" + report.getRepoSn() + "&what=" + proSn;
			} else {
				model.addAttribute("message", "서버오류");
				viewName = "project/projectReportFrom";
			}
		} else {
			viewName = "project/projectReportFrom";
		}
		return viewName;
	
	}
	
	
	
	
}



















