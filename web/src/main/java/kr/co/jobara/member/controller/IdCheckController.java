package kr.co.jobara.member.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.service.EmemberService;
import kr.co.jobara.member.service.PmemberService;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.member.vo.PmemberVO;

@Controller
public class IdCheckController {

	@Inject
	private EmemberService ememService;
	
	@Inject
	private PmemberService pmemService;
	
	@RequestMapping("/member/idCheck.do")
	public String idCheck(String inputId, Model model) {
		
		ServiceResult result;
		
		try {
		
			EmemberVO cnt = ememService.selectId(inputId);

			if(cnt.getRes()==0) { 
				  result = ServiceResult.OK;
			}else {
				  result = ServiceResult.PKDUPLICATED;
			}
			
		}catch (PKNotFoundException e){
			result = ServiceResult.FAIL;
		}
		model.addAttribute("result", result);
		return "jsonView";
	}
	
}
