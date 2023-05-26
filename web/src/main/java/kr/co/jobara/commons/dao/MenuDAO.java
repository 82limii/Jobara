package kr.co.jobara.commons.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.MenuVO;

@Mapper
public interface MenuDAO {
	public List<MenuVO> selectMenu(String menuSort);
}
