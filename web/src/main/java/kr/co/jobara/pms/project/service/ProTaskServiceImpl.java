package kr.co.jobara.pms.project.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.pms.project.dao.ProTaskDAO;
import kr.co.jobara.pms.project.vo.GanttVO;
import kr.co.jobara.pms.project.vo.ProTaskVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProTaskServiceImpl implements ProTaskService {

	@Inject
	private ProTaskDAO proTaskDAO;
	
	@Override
	public ServiceResult createTaskBoard(ProTaskVO taskBoard) {
		int rowcnt = proTaskDAO.insertTaskBoard(taskBoard);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public ProTaskVO retrieveTaskBoard(int protSn) {
		
		 ProTaskVO task = proTaskDAO.selectTaskBoard(protSn);
		 if(task == null) {
			 throw new PKNotFoundException(protSn + "글 없음");
		 }
		 return task;
	}

	@Override
	public void retrieveTaskBoardList(PagingVO<ProTaskVO> paging) {
		int totalRecord = proTaskDAO.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<ProTaskVO> dataList = proTaskDAO.selectTaskBoardList(paging);
		paging.setDataList(dataList);
	}

	@Override
	public ServiceResult modifyTaskBoard(ProTaskVO taskBoard) {
		int rowcnt = proTaskDAO.updateTaskBoard(taskBoard);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public ServiceResult removeTaskBoard(int protSn) {
		int rowcnt = proTaskDAO.deleteTaskBoard(protSn);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public List<ProTaskVO> retrieveProMemList() {
		List<ProTaskVO> memList = proTaskDAO.selectProMemList();
		return memList;
	}

	@Override
	public List<GanttVO> retrieveTaskList(String pmemId) {
		List<GanttVO> taskList = proTaskDAO.selectTaskList(pmemId);
		return taskList;
	}

	@Override
	public List<GanttVO> retrieveTaskListCom(String ememId) {
		List<GanttVO> taskList = proTaskDAO.selectTaskListCom(ememId);
		return taskList;
	}

}
