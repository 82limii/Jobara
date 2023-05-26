package kr.co.jobara.pms.project.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.pms.project.dao.ReportDAO;
import kr.co.jobara.pms.project.vo.ReportVO;

@Service
public class ReportServiceImpl implements ReportService {

	@Inject
	private ReportDAO reportDAO;
	
	@Override
	public ServiceResult createReport(ReportVO report) {
		int rowcnt = reportDAO.insertReport(report);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public ServiceResult modifyReport(ReportVO report) {
		int rowcnt = reportDAO.updateReport(report);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public ServiceResult removeReport(ReportVO report) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void retrieveReportList(PagingVO<ReportVO> paging) {
		int totalRecord = reportDAO.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<ReportVO> dataList = reportDAO.selectReportList(paging);
		paging.setDataList(dataList);
	}

	@Override
	public ReportVO retrieveReport(int repoSn) {
		ReportVO report = reportDAO.selectReport(repoSn);
		if(report == null) {
			 throw new PKNotFoundException(repoSn + "글 없음");
		 }
		return report;
	}

}
