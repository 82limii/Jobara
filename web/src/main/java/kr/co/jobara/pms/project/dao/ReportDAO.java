package kr.co.jobara.pms.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.pms.project.vo.ReportVO;

@Mapper
public interface ReportDAO {
	
	public ReportVO selectReport(int repoSn);
	public List<ReportVO> selectReportList(PagingVO<ReportVO> paging);
	public int insertReport(ReportVO report);
	public int updateReport(ReportVO report);
	public int deleteReport(ReportVO report);
	public int selectTotalRecord(PagingVO<ReportVO> paging);
	
	
}
