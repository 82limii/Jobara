package kr.co.jobara.pms.project.controller;

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
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.pms.project.service.ReportService;
import kr.co.jobara.pms.project.vo.ReportVO;

/**
 * @author 최인수
 * PMS 보고서 등록 로직
 */
@Controller
public class ProjectReportInsertController {

	@Inject
	private ReportService service;
	
	@Inject
	private AttatchService attatchService;
	
	@Inject
	private LogService logService;
	
	@RequestMapping("/project/projReportInsert.do")
	public String form(
			@ModelAttribute("report") ReportVO report
			, Model model
			) {
		
		return "project/projectReportForm";
	}
	
	@RequestMapping(value="/project/projReportInsert.do", method=RequestMethod.POST)
	public String repoProcess(
			@Validated @ModelAttribute("report") ReportVO report
			, BindingResult errors
			, @SessionAttribute(value="authMember", required=false) PmemberVO pMem
			, Model model
			, @RequestParam("what") int proSn
			, HttpServletRequest req
			) {
		String pmemId = pMem.getPmemId();
		report.setPmemId(pmemId);
		report.setProSn(proSn);
		
		String viewName = null;
		MultipartFile[] files = report.getRepoFiles();
		String biztype = "report";
		if(!errors.hasErrors()) {
			String attSn = attatchService.processAttatchFile(files, biztype);
			report.setAttSn(attSn);
			ServiceResult result = service.createReport(report);
			if(ServiceResult.OK == result) {
				// log
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+report.getRepoSn()).build();
				logService.createLog(log);
				
				viewName = "redirect:/project/projReport.do?what=" + proSn;
			} else {
				model.addAttribute("message", "서버 오류");
				viewName = "project/projectReportForm";
			}
		} else {
			viewName = "project/projectReportForm";
		}
				
		return viewName;
	}
	
}
