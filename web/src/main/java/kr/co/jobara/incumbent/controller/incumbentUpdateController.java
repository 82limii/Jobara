package kr.co.jobara.incumbent.controller;

import java.util.List;

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

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.MenuVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.incumbent.service.IncumbentService;
import kr.co.jobara.incumbent.vo.IncumbentVO;
import kr.co.jobara.member.vo.EmemberVO;

@Controller
@RequestMapping("/incumbent/com/incumbentUpdate.do")
public class incumbentUpdateController {
	@Inject
	private MenuService menuService;
	
	@Inject
	private IncumbentService service;
	
	@Inject
	private LogService logService;
	
	//재직자 수정
	@RequestMapping
	public String incumbentUpdateForm(
		@RequestParam(value="what", required=true) int empSn
		, Model model
	) {
		IncumbentVO incumbent = service.retrieveIncumbent(empSn);
		model.addAttribute("incumbent", incumbent);
		return "incumbent/incumbentEdit";
	}
	
	//재직자 수정
	@RequestMapping(method=RequestMethod.POST)
	public String incumbentUpdateProcess(
		@Validated @ModelAttribute("incumbent") IncumbentVO incumbent
		, Errors errors
		, Model model
		, HttpServletRequest req
		, @SessionAttribute("authMember") EmemberVO auth
	) {
		String viewName = null;
		if(errors.hasErrors()) {
			viewName = "incumbent/incumbentEdit";
		}else {
			ServiceResult result = service.modifyIncumbent(incumbent);
			switch (result) {
			case FAIL:
				viewName = "incumbent/incumbentEdit";
				model.addAttribute("message", "서버 오류");
				break;

			default:
				// log
				String ememId = auth.getEmemId();
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().ememId(ememId).logAdd(url.toString()+"?what="+incumbent.getEmpSn()).build();
				logService.createLog(log);
				
				viewName = "redirect:/incumbent/com/incumbentView.do?what="+incumbent.getEmpSn();
				break;
			}
		}
		
		List<MenuVO> menuList = menuService.retrieveMenu("compage_com");
		model.addAttribute("menuList", menuList);
		
		return viewName;
	}
}
