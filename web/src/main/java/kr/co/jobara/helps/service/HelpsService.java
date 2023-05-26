package kr.co.jobara.helps.service;

import java.util.List;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.helps.vo.HelpsVO;

public interface HelpsService {
	
	public ServiceResult createHelps(HelpsVO helps);
	
	public ServiceResult modifyHelps(HelpsVO helps);
	
	public HelpsVO retrieveHelps(int helpsSn);
	
	public List<HelpsVO> retrieveHelpsList(PagingVO<HelpsVO> pagingVO);
	
	public List<HelpsVO> retrieveHelpsListAdmin(PagingVO<HelpsVO> pagingVO);
	
	/**
	 * 관리자 메인페이지용 문의 목록
	 * @return
	 */
	public List<HelpsVO> retrieveListForAdmin();
	
	/**
	 * 원글 번호를 이용해 답글 조회
	 * @param helpsSn2
	 * @return
	 */
	public HelpsVO retrieveHelpsAns(int helpsSn2);
	
	public ServiceResult removeHelps(int helpsSn);
	
}
