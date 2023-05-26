package kr.co.jobara.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.empInfo.vo.EmpInfoVO;
import kr.co.jobara.member.vo.PmemberVO;

@Mapper
public interface PmemberDAO {
	
//	장인슬
	
	/**
	 * 식별자로 한사람의 사용자를 조회
	 * @param 조회할 사람의 아이디
	 * @return 존재하지 않는 경우, null 반환.
	 */
	public PmemberVO selectPmemberForAuth(String pmemId);
	
	/**
	 * 신규 회원 등록
	 * @param pmember
	 * @return > 0 : 성공
	 */
	public int insertPmember(PmemberVO pmember);
	
	/**
	 * 재직정보 등록
	 * @param empinfo
	 * @return
	 */
	public int insertEmpInfo(PmemberVO pmember);
	
	public int selectTotalRecord(PagingVO<PmemberVO> pagingVO);
	
	/**
	 * 회원 목록 조회
	 * @param pagingVO TODO
	 * @return 조건에 맞는 회원이 없는 경우, size == 0 반환.
	 */
	public List<PmemberVO> selectPmemberList(PagingVO<PmemberVO> pagingVO);
	
	/**
	 * 회원 한명의 상세 정보 조회
	 * @param pmemId
	 * @return 존재하지 않는 다면, null 반환.
	 */
	public PmemberVO selectPmember(String pmemId);
	
	/**
	 * 회원 정보 수정
	 * @param pmember
	 * @return > 0 : 수정 성공
	 */
	public int updatePmember(PmemberVO pmember);
	
	/**
	 * 개인회원 아이디찾기
	 * @param pmember
	 * @return
	 */
	public PmemberVO findIdPmem(PmemberVO pmember);
	
		
	/**
	 * 개인회원 비밀번호찾기
	 * @param pmember
	 * @return
	 */
	public PmemberVO findPwPmem(PmemberVO pmember);
	
	/**
	 * 개인회원 비밀번호 수정
	 * @return
	 */
	public void updatePassPmem(PmemberVO pmember);	
	

	
}
