package kr.co.jobara.login.service;

import kr.co.jobara.admin.vo.AdminVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.member.vo.PmemberVO;

public interface LoginService {

	/**
	 * 개인회원 로그인 인증 로직
	 * @param inputData, 인증에 필요한 아이디와 비밀번호를 가진 객체 
	 * @return 존재하지 않는 사용자(NOTEXIST), 비밀번호 오류(INVALIDPASSWORD), 인증 성공(OK)
	 */
	public ServiceResult login(PmemberVO inputData);
	
	/**
	 * 기업회원 로그인 인증 로직	
	 * @param inputData, 인증에 필요한 아이디와 비밀번호를 가진 객체 
	 * @return 존재하지 않는 사용자(NOTEXIST), 비밀번호 오류(INVALIDPASSWORD), 인증 성공(OK)
	 */
	public ServiceResult elogin(EmemberVO inputData);
	
	/**
	 * 관리자 로그인 인증 로직	
	 * @param inputData, 인증에 필요한 아이디와 비밀번호를 가진 객체 
	 * @return 존재하지 않는 사용자(NOTEXIST), 비밀번호 오류(INVALIDPASSWORD), 인증 성공(OK)
	 */
	public ServiceResult alogin(AdminVO inputData);
}
