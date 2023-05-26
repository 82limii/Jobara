package kr.co.jobara.resume.controller;

import java.util.Enumeration;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.commons.service.AttatchService;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.service.PmemberService;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.portfolio.service.PortfolioService;
import kr.co.jobara.portfolio.vo.PortfolioVO;
import kr.co.jobara.resume.service.ResumeService;
import kr.co.jobara.resume.vo.ActivityVO;
import kr.co.jobara.resume.vo.CareerVO;
import kr.co.jobara.resume.vo.EducationVO;
import kr.co.jobara.resume.vo.LanguageVO;
import kr.co.jobara.resume.vo.LicenseVO;
import kr.co.jobara.resume.vo.ResumeVO;
import kr.co.jobara.resume.vo.SchoolVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 이상림
 * 최초작성 2022.03.13
 * 이력서 등록 컨트롤러
 */
@Slf4j
@Controller
@RequestMapping("/myPage")
public class ResumeInsertController {
	
	@Inject
	private ResumeService service;
	
	@Inject
	private PmemberService pmemberService;
	
	@Inject
	private AttatchService attatchService;
	
	@Inject
	private PortfolioService portfolioService;
	
	@Inject
	private LogService logService;
	
	@RequestMapping("/resumeInsert.do")
	public String form(
			Model model
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @SessionAttribute("authMember") PmemberVO auth
			, HttpServletRequest req
	) {
		Enumeration<String> parameterNames = req.getParameterNames();
		while(parameterNames.hasMoreElements()) {
			
			String nextElement = parameterNames.nextElement();
			System.out.println(nextElement + " : " + req.getParameter(nextElement));
		}
		
		String pmemId = auth.getPmemId();
		PmemberVO member = pmemberService.retrievePmember(pmemId);
		model.addAttribute("member", member);
		
		PagingVO<PortfolioVO> pagingVO = PortListToJson(pmemId, currentPage);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "resume/resumeForm";
	}
	
	@RequestMapping(value="/resumeInsert.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	private PagingVO<PortfolioVO> PortListToJson(String pmemId, int currentPage) {
		// pmemId 값 세션 가져오기
		PagingVO<PortfolioVO> pagingVO = new PagingVO<>(5,5);
		pagingVO.setPmemId(pmemId);
		
		// 현재 페이지 처리하기
		pagingVO.setCurrentPage(currentPage);
		
		// portfolio 리스트 가져오기
		portfolioService.retrievePortfolioList(pagingVO);
		
		return pagingVO;
	}

	@RequestMapping(value="/resumeInsert.do", method=RequestMethod.POST)
	public String process(
			@ModelAttribute ResumeVO resume
			, Model model
			, @SessionAttribute("authMember") PmemberVO auth
			,HttpServletRequest req
	) {
		
		/** 파라미터 검증 */ 
		if(resume!=null) {
			List<EducationVO> educationList = resume.getEducationList();
			if(educationList!=null) {
				for(int i = educationList.size()-1; i>=0; i--) {
					EducationVO vo = educationList.get(i);
					if(vo.validate()==false) {
						educationList.remove(i);
					}
				}
			}
			
			List<CareerVO> careerList = resume.getCareerList();
			if(careerList!=null) {
				for(int i = careerList.size()-1; i>=0; i--) {
					CareerVO vo = careerList.get(i);
					if(vo.validate()==false) {
						careerList.remove(i);
					}
				}
			}
			
			List<ActivityVO> activityList = resume.getActivityList();
			if(activityList!=null) {
				for(int i = activityList.size()-1; i>=0; i--) {
					ActivityVO vo = activityList.get(i);
					if(vo.validate()==false) {
						activityList.remove(i);
					}
					
				}
			}
			
			List<SchoolVO> schoolList = resume.getSchoolList();
			if(schoolList!=null) {
				for(int i = schoolList.size()-1; i>=0; i--) {
					SchoolVO vo = schoolList.get(i);
					if(vo.validate()==false) {
						schoolList.remove(i);
					}
				}
			}
			
			List<LanguageVO> languageList = resume.getLanguageList();
			if(languageList!=null) {
				for(int i = languageList.size()-1; i>=0; i--) {
					LanguageVO vo = languageList.get(i);
					if(vo.validate()==false) {
						languageList.remove(i);
					}
				}
			}
			
			List<LicenseVO> licenseList = resume.getLicenseList();
			if(licenseList!=null) {
				for(int i = licenseList.size()-1; i>=0; i--) {
					LicenseVO vo = licenseList.get(i);
					if(vo.validate()==false) {
						licenseList.remove(i);
					}
				}
			}
		}	// end 파라미터 검증
		
		String pmemId = auth.getPmemId();
		
		resume.setPmemId(pmemId);
		String viewName = null;
		ServiceResult result = null;
		// 학력정보의 첨부파일 등록과정
		List<EducationVO> educationList = resume.getEducationList();
		String biztype = "education";
		if(educationList!=null) {
			for (EducationVO vo : educationList) {
				MultipartFile[] files = vo.getEduFiles();
				String attSn = attatchService.processAttatchFile(files, biztype);
				vo.setAttSn(attSn);
			}	// end for
		}	// end if (education!=null)
		
		// 포트폴리오 파일로 등록시
		MultipartFile[] files = resume.getPortfolio().getPortfolioFiles();
		if (files!=null) {
			String biztypePort = "portfolio";
			String attSn = attatchService.processAttatchFile(files, biztypePort);
			if (attSn!=null) {
				PortfolioVO portfolio = new PortfolioVO();
				portfolio.setAttSn(attSn);
				result = portfolioService.createPortfolio(portfolio);
				if (ServiceResult.FAIL == result) {
					model.addAttribute("message", "서버오류");
					return "resume/resumeForm";
				}
				Integer portSn = portfolio.getPortSn();
				resume.setPortSn(portSn);
			}	// if (attSn!=null) 끝
		}	// if (files!=null) 끝
		
		// create
		result = service.createResume(resume);
		if(ServiceResult.OK == result) {
			// 로그
			StringBuffer url = req.getRequestURL();
			LogVO log = LogVO.builder().pmemId(pmemId).logAdd(url.toString()+"?what="+resume.getReSn()).build();
			logService.createLog(log);
			
			viewName = "redirect:/myPage/resumeView.do?what="+resume.getReSn();
		} else {
			model.addAttribute("message","서버오류");
			viewName = "resume/resumeForm";
		}
		return viewName;
	}
}
