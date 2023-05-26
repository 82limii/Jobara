package kr.co.jobara.admin.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.jobboard.service.JobBoardService;
import kr.co.jobara.jobboard.vo.JobBoardVO;
import kr.co.jobara.proboard.service.ProBoardService;
import kr.co.jobara.proboard.vo.ProBoardVO;
import kr.co.jobara.statistics.service.StatisticsService;
import kr.co.jobara.statistics.vo.StatisticsVO;

/**
 * @author 이상림
 * 채용공고 목록 조회, 상세보기 => 관리자
 * 프로젝트 공고 목록 조회, 상세보기 => 관리자
 */
@Controller
@RequestMapping("/board")
public class BoardRetrieveController {
	@Inject
	private JobBoardService jobBoardService;
	@Inject
	private ProBoardService proBoardService;
	@Inject
	private StatisticsService staService;
	
	// 채용공고 관리 목록 조회
	@RequestMapping("admin/jobBoardList.do")
	public String jobBoardList(Model model
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("detailSearch") JobBoardVO detailSearch
	) {
		PagingVO<JobBoardVO> pagingVO = jobListToJson(currentPage, detailSearch);
		model.addAttribute("pagingVO", pagingVO);
		return "admin/board/jobboardList";
	}

	// 채용공고 관리 목록 json
	@RequestMapping(value="admin/jobBoardList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<JobBoardVO> jobListToJson(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("detailSearch") JobBoardVO detailSearch
			) {
		PagingVO<JobBoardVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailSearch(detailSearch);
		
		jobBoardService.retrieveJobBoardList(pagingVO);
		return pagingVO;
	}
	
	// 채용공고 관리 상세조회
	@RequestMapping("admin/jobBoardView.do")
	public String jobBoardView(
			@RequestParam(value="what", required=true) int jobSn
			, Model model
			) {
		JobBoardVO jobBoard = jobBoardService.retrieveJobBoard(jobSn);
		model.addAttribute("jobBoard", jobBoard);
		
		// 통계 - 학력정보
		String view = "job";
		List<StatisticsVO> eduSta = staService.retrieveEduList(jobSn, view);
		model.addAttribute("eduSta", eduSta);
		// 통계 - 경력정보
		List<StatisticsVO> carSta = staService.retrieveCarList(jobSn, view);
		model.addAttribute("carSta", carSta);
		// 지원자 수
		StatisticsVO appCntVO = staService.retrieveAppCnt(jobSn, view);
		model.addAttribute("appCntVO", appCntVO);
		if (appCntVO.getAppCnt()==0) {
			// 지원자가 없을경우 메세지
			StatisticsVO sta = StatisticsVO.builder().msg("지원자가 없습니다.").build();
			model.addAttribute("sta", sta);
		}
		
		return "admin/board/jobboardView";
	}

	
	// 프로젝트 공고 관리 목록 조회
	@RequestMapping("admin/proBoardList.do")
	public String proBoardList(
			Model model
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("detailSearch") ProBoardVO detailSearch
	) {
		PagingVO<ProBoardVO> pagingVO = proListToJson(currentPage, detailSearch);
		model.addAttribute("pagingVO", pagingVO);
		return "admin/board/proboardList";
	}
	
	// 프로젝트 공고 관리 목록 json
	@RequestMapping(value="admin/proBoardList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<ProBoardVO> proListToJson(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("detailSearch") ProBoardVO detailSearch
			) {
		PagingVO<ProBoardVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailSearch(detailSearch);
		
		proBoardService.retrieveProBoardList(pagingVO);
		return pagingVO;
	}

	// 프로젝트 관리 상세조회
	@RequestMapping("admin/proBoardView.do")
	public String proBoardView(
			@RequestParam(value="what", required=true) int probSn
			, Model model
	) {
		ProBoardVO proBoard = proBoardService.retrieveProBoard(probSn);
		model.addAttribute("proBoard", proBoard);
		
		// 통계 - 학력정보
		String view = "pro";
		List<StatisticsVO> eduSta = staService.retrieveEduList(probSn, view);
		model.addAttribute("eduSta", eduSta);
		// 통계 - 경력정보
		List<StatisticsVO> carSta = staService.retrieveCarList(probSn, view);
		model.addAttribute("carSta", carSta);
		// 지원자 수
		StatisticsVO appCntVO = staService.retrieveAppCnt(probSn, view);
		model.addAttribute("appCntVO", appCntVO);
		if (appCntVO.getAppCnt()==0) {
			// 지원자가 없을경우 메세지
			StatisticsVO sta = StatisticsVO.builder().msg("지원자가 없습니다.").build();
			model.addAttribute("sta", sta);
		}
				
		return "admin/board/proboardView";
	}
}
