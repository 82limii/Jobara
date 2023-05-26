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

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.contact.service.ContactService;
import kr.co.jobara.contact.vo.ContactVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.validate.hints.UpdateGroup;

@Controller
@RequestMapping("project/com/projPhoneNumUpdate.do")
public class ProjectNumUpdateController {

	@Inject
	private ContactService service;

	@Inject
	private LogService logService;

	@RequestMapping
	public String contactUpdateForm(@RequestParam(value = "what", required = true) int contacSn, Model model) {
		ContactVO contact = service.retrieveContact(contacSn);
		model.addAttribute("contact", contact);
		return "project/projectNumForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String contactUpdateProcess(@Validated(UpdateGroup.class) @ModelAttribute("contact") ContactVO contact,
			Errors errors, Model model, @SessionAttribute("authMember") EmemberVO auth, HttpServletRequest req) {
		String ememId = auth.getEmemId();
		contact.setEmemId(ememId);
		String viewName = null;
		if (errors.hasErrors()) {
			viewName = "project/projectNumForm";
		} else {
			ServiceResult result = service.modifyContactPro(contact);
			switch (result) {
			case FAIL:
				viewName = "project/projectNumForm";
				model.addAttribute("message", "서버 오류");
				break;

			default:
				// log
				StringBuffer url = req.getRequestURL();
				LogVO log = LogVO.builder().ememId(ememId).logAdd(url.toString() + "?what=" + contact.getContacSn())
						.build();
				logService.createLog(log);

				viewName = "redirect:/project/com/projPhoneNumView.do?what=" + contact.getContacSn();
				break;
			}
		}

		return viewName;
	}
}
