package kr.co.jobara.fitme.faceMatching.controller;

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

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.fitme.faceMatching.service.FaceMatchingService;
import kr.co.jobara.fitme.faceMatching.vo.FaceMatchingVO;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.validate.hints.InsertGroup;

/**
 * 작성일 2022.03.22
 * @author 김승현
 * 기업추천(CRU)용 InsertController
 */
@Controller
@RequestMapping("/fitme")
public class FaceMatchingInsertController {
	
	@Inject
	private FaceMatchingService service;
	
	@Inject
	private LogService logService;
	
	@RequestMapping(value="faceMatchingInsert.do", method=RequestMethod.GET)
	public String process(
			@Validated(InsertGroup.class) @ModelAttribute("faceMatching") FaceMatchingVO faceMatching
			, BindingResult errors
			, Model model
			, @SessionAttribute("authMember") PmemberVO auth 
			, HttpServletRequest req
//			, @RequestParam(value="what", required=true) String pmemId
	) {
		String viewName = null;
		String pmemId = auth.getPmemId();
		faceMatching.setPmemId(pmemId);
		if(!errors.hasErrors()) {
			ServiceResult result = service.createFaceMatching(faceMatching);
			if(ServiceResult.OK == result) {
				// log
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+pmemId).build();
				logService.createLog(log);
				
				viewName = "redirect:/fitme/faceMatching.do";
			} else {
				model.addAttribute("message", "서버오류");
				viewName = "fitme/faceMatching";
			}
		} else {
			viewName = "fitme/faceMatching";
		}
		return viewName;
	}
	

}
