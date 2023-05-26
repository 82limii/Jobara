package kr.co.jobara.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.commons.vo.PagingVO;

/**
 * @author 이상림
 * 최초작성 2022.03.16
 */
@Mapper
public interface LogDAO {
	/**
	 * log 목록 조회 쿼리
	 * @param paging
	 * @return
	 */
	public List<LogVO> selectLogList(PagingVO<LogVO> paging);
	
	public int selectTotalRecord(PagingVO<LogVO> paging);
	
	/**
	 * log 등록
	 * @param logVO => id 입력
	 * 			logTime, logAdd 필수입력사항 
	 * @return
	 */
	public int insertLog(LogVO log);

}
