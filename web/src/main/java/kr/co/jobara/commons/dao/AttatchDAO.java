package kr.co.jobara.commons.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.AttatchVO;

/**
 * @author 이상림
 * 최초작성 2022.02.23
 */
@Mapper
public interface AttatchDAO {
	public int insertAttatches(AttatchVO attatch);
	public String selectNextVal();
	
	public List<AttatchVO> selectAttatchList(String attSn);
	
	public AttatchVO selectAttatch(String attSn);
}
