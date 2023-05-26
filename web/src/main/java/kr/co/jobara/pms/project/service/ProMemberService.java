package kr.co.jobara.pms.project.service;

import java.util.List;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.pms.project.vo.ProMemberVO;
import kr.co.jobara.proboard.vo.ProBoardVO;

public interface ProMemberService {
	public ServiceResult createMember(ProMemberVO proMember);
	public ServiceResult modifyMember(ProMemberVO proMember);
	public ServiceResult removeMember(ProMemberVO proMember);
	public ProMemberVO retrieveProMember(String pmemId );
	public List<ProMemberVO> retrieveProMemberList(PagingVO<ProMemberVO> pagingVO);
	public void retrieveApplyMemberList(PagingVO<ProBoardVO> paging);
	public ServiceResult insertMemberListAdd(ProMemberVO proMember, String[] checkboxName);

}
