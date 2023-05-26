package kr.co.jobara.commons.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.commons.dao.CommonCodeDAO;
import kr.co.jobara.commons.vo.CommonCodeVO;
import kr.co.jobara.commons.vo.LocationVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 이상림
 * 최초작성 2022.02.27
 * 공통코드를 불러오기 위한 service
 */
@Service
public class CommonCodeService {
	
	@Inject
	private CommonCodeDAO commonCodeDAO;
	
	public Map<String, List<CommonCodeVO>> commonService(String code) {
		
		return null;
	}

	public List<CommonCodeVO> retrieveBigCodeList() {
		return commonCodeDAO.selectBigCodeList();
	}

	public List<CommonCodeVO> retrieveMiddleCodeList(String combCd) {
		return commonCodeDAO.selectMiddleCodeList(combCd);
	}

	public List<CommonCodeVO> retrieveSmallCodeList(String commCd) {
		return commonCodeDAO.selectSmallCodeList(commCd);
	}

	public List<LocationVO> retrieveCityList() {
		return commonCodeDAO.selectCityList();
	}

	public List<LocationVO> retrieveLocationList() {
		return commonCodeDAO.selectLocationList();
	}
	public List<LocationVO> retrieveLocation(String locCd) {
		return commonCodeDAO.selectLocation(locCd);
	}

}
