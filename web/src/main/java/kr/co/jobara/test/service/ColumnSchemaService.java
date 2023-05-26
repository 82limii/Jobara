package kr.co.jobara.test.service;

import java.util.List;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.test.vo.ColumnSchemaVO;

public interface ColumnSchemaService {
	public List<String> retrieveTableNames();
	public List<ColumnSchemaVO> retrieveColumnSchemaList(PagingVO<ColumnSchemaVO> pagingVO);
}
