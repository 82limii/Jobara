package kr.co.jobara.info.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.info.vo.InfoVO;
import kr.co.jobara.jobboard.vo.JobBoardVO;


@Mapper
public interface InfoDAO {
	
	public InfoVO selectInfo(String ememId);
	
	public List<JobBoardVO> selectInfoList(PagingVO<JobBoardVO> pagingVO);
	
	public int selectTotalRecord(PagingVO<JobBoardVO> pagingVO);
}
