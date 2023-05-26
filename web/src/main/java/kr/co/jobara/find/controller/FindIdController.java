package kr.co.jobara.find.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.find.service.FindIdService;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.member.vo.PmemberVO;

@Controller
@RequestMapping("/find")
public class FindIdController {
//	장인슬
	
	@Inject
	private FindIdService service;
	
	@Inject
	private LogService logService;
	
	@RequestMapping("/memberfindid.do")
	public String findId() {
		return "find/memberFindId";
	}
	
	@RequestMapping(value="pmemId", method=RequestMethod.POST)
	public String findIdPmem(Model model, PmemberVO inputData
			, HttpServletRequest req
	) {
		
		PmemberVO pmem = service.findIdPmem(inputData);
		
		if(pmem == null) {
			model.addAttribute("msg", "일치하는 정보가 존재하지 않습니다.");
			return "find/memberFindId";
			
		}else {
			// log
			String pmemId = pmem.getPmemId();
			StringBuffer url = req.getRequestURL();
			LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+pmemId).build();
			logService.createLog(log);

			String name = pmem.getPmemId();
			name = name.replaceAll(".{3}$", "***");
			model.addAttribute("id", name);
			return "find/findId";
		}
	}
	
	@RequestMapping(value="ememId", method=RequestMethod.POST)
	public String findIdEmem(Model model, EmemberVO inputData
			, HttpServletRequest req
	) {
		
		EmemberVO emem = service.findIdEmem(inputData);
		
		if(emem == null) {
			model.addAttribute("msg", "일치하는 정보가 존재하지 않습니다.");
			return "find/memberFindId";
			
		}else {
			// log
			String ememId = emem.getEmemId();
			StringBuffer url = req.getRequestURL();
			LogVO log = LogVO.builder().ememId(ememId).logAdd(url.toString()+"?what="+ememId).build();
			logService.createLog(log);

			String name = emem.getEmemId();
			name = name.replaceAll(".{3}$", "***");
			model.addAttribute("id", name);
			model.addAttribute("id", name);
			return "find/findId";
		}
	}
}
