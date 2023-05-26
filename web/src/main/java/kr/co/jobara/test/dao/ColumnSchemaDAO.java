package kr.co.jobara.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.test.vo.ColumnSchemaVO;

@Mapper
public interface ColumnSchemaDAO {
	public List<String> selectTableNames();
	public int selectTotalRecord(PagingVO<ColumnSchemaVO> pagingVO);
	/**
	 * 전체 컬럼 스키마 목록 조회
	 * @param pagingVO TODO
	 * @return
	 */
	public List<ColumnSchemaVO> selectColumnSchemaList(PagingVO<ColumnSchemaVO> pagingVO);
}
