package kr.co.jobara.pms.project.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.pms.project.service.ProCreateService;
import kr.co.jobara.pms.project.service.ProMemberService;
import kr.co.jobara.pms.project.vo.ProCreateVO;
import kr.co.jobara.pms.project.vo.ProMemberVO;
import kr.co.jobara.proboard.vo.ProBoardVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/project")
@Slf4j
public class ProjectMemberRetrieveController {
	
	@Inject
	private ProMemberService service;
	
	@Inject
	private ProCreateService proCreateService;
	
	@RequestMapping(value="projMemList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<ProMemberVO> listToJson(
			@SessionAttribute("authMember") EmemberVO auth
			, Model model
			) {
		
		String ememId = auth.getEmemId();
		PagingVO<ProMemberVO> pagingVO = new PagingVO<>();
		List<ProBoardVO> proBoardList = proCreateService.retrieveProjApplyList(ememId);
		List<ProCreateVO> projListCom = proCreateService.retrieveProjListCom(ememId);
		model.addAttribute("proBoardList", proBoardList);
		model.addAttribute("projListCom", projListCom);
		service.retrieveProMemberList(pagingVO);
		return pagingVO;
	}
	
	///////////구성원 조회
	@RequestMapping("projMemList.do")
	public String list() {
		log.info("list()메소드에 왔다");
		return "project/projectMemberList";
	}
	

	@RequestMapping("com/projMemList.do")
	public String listCom(
			@SessionAttribute("authMember") EmemberVO auth
			, Model model
			) {
		String ememId = auth.getEmemId();
		List<ProBoardVO> proBoardList = proCreateService.retrieveProjApplyList(ememId);
		List<ProCreateVO> projListCom = proCreateService.retrieveProjListCom(ememId);
		model.addAttribute("proBoardList", proBoardList);
		model.addAttribute("projListCom", projListCom);
		log.info("Comlist()메소드에 왔다");
		return "project/projectMemberList";
	}
	
//	@RequestMapping(value="com/projMemList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public PagingVO<ProMemberVO> listToComJson(
//			@RequestParam(value="what") int what
//			, @ModelAttribute("detailSearch") ProMemberVO detailSearch
//			, Model model
//			, @ModelAttribute("probVO") ProBoardVO probVO
//	){
//		detailSearch.setProSn(what);
//		int probSn = probVO.getProbSn();
//		log.info("probSn : " + probSn);
//		PagingVO<ProMemberVO> pagingVO = new PagingVO<>();
//		pagingVO.setDetailSearch(detailSearch);
//		
//		List<ProBoardVO> applyMemList = proCreateService.retrieveApplyMemList(what);
//		model.addAttribute("applyMemList", applyMemList);
//		
//		
//		
//		service.retrieveProMemberList(pagingVO);
//		return pagingVO;
//	}

}