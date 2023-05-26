package kr.co.jobara.pms.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.pms.project.vo.CalendarVO;
import kr.co.jobara.pms.project.vo.ProCreateVO;
import kr.co.jobara.proboard.vo.ProBoardVO;

@Mapper
public interface ProCreateDAO {
	public ProCreateVO selectProject(int proSn);
	public List<CalendarVO> selectProjectListCom(String ememId);
	public List<CalendarVO> selectProjectList(String pmemId);
	public int insertProject(ProCreateVO project);
	public int updateProject(ProCreateVO project);
	public int deleteProject(ProCreateVO project);
	public int selectTotalRecord(PagingVO<ProCreateVO> paging);
	public List<ProBoardVO> selectProjApplyList(String ememId);
	public List<ProBoardVO> selectApplyMemList(int probSn);
	public List<ProCreateVO> selectProjListCom(String ememId);
}
