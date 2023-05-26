package kr.co.jobara.member.service;

import java.util.List;

import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.vo.EmemberVO;

/**
 * 장인슬
 * @author PC-17
 */
public interface EmemberService {

	/**
	 * 회원 가입
	 * @param emember
	 * @return {@link ServiceResult.PKDUPLICATED} {@link ServiceResult.OK}, {@link ServiceResult.FAIL}
	 */
	public ServiceResult createEmember(EmemberVO emember);
	
	/**
	 * 상세조회
	 * @param ememId
	 * @return 존재하지 않는다면, {@link PKNotFoundException} 발생.
	 */
	public EmemberVO retrieveEmember(String ememId);
	
	/**
	 * 아이디 존재여부 조회
	 * @param ememId
	 * @return
	 */
	public EmemberVO selectId(String ememId);
	
	/**
	 * 목록 조회
	 * @param pagingVO TODO
	 * @return 조건에 맞는 회원이 없으면, size==0
	 */
	public List<EmemberVO> retrieveEmemberList(PagingVO<EmemberVO> pagingVO);
	
	/**
	 * 회원정보 수정
	 * @param member
	 * @return {@link PKNotFoundException}, INVALIDPASSWORD, OK, FAIL
	 */
	public ServiceResult updateEmember(EmemberVO emember);
	
	/**
	 * 기업이름 검색을 통한 결과 목록
	 * @param ememNm
	 * @return
	 */
	public List<EmemberVO> searchResultCompany(String searchName);

}
