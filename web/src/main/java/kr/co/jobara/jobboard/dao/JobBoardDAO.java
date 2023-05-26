package kr.co.jobara.jobboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.jobboard.vo.JobBoardVO;

/**
 * @author 이상림
 * 최초작성 2022.02.22
 * 채용공고 관리 persistence layer
 */
@Mapper
public interface JobBoardDAO {
	/**
	 * 공고 등록
	 * @param jobBoard
	 * @return > 0 : OK
	 */
	public int insertJobBoard(JobBoardVO jobBoard);
	
	/**
	 * 목록 조회
	 * @param paging
	 * @return
	 */
	public List<JobBoardVO> selectJobBoardList(PagingVO<JobBoardVO> pagingVO);
	public int selectTotalRecord(PagingVO<JobBoardVO> pagingVO);
	
	/**
	 * 상세조회
	 * @param jobSn : 채용공고 글번호
	 * @return
	 */
	public JobBoardVO selectJobBoard(int jobSn);
	
	/**
	 * 기업별 공고 목록 조회
	 * @param pagingVO, ememId가 null이면 안됨
	 * @return
	 */
	public List<JobBoardVO> selectMyJobBoard(PagingVO<JobBoardVO> pagingVO);
	public int selectMyTotalRecord(PagingVO<JobBoardVO> pagingVO);
	
	/**
	 * 공고 수정
	 * @param jobBoard
	 * @return
	 */
	public int updateJobBoard(JobBoardVO jobBoard);
}
