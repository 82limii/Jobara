package kr.co.jobara.board.askit.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.board.askit.service.CommentsService;
import kr.co.jobara.board.askit.vo.AskItVO;
import kr.co.jobara.board.askit.vo.CommentsVO;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.validate.hints.InsertGroup;
import lombok.extern.slf4j.Slf4j;

@RequestMapping(value="/board/{boardSn}/reply", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@Slf4j
public class AskItReplyController {
	
	@Inject
	private CommentsService service;
	
	@Inject
	private LogService logService;

	@PostMapping
	public Map<String, Object> insert(
			@Validated(InsertGroup.class) @ModelAttribute("reply") CommentsVO reply
			, BindingResult errors
			, @SessionAttribute("authMember") PmemberVO authMember
			, HttpServletRequest req
			){
		ServiceResult result = null;
		String pmemId = authMember.getPmemId();
		reply.setPmemId(pmemId);
		Map<String, Object> resultMap = new HashMap<>();
		log.info("reply : " + reply);
		if(!errors.hasErrors()) {
			// log
			StringBuffer url = req.getRequestURL();
			LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+reply.getCommSn()).build();
			logService.createLog(log);
			
			result = service.createReply(reply);
		} else {
			result = ServiceResult.FAIL;
			resultMap.put("message", "이 검증에 걸림");
		}
		resultMap.put("result", result);
		return resultMap;
	}

	@PutMapping
	public Map<String, Object> update(
			 @ModelAttribute("reply") CommentsVO reply
			, BindingResult errors
			, @SessionAttribute("authMember") PmemberVO authMember
			, @ModelAttribute("askIt") AskItVO askIt
			, HttpServletRequest req
			) {
		ServiceResult result = null;
		int boardSn = askIt.getBoardSn();
		String pmemId = authMember.getPmemId();
		reply.setPmemId(pmemId);
		reply.setBoardSn(boardSn);
		log.info("reply : " + reply);
		Map<String, Object> resultMap = new HashMap<>();
		
		if(!errors.hasErrors()) {
			// log
			StringBuffer url = req.getRequestURL();
			LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+reply.getCommSn()).build();
			logService.createLog(log);
			
			result = service.modifyReply(reply);
		} else {
			result = ServiceResult.FAIL;
			resultMap.put("message", "검증에 걸림");
		}
		resultMap.put("result", result);
		return resultMap;
	}
	
	@DeleteMapping
	public Map<String, Object> delete(
			@ModelAttribute("reply") CommentsVO reply
			, @ModelAttribute("askIt") AskItVO askIt
			, HttpServletRequest req
			,@SessionAttribute("authMember") PmemberVO auth
		){
		int boardSn = askIt.getBoardSn();
		int commSn = reply.getCommSn();
		reply.setBoardSn(boardSn);

		ServiceResult result = service.removeReply(commSn);

		// log
		String pmemId = auth.getPmemId();
		StringBuffer url = req.getRequestURL();
		LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+reply.getCommSn()).build();
		logService.createLog(log);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		return resultMap;
	}
	
	
	@GetMapping
	public PagingVO<CommentsVO> list(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @PathVariable(name="boardSn", required=true) int boardSn
			) {
		CommentsVO detailSearch = new CommentsVO();
		
		detailSearch.setBoardSn(boardSn);
		
		PagingVO<CommentsVO> pagingVO = new PagingVO<>();
		pagingVO.setDetailSearch(detailSearch);
		
		pagingVO.setCurrentPage(currentPage);
		
		service.readReplyList(pagingVO);
		
		return pagingVO;
	}
	
	
	
}







