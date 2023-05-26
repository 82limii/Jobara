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
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.pms.project.service.ProNoticeService;
import kr.co.jobara.pms.project.vo.ProNoticeVO;

/**
 * @author 최인수
 * PMS 전달사항 등록 로직
 */
@Controller
public class ProjectNoticeInsertController {
	
	@Inject
	private ProNoticeService service;
	
	@Inject
	private AttatchService attatchService;
	
	@Inject
	private LogService logService;
	
	@RequestMapping("/project/projNoticeInsert.do")
	public String form(
			@ModelAttribute("notice") ProNoticeVO notice
			, @RequestParam("what") int proSn
			, Model model) {
		return "project/projectNoticeForm";
	}
	
	
	@RequestMapping(value="/project/projNoticeInsert.do", method=RequestMethod.POST)
	public String  notiProcess(
			@Validated @ModelAttribute("notice") ProNoticeVO notice
			, BindingResult errors
			, @SessionAttribute("authMember") PmemberVO pMem
			, @RequestParam("what") int proSn
			, Model model
			, HttpServletRequest req
			) {
		String pmemId = pMem.getPmemId();
		notice.setPmemId(pmemId);
		notice.setProSn(proSn);
		String viewName = null;
		MultipartFile[] files = notice.getNotiFiles();
		String biztype = "noti";
		if(!errors.hasErrors()) {
			String attSn = attatchService.processAttatchFile(files, biztype);
			notice.setAttSn(attSn);
			ServiceResult result = service.createNotice(notice);
			if(ServiceResult.OK == result) {
				// log
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+notice.getNotiSn()).build();
				logService.createLog(log);
				
				viewName = "redirect:/project/projNotice.do?what=" + proSn;
			} else {
				model.addAttribute("message", "서버오류");
				viewName = "project/projectNoticeForm";
			}
		} else {
			viewName = "project/projectNoticeForm";
		}
		
		return viewName;
	}
}














