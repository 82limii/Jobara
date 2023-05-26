package kr.co.jobara.applicant.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.incumbent.vo.IncumbentVO;
import kr.co.jobara.jobboard.vo.JobBoardVO;
import kr.co.jobara.proboard.vo.ProBoardVO;

/**
 * @author 전영준
 * 지원자 관리 DAO
 */
@Mapper
public interface ApplicantDAO {
	
	/**
	 * 채용 지원자 목록 조회(진행중인 공고)
	 * @param pagingVO
	 * @return
	 */
	public List<JobBoardVO> selectJobApplicantList(PagingVO<JobBoardVO> pagingVO);
	
	/**
	 * 채용 지원자 목록 조회(마감된 공고)
	 * @param pagingVO
	 * @return
	 */
	public List<JobBoardVO> selectJobApplicantListEnd(PagingVO<JobBoardVO> pagingVO);
	
	/**
	 * 프로젝트 지원자 목록 조회(진행중인 공고)
	 * @param pagingVO
	 * @return
	 */
	public List<ProBoardVO> selectProApplicantList(PagingVO<ProBoardVO> pagingVO);
	
	/**
	 * 프로젝트 지원자 목록 조회(마감된 공고)
	 * @param pagingVO
	 * @return
	 */
	public List<ProBoardVO> selectProApplicantListEnd(PagingVO<ProBoardVO> pagingVO);
	
	public int selectJobTotalRecord(PagingVO<JobBoardVO> pagingVO);
	
	public int selectProTotalRecord(PagingVO<ProBoardVO> pagingVO);
	
	public List<JobBoardVO> selectJobApplicantManList(PagingVO<JobBoardVO> pagingVO);
	
	public JobBoardVO selectJobApplicant(int jobSn);
	
	public int selectJobManTotalRecord(PagingVO<JobBoardVO> pagingVO);
	
	public List<ProBoardVO> selectProApplicantManList(PagingVO<ProBoardVO> pagingVO);
	
	public ProBoardVO selectProApplicant(int probSn);
	
	public int selectProManTotalRecord(PagingVO<ProBoardVO> pagingVO);
	
	/**
	 * 재직자 등록
	 * @param incumbent
	 * @return
	 */
	public int insertIncumbent(IncumbentVO incumbent);
	
	/**
	 * 지원자 삭제
	 * @param appSn
	 * @return
	 */
	public int deleteApplicantMan(int appSn);
	
	public JobBoardVO selectJobApplicantMan(int reSn);
	
	public ProBoardVO selectProApplicantMan(int reSn);
	
}
