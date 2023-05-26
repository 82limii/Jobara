package kr.co.jobara.commons.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.commons.dao.MainDAO;
import kr.co.jobara.commons.vo.MainVO;
import kr.co.jobara.jobboard.vo.JobBoardVO;
import kr.co.jobara.proboard.vo.ProBoardVO;

@Service
public class MainService {
	@Inject
	private MainDAO dao;
	
	public List<JobBoardVO> mainJobBoard() {
		List<JobBoardVO> jobList = dao.selectMainJobList();
		return jobList;
	}
	
	public List<ProBoardVO> mainProBoard() {
		List<ProBoardVO> proList = dao.selectMainProList();
		return proList;
	}
	
	public List<MainVO> mainReview1() {
		List<MainVO> reviewList1 = dao.selectReviewList1();
		return reviewList1;
	}
	public List<MainVO> mainReview2() {
		List<MainVO> reviewList2 = dao.selectReviewList2();
		return reviewList2;
	}
	
}
