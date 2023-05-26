package kr.co.jobara.helps.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.helps.dao.HelpsDAO;
import kr.co.jobara.helps.vo.HelpsVO;

@Service
public class HelpsServiceImpl implements HelpsService {
	
	@Inject
	private HelpsDAO helpsDAO;
	
	@Override
	public ServiceResult createHelps(HelpsVO helps) {
		int rowcnt = helpsDAO.insertHelps(helps);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}
	
	@Override
	public ServiceResult modifyHelps(HelpsVO helps) {
		retrieveHelps(helps.getHelpsSn());
		int rowcnt = helpsDAO.updateHelps(helps);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public HelpsVO retrieveHelps(int helpsSn) {
		HelpsVO helps = helpsDAO.selectHelps(helpsSn);
		if(helps == null)
			throw new PKNotFoundException(helpsSn+"에 해당하는 게시글이 없음.");
		return helps;
	}

	@Override
	public List<HelpsVO> retrieveHelpsList(PagingVO<HelpsVO> pagingVO) {
		int totalRecord = helpsDAO.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<HelpsVO> helpsList = helpsDAO.selectHelpsList(pagingVO);
		pagingVO.setDataList(helpsList);
		return helpsList;
	}
	
	@Override
	public List<HelpsVO> retrieveHelpsListAdmin(PagingVO<HelpsVO> pagingVO) {
		int totalRecord = helpsDAO.selectTotalRecordAdmin(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<HelpsVO> helpsList = helpsDAO.selectHelpsListAdmin(pagingVO);
		pagingVO.setDataList(helpsList);
		return helpsList;
	}

	@Override
	public List<HelpsVO> retrieveListForAdmin() {
		List<HelpsVO> list = helpsDAO.selectListForAdmin();
		return list;
	}

	@Override
	public HelpsVO retrieveHelpsAns(int helpsSn2) {
		HelpsVO helpsAns = helpsDAO.selectHelpsAns(helpsSn2);
		return helpsAns;
	}

	@Override
	public ServiceResult removeHelps(int helpsSn) {
		int rowcnt = helpsDAO.deleteHelps(helpsSn);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}




}
