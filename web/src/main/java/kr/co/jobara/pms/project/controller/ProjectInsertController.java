package kr.co.jobara.pms.project.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.incumbent.service.IncumbentService;
import kr.co.jobara.incumbent.vo.IncumbentVO;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.pms.project.service.ProCreateService;
import kr.co.jobara.pms.project.service.ProMemberService;
import kr.co.jobara.pms.project.vo.ProCreateVO;
import kr.co.jobara.proboard.vo.ProBoardVO;
import kr.co.jobara.searchboard.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/project")
@Slf4j
public class ProjectInsertController {
	
	@Inject
	private ProCreateService service;
	
	@Inject
	private IncumbentService incumbentService;
	
	@Inject
	private ProMemberService proMemberService;
	
	@Inject
	private LogService logService;
	
	@RequestMapping("com/projInsert.do")
	public String form(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @SessionAttribute(value="authMember", required=false) EmemberVO authEmember
			, @ModelAttribute("project") ProCreateVO project
			, @ModelAttribute("detailSearch") IncumbentVO detailSearch
			, @ModelAttribute("simpleSearch") SearchVO simpleSearch
			, Model model) {
		String ememId = authEmember.getEmemId();
		detailSearch.setEmemId(ememId);
		PagingVO<IncumbentVO> pagingVO = new PagingVO<>();
		List<ProBoardVO> proBoardList = service.retrieveProjApplyList(ememId);
		
		pagingVO.setSimpleSearch(simpleSearch);
		pagingVO.setDetailSearch(detailSearch);
		pagingVO.setCurrentPage(currentPage);

		incumbentService.retrieveIncumbentList(pagingVO);
		model.addAttribute("paging", pagingVO);
		model.addAttribute("proBoardList",proBoardList);
		return "project/projectForm";
	}
	
	@RequestMapping(value="com/projInsert.do", method=RequestMethod.POST )
	public String process(
			@SessionAttribute(value="authMember", required=false) EmemberVO authEmember
			, @ModelAttribute("project") ProCreateVO project
			, BindingResult errors
			, Model model
			, HttpServletRequest request){
		//project : ProCreateVO(proSn=null, ememId=null, proName=테스트 프로젝트11111, proStartd=2022-03-24, proEndd=2022-03-26
		//, projectMemberList=[
		//  ProMemberVO(rnum=0, proSn=null, pmemId=insoo, pmemNm=최인수, memPosition=null, memEmail=null, memTel=null, memFree=null)
		//, ProMemberVO(rnum=0, proSn=null, pmemId=, pmemNm=, memPosition=null, memEmail=null, memTel=null, memFree=null)])
		log.info("project : " + project.toString());
		String viewName = null;
		String ememId = authEmember.getEmemId();
		if (!errors.hasErrors()) {
			project.setEmemId(ememId);
			ServiceResult result = service.createProject(project);
			if (ServiceResult.OK == result) {
				// log
				StringBuffer url = request.getRequestURL();
				LogVO log = LogVO.builder().ememId(ememId).logAdd(url.toString()+"?what="+project.getProSn()).build();
				logService.createLog(log);
				
				viewName = "redirect:/project/com/projHome.do";
			} else {
				model.addAttribute("message", "서버 오류");
				viewName = "project/projectForm";
			}
		} else {
			viewName = "project/projectForm";
		}
		
		return viewName;
	}
	
	@RequestMapping("com/proBoardApplyMem.do")
	public String proBoardApplyList(
			@RequestParam("what") int probSn
			, Model model
			) {
		log.info("들어온값 : "+probSn+"");
		List<ProBoardVO> applyMemList = service.retrieveApplyMemList(probSn);
		model.addAttribute("applyMemList", applyMemList);
		
		
		return "jsonView";
	}
	
	
}
