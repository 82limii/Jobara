package kr.co.jobara.incumbent.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.incumbent.dao.IncumbentDAO;
import kr.co.jobara.incumbent.vo.IncumbentVO;

@Service
public class IncumbentServiceImpl implements IncumbentService {
	
	@Inject
	private IncumbentDAO incumbentDAO;
	
	@Override
	public List<IncumbentVO> retrieveIncumbentList(PagingVO<IncumbentVO> pagingVO) {
		int totalRecord = incumbentDAO.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<IncumbentVO> incumbentList = incumbentDAO.selectIncumbentList(pagingVO);
		pagingVO.setDataList(incumbentList);
		return incumbentList;
	}

	@Override
	public IncumbentVO retrieveIncumbent(int empSn) {
		IncumbentVO incumbent = incumbentDAO.selectIncumbent(empSn);
		if(incumbent == null)
			throw new PKNotFoundException(empSn+"에 해당하는 게시글이 없음.");
		return incumbent;
	}
	
	@Override
	public ServiceResult modifyIncumbent(IncumbentVO incumbent) {
		retrieveIncumbent(incumbent.getEmpSn());
		int rowcnt = incumbentDAO.updateIncumbent(incumbent);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public ServiceResult removeIncumbent(int empSn) {
		int rowcnt = incumbentDAO.deleteIncumbent(empSn);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

}
