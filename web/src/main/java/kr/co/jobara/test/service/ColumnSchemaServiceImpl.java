package kr.co.jobara.test.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.test.dao.ColumnSchemaDAO;
import kr.co.jobara.test.vo.ColumnSchemaVO;

@Service
public class ColumnSchemaServiceImpl implements ColumnSchemaService {

	@Inject
	private ColumnSchemaDAO dao;
	
	@Override
	public List<String> retrieveTableNames() {
		return dao.selectTableNames();
	}
	
	@Override
	public List<ColumnSchemaVO> retrieveColumnSchemaList(PagingVO<ColumnSchemaVO> pagingVO) {
		pagingVO.setTotalRecord(dao.selectTotalRecord(pagingVO));
		List<ColumnSchemaVO> colList = dao.selectColumnSchemaList(pagingVO);
		pagingVO.setDataList(colList);
		return colList;
	}

}
