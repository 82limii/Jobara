package kr.co.jobara.admin.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.jobara.admin.dao.LogDAO;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.commons.vo.PagingVO;

/**
 * @author 이상림
 * 최초작성 2022.03.16
 */
@Service
public class LogService {
	
	@Inject
	private LogDAO dao;
	
	/**
	 * 관리자용 log목록 조회
	 * @param paging
	 * @return
	 */
	public List<LogVO> retrieveLogList(PagingVO<LogVO> paging) {
		List<LogVO> list = dao.selectLogList(paging);
		if(list!=null) {
			int totalRecord = dao.selectTotalRecord(paging);
			paging.setTotalRecord(totalRecord);
			paging.setDataList(list);
		}
		return list;
	}
	
	@Transactional
	public void createLog(LogVO log) {
		String ememId = log.getEmemId();
		String pmemId = log.getPmemId();
		if(ememId == null && pmemId == null) {
			throw new RuntimeException("현재 유저의 ID를 확인할 수 없습니다.");
		}
		dao.insertLog(log);
	}
}
