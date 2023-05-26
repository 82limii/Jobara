package kr.co.jobara.commons.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.CommonCodeVO;
import kr.co.jobara.commons.vo.LocationVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 이상림
 * 2022.02.22
 * 공통코드 연결 코드
 */
@Mapper
public interface CommonCodeDAO {
	public List<CommonCodeVO> selectCodeList();
	
	public List<CommonCodeVO> selectBigCodeList();
	public List<CommonCodeVO> selectMiddleCodeList(String combCd);
	public List<CommonCodeVO> selectSmallCodeList(String commCd);

	// 주소 코드 가져오기
	public List<LocationVO> selectCityList();
	public List<LocationVO> selectLocationList();
	public List<LocationVO> selectLocation(String locCd);
}
