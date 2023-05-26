//package kr.co.jobara.commons.controller;
//
//import java.util.List;
//
//import javax.inject.Inject;
//
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ModelAttribute;
//
//import kr.co.jobara.commons.service.CommonCodeService;
//import kr.co.jobara.commons.vo.CommonCodeVO;
//import kr.co.jobara.commons.vo.LocationVO;
//
//@ControllerAdvice("kr.co.jobara")
//public class CommonCodeController {
//	@Inject
//	private CommonCodeService service;
//	
//	// 학력 구분코드
//	@ModelAttribute("eduList")
//	public List<CommonCodeVO> eduList() {
//		return service.retrieveMiddleCodeList("EDU");
//	}
//	// 경력 구분코드
//	@ModelAttribute("carList")
//	public List<CommonCodeVO> carList() {
//		return service.retrieveMiddleCodeList("CAR");
//	}
//	// 고용형태 구분코드
//	@ModelAttribute("empList")
//	public List<CommonCodeVO> empList() {
//		return service.retrieveMiddleCodeList("EMP");
//	}
//	// 직급 구분코드
//	@ModelAttribute("posiList")
//	public List<CommonCodeVO> posiList() {
//		return service.retrieveMiddleCodeList("POS");
//	}
//	// 직무 구분코드
//	@ModelAttribute("deptList")
//	public List<CommonCodeVO> deptList() {
//		return service.retrieveMiddleCodeList("DEP");
//	}
//	// 지원방법 구분코드
//	@ModelAttribute("wayList")
//	public List<CommonCodeVO> wayList() {
//		return service.retrieveMiddleCodeList("WAY");
//	}
//	// 기술스택 분류코드
//	@ModelAttribute("staDivList")
//	public List<CommonCodeVO> staDivList() {
//		return service.retrieveMiddleCodeList("STA");
//	}
//	// 기술스택 코드
//	@ModelAttribute("stackList")
//	public List<CommonCodeVO> stackList() {
//		return service.retrieveSmallCodeList("STA");
//	}
//	// 지역대분류 구분코드
//	@ModelAttribute("cityList")
//	public List<LocationVO> cityList() {
//		return service.retrieveCityList();
//	}
//	// 지역 구분코드
//	@ModelAttribute("locList")
//	public List<LocationVO> locList() {
//		return service.retrieveLocationList();
//	}
//	
//	// 중요도 구분코드
//	@ModelAttribute("impList")
//	public List<CommonCodeVO> impList() {
//		return service.retrieveMiddleCodeList("IMP");
//	}
//	// 진행상태 코드
//	@ModelAttribute("ingList")
//	public List<CommonCodeVO> ingList() {
//		return service.retrieveMiddleCodeList("ING");
//	}
//	
//	// 후기 구분코드
//	@ModelAttribute("revList")
//	public List<CommonCodeVO> revList() {
//		return service.retrieveMiddleCodeList("REV");
//	}
//	
//	//성별 구분코드
//	@ModelAttribute("genList")
//	public List<CommonCodeVO> genList() {
//		return service.retrieveMiddleCodeList("GEN");
//	}	
//	//재직상태 코드
//	@ModelAttribute("tenList")
//	public List<CommonCodeVO> tenList() {
//		return service.retrieveMiddleCodeList("TEN");
//	}	
//	
//	// 문의종류 코드
//	@ModelAttribute("helList")
//	public List<CommonCodeVO> helList() {
//		return service.retrieveMiddleCodeList("HEL");
//	}	
//	
//	// 
//	@ModelAttribute("faqList")
//	public List<CommonCodeVO> faqList() {
//		return service.retrieveMiddleCodeList("FAQ");
//	}	
//	// 학교 구분코드
//	@ModelAttribute("schList")
//	public List<CommonCodeVO> schList() {
//		return service.retrieveMiddleCodeList("SCH");
//	}	
//	// 입학상태 코드
//	@ModelAttribute("entList")
//	public List<CommonCodeVO> entList() {
//		return service.retrieveMiddleCodeList("ENT");
//	}	
//	
//	// 졸업상태 코드
//	@ModelAttribute("graList")
//	public List<CommonCodeVO> graList() {
//		return service.retrieveMiddleCodeList("GRA");
//	}	
//	// 활동 구분코드
//	@ModelAttribute("actList")
//	public List<CommonCodeVO> actList() {
//		return service.retrieveMiddleCodeList("ACT");
//	}	
//	// 어학 구분코드
//	@ModelAttribute("langList")
//	public List<CommonCodeVO> langList() {
//		return service.retrieveMiddleCodeList("LAN");
//	}	
//	// 회화능력 구분코드
//	@ModelAttribute("conList")
//	public List<CommonCodeVO> conList() {
//		return service.retrieveMiddleCodeList("CON");
//	}	
//	// 게시판 유형 코드
//	@ModelAttribute("dvtList")
//	public List<CommonCodeVO> dvtList() {
//		return service.retrieveMiddleCodeList("DVT");
//	}
//	
//	// 인증 재직 구분 코드
//	@ModelAttribute("resList")
//	public List<CommonCodeVO> resList() {
//		return service.retrieveMiddleCodeList("RES");
//	}
//	
//	// 난도 구분 코드
//	@ModelAttribute("highList")
//	public List<CommonCodeVO> highList() {
//		return service.retrieveMiddleCodeList("HIGH");
//	}
//	
//	// 년도 구분 코드
//	@ModelAttribute("yrList")
//	public List<CommonCodeVO> yrList() {
//		return service.retrieveMiddleCodeList("YR");
//	}
//	
//	// 별점 구분 코드
//	@ModelAttribute("ratList")
//	public List<CommonCodeVO> ratList() {
//		return service.retrieveMiddleCodeList("RAT");
//	}
//}
