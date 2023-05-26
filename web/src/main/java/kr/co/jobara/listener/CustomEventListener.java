package kr.co.jobara.listener;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import kr.co.jobara.commons.service.CommonCodeService;
import kr.co.jobara.listener.event.LoginSuccessEvent;
import kr.co.jobara.member.vo.PmemberVO;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomEventListener {
	@Inject
	private WebApplicationContext context;
	private ServletContext application;
	
	@Inject
	private CommonCodeService service;
	
	@PostConstruct
	public void init() {
		application = context.getServletContext();		
	}
	
	@EventListener(ContextRefreshedEvent.class)
	public void initializer(ContextRefreshedEvent event) {
		application.setAttribute("cPath", application.getContextPath());
		application.setAttribute("userList", new LinkedHashSet<>());
		
		// 학력 구분코드
		application.setAttribute("eduList", service.retrieveMiddleCodeList("EDU"));
		// 경력 구분코드
		application.setAttribute("carList", service.retrieveMiddleCodeList("CAR"));
		// 고용형태 구분코드
		application.setAttribute("empList", service.retrieveMiddleCodeList("EMP"));
		// 직급 구분코드
		application.setAttribute("posiList", service.retrieveMiddleCodeList("POS"));
		// 직무 구분코드
		application.setAttribute("deptList", service.retrieveMiddleCodeList("DEP"));
		// 지원방법 구분코드
		application.setAttribute("wayList", service.retrieveMiddleCodeList("WAY"));
		// 기술스택 분류코드
		application.setAttribute("staDivList", service.retrieveMiddleCodeList("STA"));
		// 기술스택 코드
		application.setAttribute("stackList", service.retrieveSmallCodeList("STA"));
		// 지역대분류 구분코드
		application.setAttribute("cityList", service.retrieveCityList());
		// 지역 구분코드
		application.setAttribute("locList", service.retrieveLocationList());
		// 중요도 구분코드
		application.setAttribute("impList", service.retrieveMiddleCodeList("IMP"));
		// 진행상태 코드
		application.setAttribute("ingList", service.retrieveMiddleCodeList("ING"));
		// 후기 구분코드
		application.setAttribute("revList", service.retrieveMiddleCodeList("REV"));
		// 성별 구분코드
		application.setAttribute("genList", service.retrieveMiddleCodeList("GEN"));
		// 재직상태 코드
		application.setAttribute("tenList", service.retrieveMiddleCodeList("TEN"));
		// 문의종류 코드
		application.setAttribute("helList", service.retrieveMiddleCodeList("HEL"));
		// 교육 구분코드
		application.setAttribute("schList", service.retrieveMiddleCodeList("SCH"));
		// 입학상태 코드
		application.setAttribute("entList", service.retrieveMiddleCodeList("ENT"));
		// 졸업상태 코드
		application.setAttribute("graList", service.retrieveMiddleCodeList("GRA"));
		// 활동 구분코드
		application.setAttribute("actList", service.retrieveMiddleCodeList("ACT"));
		// 어학 구분코드
		application.setAttribute("langList", service.retrieveMiddleCodeList("LAN"));
		// 회화능력 구분코드
		application.setAttribute("conList", service.retrieveMiddleCodeList("CON"));
		// 게시판 유형 코드
		application.setAttribute("dvtList", service.retrieveMiddleCodeList("DVT"));
		// 인증 재직 구분 코드
		application.setAttribute("resList", service.retrieveMiddleCodeList("RES"));
		// 난이도 구분 코드
		application.setAttribute("highList", service.retrieveMiddleCodeList("HIGH"));
		// 년도 구분 코드
		application.setAttribute("yrList", service.retrieveMiddleCodeList("YR"));
		// 별점 구분 코드
		application.setAttribute("ratList", service.retrieveMiddleCodeList("RAT"));
		// 질문 구분 코드
		application.setAttribute("faqList", service.retrieveMiddleCodeList("FAQ"));
		
		log.info("context refreshed, make cPath : {}", application.getContextPath());
	}
	
	@EventListener(LoginSuccessEvent.class)
	public void loginSuccessHandler(LoginSuccessEvent event) {
		PmemberVO user = event.getTarget();
		Set<PmemberVO> userList = (Set<PmemberVO>) application.getAttribute("userList");
		userList.add(user);
		log.info("{} 로그인 성공", user.getPmemNm());
	}
	
}















