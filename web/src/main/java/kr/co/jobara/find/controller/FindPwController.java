package kr.co.jobara.find.controller;

import java.util.Random;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.find.service.CallService;
import kr.co.jobara.find.service.FindPwService;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.member.vo.PmemberVO;
import lombok.extern.slf4j.Slf4j;

//장인슬
@Controller
@RequestMapping("/find")
@Slf4j
public class FindPwController {

	@Inject
	private FindPwService service;
	
	@Inject
	private LogService logService;

	@RequestMapping("/memberfindpw.do")
	public String findPw() {
		return "find/memberFindPw";
	}
	@RequestMapping("/updatePwEmem.do")
	public String updateEPw() {
		return "find/ememUpdatePw";
	}
	@RequestMapping("/updatePwPmem.do")
	public String updatePPw() {
		return "find/pmemUpdatePw";
	}

	@RequestMapping(value = "ememPw", method = RequestMethod.POST)
	public String findPwEmem(Model model, EmemberVO inputData) {

		EmemberVO emem = service.findPwEmem(inputData);


		if (emem == null) {
			model.addAttribute("msg", "일치하는 정보가 존재하지 않습니다.");
			return "find/memberFindPw";

		} else {
			//임시비밀번호를 발급한다
			model.addAttribute("id", emem.getEmemId());
			return "find/ememUpdatePw";
		}
	}

	@RequestMapping(value = "updatePwEmem", method = RequestMethod.POST)
	public String updatePassAction(@RequestParam(value = "id", defaultValue = "", required = false) String id
			, EmemberVO inputData
			, HttpServletRequest req
	) {
		inputData.setEmemId(id);

		// log
		StringBuffer url = req.getRequestURL();
		LogVO log = LogVO.builder().ememId(id).logAdd(url.toString()+"?what="+id).build();
		logService.createLog(log);
		
		System.out.println(inputData);
		service.updatePassEmem(inputData);
		return "login/loginForm";
	}

	@RequestMapping(value = "pmemPw", method = RequestMethod.POST)
	public String findPwpmem(Model model, PmemberVO inputData) {

		PmemberVO pmem = service.findPwPmem(inputData);

		if (pmem == null) {
			model.addAttribute("msg", "일치하는 정보가 존재하지 않습니다.");
			return "find/memberFindPw";

		} else {
			model.addAttribute("id", pmem.getPmemId());
			return "find/pmemUpdatePw";
		}
	}
	
	@GetMapping("pCall")
    public @ResponseBody String sendP(String memTel) {

        Random rand  = new Random();
        String ranNum = "";
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            ranNum+=ran;
        }

        System.out.println("수신자 번호 : " + memTel);
        System.out.println("인증번호 : " + ranNum);
        CallService.pCall(memTel,ranNum);
        return ranNum;
    }

	@RequestMapping(value="updatePwPmem", method=RequestMethod.POST)
	public String updatePassAction(@RequestParam(value="id", defaultValue="", required=false) String id
			, PmemberVO inputData
			, HttpServletRequest req
	) {
		inputData.setPmemId(id);
		
		//log
		StringBuffer url = req.getRequestURL();
		LogVO log = LogVO.builder().pmemId(id).logAdd(url.toString()+"?what="+id).build();
		logService.createLog(log);
		
		System.out.println(inputData);
		service.updatePassPmem(inputData);
		return "login/loginForm";
	}
}
