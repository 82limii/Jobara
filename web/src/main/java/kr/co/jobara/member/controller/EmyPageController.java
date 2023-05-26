package kr.co.jobara.member.controller;

import java.io.File;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.MenuVO;
import kr.co.jobara.find.service.CallService;
import kr.co.jobara.member.service.EmemberService;
import kr.co.jobara.member.vo.EmemberVO;


/**
 * 장인슬
 * @author PC-17
 * 2022-03-02	
 */
@Controller
@RequestMapping("/member")
public class EmyPageController {
	@Inject
	private MenuService menuService;
	
	@Inject
	private EmemberService service;
	
	@Value("#{appInfo['member.attatch']}")
	private static File folder;
	
	@RequestMapping("com/emyPage.do")
	public String emyPage(@SessionAttribute(value="authMember", required=true) EmemberVO authMember, Model model) {
		//left 메뉴 가져오기
		List<MenuVO> menuList = menuService.retrieveMenu("compage_com");
		model.addAttribute("menuList", menuList);
		
		//authmember에 담긴 기록으로 회원정보 가져오기.
		EmemberVO detail = service.retrieveEmember(authMember.getEmemId());
		model.addAttribute("emember", detail);
		return "member/eMyPage";
	}
	
	@RequestMapping("com/eCall.do")
	public String call(Model model) {
		List<MenuVO> menuList = menuService.retrieveMenu("compage_com");
		model.addAttribute("menuList", menuList);
		
		return "member/eCall";
	}
	
	@GetMapping("eCall")
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

	
	@RequestMapping("admin/emyPage.do")
	public String emyPageAdmin() {
		return "admin/member/eMyPage";
	}
}
