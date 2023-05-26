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
import kr.co.jobara.pms.project.service.ProNoticeService;
import kr.co.jobara.pms.project.vo.ProNoticeVO;
import kr.co.jobara.validate.hints.UpdateGroup;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ProjectNoticeUpdateController {
	
	@Inject
	private ProNoticeService service;
	
	@Inject
	private AttatchService attatchService;
	
	@Inject
	private LogService logService;
	
	@RequestMapping("/project/projNoticeUpdate.do")
	public String form(
			@RequestParam(value="jobara", required=true) int notiSn
			, @RequestParam("what") int proSn
			, Model model
			) {
		
		ProNoticeVO notice = service.retrieveNotice(notiSn);
		model.addAttribute("notice", notice);
		
		return "project/projectNoticeForm";
	}
	
	@RequestMapping(value="/project/projNoticeUpdate.do", method=RequestMethod.POST)
	public String process(
			@Validated(UpdateGroup.class) @ModelAttribute("notice") ProNoticeVO notice
			,Errors errors
			, @RequestParam("what") int proSn
			, Model model
			, HttpServletRequest req
			, @SessionAttribute("authMember") PmemberVO auth
			) {
		String viewName = null;
		MultipartFile[] files = notice.getNotiFiles();
		String biztype = "noti";
		if(!errors.hasErrors()) {
			String attSn = attatchService.processAttatchFile(files, biztype);
			notice.setAttSn(attSn);
			ServiceResult result = service.modifyNotice(notice);
			if(ServiceResult.OK == result) {
				// log
				String pmemId = auth.getPmemId();
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+notice.getNotiSn()).build();
				logService.createLog(log);
				
				viewName = "redirect:/project/projNoticeView.do?jobara=" + notice.getNotiSn() +"&what=" + proSn;
			} else {
				model.addAttribute("message", "서버오류");
				viewName = "project/projectNoticeForm";
			}
		} else {
			log.info("error : " + errors);
			viewName = "project/projectNoticeForm";
		}
		return viewName;
		
	}
	
}















