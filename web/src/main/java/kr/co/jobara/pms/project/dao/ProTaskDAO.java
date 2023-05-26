package kr.co.jobara.pms.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.pms.project.vo.GanttVO;
import kr.co.jobara.pms.project.vo.ProTaskVO;

@Mapper
public interface ProTaskDAO {
	public ProTaskVO selectTaskBoard(int protId);
	public List<ProTaskVO> selectTaskBoardList(PagingVO<ProTaskVO> paging);
	public int insertTaskBoard(ProTaskVO taskBoard);
	public int updateTaskBoard(ProTaskVO taskBoard);
	public int deleteTaskBoard(int protSn);
	public int selectTotalRecord(PagingVO<ProTaskVO> paging);
	public List<ProTaskVO> selectProMemList();
	public List<GanttVO> selectTaskList(String pmemId);
	public List<GanttVO> selectTaskListCom(String ememId);
}
