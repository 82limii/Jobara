package kr.co.jobara.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.member.vo.EmemberVO;

/**
 * @author PC-17
 * 장인슬
 */
@Mapper
public interface EmemberDAO {

	/**
	 * 식별자로 한사람의 사용자를 조회
	 * @param 조회할 사람의 아이디
	 * @return 존재하지 않는 경우, null 반환.
	 */
	public EmemberVO selectEmemberForAuth(String ememId);
	
	/**
	 * P_MEMBER, E_MEMBER 두테이블 ID 여부 조회
	 * @param ememId
	 * @return
	 */
	public EmemberVO selectId(String ememId);
	
	/**
	 * 기업 회원 등록
	 * @param emember
	 * @return > 0 : 성공
	 */
	public int insertEmember(EmemberVO emember);
	
	public int selectTotalRecord(PagingVO<EmemberVO> pagingVO);
	
	/**
	 * 회원 목록 조회
	 * @param pagingVO TODO
	 * @return 조건에 맞는 회원이 없는 경우, size == 0 반환.
	 */
	public List<EmemberVO> selectEmemberList(PagingVO<EmemberVO> pagingVO);
	
	/**
	 * 회원 한명의 상세 정보 조회
	 * @param ememId
	 * @return 존재하지 않는 다면, null 반환.
	 */
	public EmemberVO selectEmember(String ememId);
	
	
	/**
	 * 회원 정보 수정
	 * @param emember
	 * @return > 0 : 수정 성공
	 */
	public int updateEmember(EmemberVO emember);

	/**
	 * 기업회원 아이디찾기
	 * @param emember
	 * @return
	 */
	public EmemberVO findIdEmem(EmemberVO emember);
	
		
	/**
	 * 기업회원 비밀번호찾기
	 * @param emember
	 * @return
	 */
	public EmemberVO findPwEmem(EmemberVO emember);
	
	/**
	 * 기업회원 비밀번호 수정
	 * @return
	 */
	public void updatePassEmem(EmemberVO emember);
	
	/**
	 * 기업검색 목록 불러오기
	 * @param ememNm
	 * @return 검색 결과에 걸리는 기업회원 정보 리스트
	 */
	public List<EmemberVO> selectSearchList(String searchName);
	
	
}
