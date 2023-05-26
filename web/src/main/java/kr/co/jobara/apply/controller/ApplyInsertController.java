package kr.co.jobara.apply.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.apply.service.ApplyService;
import kr.co.jobara.apply.vo.ApplyVO;
import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.vo.PmemberVO;

/**
 * @author 이상림
 * 최초작성일 2022.03.21
 */
@Controller
@RequestMapping("/apply")
public class ApplyInsertController {
	
	@Inject
	private ApplyService service;
	
	@Inject
	private LogService logService;
	
	@RequestMapping("jobApply.do")
	public String jobApply(
			@ModelAttribute ApplyVO apply
			, HttpServletRequest req
			,@SessionAttribute("authMember") PmemberVO auth
	) {
		String viewName = null;
		ServiceResult result = null;
		validate(apply, result);
		String pmemId = auth.getPmemId();
		apply.setPmemId(pmemId);
		if(ServiceResult.FAIL == result) {
			viewName = "redirect:/jobboard/jobBoardView.do?what="+apply.getJobSn();
		} else {
			result = service.applyBoard(apply);
			if (ServiceResult.OK == result) {
				
				// log
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+apply.getAppSn()).build();
				logService.createLog(log);
				
				viewName = "redirect:/jobboard/jobBoardView.do?what="+apply.getJobSn();
			}
		}
		
		return viewName;
	}
	
	@RequestMapping("proApply.do")
	public String proApply(
			@ModelAttribute ApplyVO apply
			, HttpServletRequest req
			, @SessionAttribute("authMember") PmemberVO auth
	) {
		String viewName = null;
		ServiceResult result = null;
		validate(apply, result);
		
		String pmemId = auth.getPmemId();
		StringBuffer url = req.getRequestURL();
		LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()).build();
		logService.createLog(log);
		
		if(ServiceResult.FAIL == result) {
			viewName = "redirect:/proboard/proBoardView.do?what="+apply.getProbSn();
		} else {
			result = service.applyBoard(apply);
			if (ServiceResult.OK == result) {
				viewName = "redirect:/proboard/proBoardView.do?what="+apply.getProbSn();
			}
		}
		
		return viewName;
	}
	
	public void validate(ApplyVO apply, ServiceResult result) {
		Integer jobSn = apply.getJobSn();
		Integer probSn = apply.getProbSn();
		Integer reSn = apply.getReSn();
		
		if(jobSn == null && probSn == null) {
			// 지원하기 위한 공고가 존재해야함
			throw new PKNotFoundException("해당 공고가 존재하지 않습니다.");
		} else if (jobSn != null && probSn != null) {
			// 채용공고와 프로젝트 공고에 한번에 지원할 수 없다
			result = ServiceResult.FAIL;
		}
		// 지원하는 이력서가 존재해야함
		if(reSn == null) {
			throw new PKNotFoundException("해당 이력서가 존재하지 않습니다.");
		}
	}
}
