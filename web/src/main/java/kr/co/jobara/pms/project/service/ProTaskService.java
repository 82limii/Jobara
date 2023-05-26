package kr.co.jobara.pms.project.service;

import java.util.List;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.pms.project.vo.GanttVO;
import kr.co.jobara.pms.project.vo.ProTaskVO;

public interface ProTaskService {
	public ServiceResult createTaskBoard(ProTaskVO taskBoard);
	public ProTaskVO retrieveTaskBoard(int protSn);
	public void retrieveTaskBoardList(PagingVO<ProTaskVO> paging);
	public ServiceResult modifyTaskBoard(ProTaskVO taskBoard);
	public ServiceResult removeTaskBoard(int protSn);
	public List<ProTaskVO> retrieveProMemList();
	public List<GanttVO> retrieveTaskList(String pmemId);
	public List<GanttVO> retrieveTaskListCom(String ememId);
}
