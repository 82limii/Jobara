package kr.co.jobara.pms.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.pms.project.vo.ProMemberVO;
import kr.co.jobara.proboard.vo.ProBoardVO;

@Mapper
public interface ProMemberDAO {
	public int selectTotalRecord(PagingVO<ProMemberVO> pagingVO);
	public int insertProMember(ProMemberVO proMember);
	public int deleteProMember(ProMemberVO proMember);
	public List<ProMemberVO> selectProMemberList(PagingVO<ProMemberVO> proMember);
	public ProMemberVO selectProMember(String pmemId);
	public List<ProBoardVO> selectApplyMemberList(int probSn);
	public int multiInsertProMember(ProMemberVO proMemberVO);
	public int insertMemberListAdd(ProMemberVO proMemberVO);
}
