package kr.co.jobara.commons.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.MainVO;
import kr.co.jobara.jobboard.vo.JobBoardVO;
import kr.co.jobara.proboard.vo.ProBoardVO;

@Mapper
public interface MainDAO {
	public List<JobBoardVO> selectMainJobList();
	
	public List<ProBoardVO> selectMainProList();
	
	public List<MainVO> selectReviewList1();
	public List<MainVO> selectReviewList2();
}
