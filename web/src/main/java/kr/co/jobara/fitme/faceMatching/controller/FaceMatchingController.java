package kr.co.jobara.fitme.faceMatching.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.MenuVO;
import kr.co.jobara.fitme.faceMatching.service.FaceMatchingService;
import kr.co.jobara.fitme.faceMatching.vo.FaceMatchingVO;
import kr.co.jobara.member.vo.PmemberVO;

/**
 * 작성일 2022.03.22
 * @author 김승현
 * 기업추천(CRU)용 selectController
 */
@Controller
@RequestMapping("/fitme")
public class FaceMatchingController {
	
	@Inject
	private FaceMatchingService service;
	
	@Inject
	private MenuService menuService;


	@RequestMapping("faceMatching.do")
	public String pmyPage(
			@SessionAttribute(value="authMember", required=true) PmemberVO auth
			, Model model
			, @ModelAttribute("faceMatching") FaceMatchingVO faceMatching
			) {
		String pmemId = auth.getPmemId();
		faceMatching.setPmemId(pmemId);
		List<MenuVO> menuList = menuService.retrieveMenu("mypage_per");
		model.addAttribute("menuList", menuList);
		FaceMatchingVO faceMatch =  service.retrieveFaceMatching(pmemId);
		model.addAttribute("faceMatch", faceMatch);
		return "fitme/faceMatching";
	}
}
