package kr.co.jobara.incumbent.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.incumbent.service.IncumbentService;
import kr.co.jobara.member.vo.EmemberVO;

@Controller
@RequestMapping("/incumbent")
public class IncumbentDeleteController {

	@Inject
	private IncumbentService service;
	
	@Inject
	private LogService logService;
	
	@RequestMapping("com/incumbentDelete.do")
	public String qnaDeleteCom(
		@RequestParam(value="what", required=true) int empSn
		, Model model
		, HttpServletRequest req
		, @SessionAttribute("authMember") EmemberVO auth
	) {
		String viewName = null;
		ServiceResult result = service.removeIncumbent(empSn);
		if(ServiceResult.OK.equals(result)) {
			//log 
			String ememId = auth.getEmemId();
			StringBuffer url = req.getRequestURL();
			LogVO log = LogVO.builder().ememId(ememId).logAdd(url.toString()+"?what="+empSn).build();
			logService.createLog(log);
			
			viewName = "redirect:/incumbent/com/incumbentList.do";
		} else {
			model.addAttribute("message", "서버오류");
			viewName = "redirect:/incumbent/com/incumbentList.do";
		}
		
		return viewName;
	}
	
	
}
