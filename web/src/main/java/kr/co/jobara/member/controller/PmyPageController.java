package kr.co.jobara.member.controller;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.MenuVO;
import kr.co.jobara.find.service.CallService;
import kr.co.jobara.member.service.PmemberService;
import kr.co.jobara.member.vo.PmemberVO;

/**
 * 장인슬
 * @author PC-17
 * 2022-02-28
 */
@Controller
@RequestMapping("/member")
public class PmyPageController {
	@Inject
	private MenuService menuService;
	
	@Inject
	private PmemberService service;
	
	@RequestMapping("pmyPage.do")
	public String pmyPage(@SessionAttribute(value="authMember", required=true) PmemberVO authMember, Model model) {
			
		// left메뉴 불러오기
		List<MenuVO> menuList = menuService.retrieveMenu("mypage_per");
		model.addAttribute("menuList", menuList);
		
		PmemberVO detail = service.retrievePmember(authMember.getPmemId());
		model.addAttribute("pmember", detail);
		return "member/pMyPage";
	}
	
	@RequestMapping("pCall.do")
	public String call(Model model) {
		List<MenuVO> menuList = menuService.retrieveMenu("mypage_per");
		model.addAttribute("menuList", menuList);
		
		return "member/pCall";
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
	
	@RequestMapping("admin/pmyPage.do")
	public String pmyPageAdmin() {
		return "admin/member/pMyPage";
	}
	
}
