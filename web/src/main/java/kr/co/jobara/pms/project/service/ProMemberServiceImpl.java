package kr.co.jobara.pms.project.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.pms.project.dao.ProMemberDAO;
import kr.co.jobara.pms.project.vo.ProMemberVO;
import kr.co.jobara.proboard.vo.ProBoardVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProMemberServiceImpl implements ProMemberService {

	@Inject
	private ProMemberDAO proMemberDAO;	
	
	@Override
	public ServiceResult createMember(ProMemberVO proMember) {
		int rowcnt = 0;
		
		List<ProMemberVO> memberList = proMember.getProjectMemberList();
		
		for (ProMemberVO proMemberVO : memberList) {
			rowcnt = proMemberDAO.multiInsertProMember(proMemberVO);
		}
		
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}
	
	@Override
	public ServiceResult insertMemberListAdd(ProMemberVO proMember, String[] checkboxName) {
		int rowcnt = 0;
		
		for(String pmemId : checkboxName) {
			proMember.setPmemId(pmemId);
			
			log.info("proMember : " + proMember.toString());
			
			rowcnt = rowcnt + proMemberDAO.insertMemberListAdd(proMember);
		}
//		for (ProMemberVO proMemberVO : memberList) {
//			rowcnt = proMemberDAO.multiInsertProMember(proMemberVO);
//		}
		
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public ServiceResult modifyMember(ProMemberVO proMember) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult removeMember(ProMemberVO proMember) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProMemberVO retrieveProMember(String pmemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProMemberVO> retrieveProMemberList(PagingVO<ProMemberVO> pagingVO) {
		int totalRecord = proMemberDAO.selectTotalRecord(pagingVO);
		List<ProMemberVO> proMemberList = proMemberDAO.selectProMemberList(pagingVO);
		if(pagingVO != null) {
			pagingVO.setTotalRecord(totalRecord);
			pagingVO.setDataList(proMemberList);
		}
		return proMemberList;
	}

	@Override
	public void retrieveApplyMemberList(PagingVO<ProBoardVO> paging) {
		// TODO Auto-generated method stub
	}
}
