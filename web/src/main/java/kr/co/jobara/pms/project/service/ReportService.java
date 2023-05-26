package kr.co.jobara.pms.project.service;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.pms.project.vo.ReportVO;

public interface ReportService {
	
	public ServiceResult createReport(ReportVO report);
	public ServiceResult modifyReport(ReportVO report);
	public ServiceResult removeReport(ReportVO report);
	public void retrieveReportList(PagingVO<ReportVO> paging);
	public ReportVO retrieveReport(int repoSn);
	
}
