package kr.co.jobara.apply.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.apply.vo.ApplyVO;
import kr.co.jobara.commons.vo.PagingVO;

/**
 * @author 이상림
 * 최초작성일 2022.03.21
 */
@Mapper
public interface ApplyDAO {
	/**
	 * 지원 등록
	 * @param apply
	 * @return
	 */
	public int insertApply(ApplyVO apply);
	
	public int selectTotalRecord(PagingVO<ApplyVO> pagingVO);
	
	/**
	 * 채용공고 지원 목록 조회
	 * @param pagingVO TODO
	 * @return 조건에 맞는 공고가 없는 경우, size == 0 반환.
	 */
	public List<ApplyVO> selectJobList(PagingVO<ApplyVO> pagingVO);
	
	/**
	 * 프로젝트공고 지원 목록 조회
	 * @param pagingVO TODO
	 * @return 조건에 맞는 공고가 없는 경우, size == 0 반환.
	 */
	public List<ApplyVO> selectProList(PagingVO<ApplyVO> pagingVO);
}
