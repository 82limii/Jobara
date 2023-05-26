package kr.co.jobara.pms.project.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.pms.project.dao.ProCreateDAO;
import kr.co.jobara.pms.project.dao.ProMemberDAO;
import kr.co.jobara.pms.project.vo.CalendarVO;
import kr.co.jobara.pms.project.vo.ProCreateVO;
import kr.co.jobara.pms.project.vo.ProMemberVO;
import kr.co.jobara.proboard.vo.ProBoardVO;

@Service
public class ProCreateServiceImpl implements ProCreateService {
	
	@Inject
	private ProCreateDAO proCreateDAO;
	
	@Inject
	private ProMemberDAO proMemberDAO;

	@Override
	public ServiceResult createProject(ProCreateVO project) {
		//1) PRO_CREATE 에 INSERT
		int rownum = proCreateDAO.insertProject(project);
		
		//2) MEMBER 에 다중 INSERT
		List<ProMemberVO> projectMemberList = project.getProjectMemberList();
		
		if(projectMemberList != null) {
			for(ProMemberVO proMemberVO : projectMemberList) {
				//proSn처리하기!
				proMemberDAO.multiInsertProMember(proMemberVO);
			}
		}
		
		return rownum > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public ProCreateVO retrieveProject(int proSn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CalendarVO> retrieveProjectListCom(String ememId) {
		List<CalendarVO> proList = proCreateDAO.selectProjectListCom(ememId);
		return proList;
	}
	
	@Override
	public List<CalendarVO> retrieveProjectList(String pmemId) {
		List<CalendarVO> proList = proCreateDAO.selectProjectList(pmemId);
		return proList;
	}

	@Override
	public ServiceResult modifyProject(ProCreateVO project) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult removeProject(ProCreateVO project) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProBoardVO> retrieveProjApplyList(String ememId) {
		return proCreateDAO.selectProjApplyList(ememId);
	}

	@Override
	public List<ProBoardVO> retrieveApplyMemList(int probSn) {
		return proCreateDAO.selectApplyMemList(probSn);
	}

	@Override
	public List<ProCreateVO> retrieveProjListCom(String ememId) {
		List<ProCreateVO> projListCom = proCreateDAO.selectProjListCom(ememId);
		return projListCom;
	}


}
