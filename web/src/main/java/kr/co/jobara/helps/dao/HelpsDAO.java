package kr.co.jobara.helps.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.helps.vo.HelpsVO;

@Mapper
public interface HelpsDAO {
	
	/**
	 * 1:1문의 게시글 등록(회원)
	 * 1:1문의 게시글 답변 등록(관리자)
	 * @return
	 */
	public int insertHelps(HelpsVO helps);
	
	/**
	 * 1:1문의 게시글 수정
	 * @return
	 */
	public int updateHelps(HelpsVO helps);
	
	public int selectTotalRecord(PagingVO<HelpsVO> pagingVO);
	
	public int selectTotalRecordAdmin(PagingVO<HelpsVO> pagingVO);
	
	/**
	 * 1:1문의 리스트 조회
	 * @return
	 */
	public List<HelpsVO> selectHelpsList(PagingVO<HelpsVO> pagingVO);
	
	public List<HelpsVO> selectHelpsListAdmin(PagingVO<HelpsVO> pagingVO);
	
	/**
	 * 1:1문의 게시글 상세내용 조회
	 * @return
	 */
	public HelpsVO selectHelps(int helpsSn);
	
	/**
	 * 관리자 메인페이지용 문의 목록
	 * @return
	 */
	public List<HelpsVO> selectListForAdmin();
	
	/**
	 * 원글 번호를 이용해 답글 조회
	 * @param helpsSn2
	 * @return
	 */
	public HelpsVO selectHelpsAns(int helpsSn2);
	
	public int deleteHelps(int helpsSn);
	
}
