package kr.co.jobara.pms.project.service;

import java.util.List;

import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.pms.project.vo.CalendarVO;
import kr.co.jobara.pms.project.vo.ProCreateVO;
import kr.co.jobara.proboard.vo.ProBoardVO;

public interface ProCreateService {
	public ServiceResult createProject(ProCreateVO project);
	public ProCreateVO retrieveProject(int proSn);
	public List<CalendarVO> retrieveProjectListCom(String ememId);
	public List<CalendarVO> retrieveProjectList(String pmemId);
	public ServiceResult modifyProject(ProCreateVO project);
	public ServiceResult removeProject(ProCreateVO project);
	public List<ProBoardVO> retrieveProjApplyList(String ememId);
	public List<ProBoardVO> retrieveApplyMemList(int probSn);
	public List<ProCreateVO> retrieveProjListCom(String ememId);
}
