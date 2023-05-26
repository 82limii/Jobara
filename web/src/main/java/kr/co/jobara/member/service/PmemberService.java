package kr.co.jobara.member.service;

import java.util.List;

import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.vo.PmemberVO;

/**
 * 장인슬
 * @author PC-17
 *
 */
public interface PmemberService {
	
	/**
	 * 회원 가입
	 * @param pmember
	 * @return {@link ServiceResult.PKDUPLICATED} {@link ServiceResult.OK}, {@link ServiceResult.FAIL}
	 */
	public ServiceResult createPmember(PmemberVO pmember);
	
	/**
	 * 상세조회
	 * @param pmemId
	 * @return 존재하지 않는다면, {@link PKNotFoundException} 발생.
	 */
	public PmemberVO retrievePmember(String pmemId);
	
	/**
	 * 회원정보 수정
	 * @param pmember
	 * @return {@link PKNotFoundException}, INVALIDPASSWORD, OK, FAIL
	 */
	public ServiceResult updatePmember(PmemberVO pmember);
	
	/**
	 * 목록 조회
	 * @param pagingVO TODO
	 * @return 조건에 맞는 회원이 없으면, size==0
	 */
	public List<PmemberVO> retrievePmemberList(PagingVO<PmemberVO> pagingVO); 

}
