package kr.co.jobara.commons.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.commons.dao.MenuDAO;
import kr.co.jobara.commons.vo.MenuVO;

@Service
public class MenuService {

	@Inject
	private MenuDAO menuDAO;
	
	public List<MenuVO> retrieveMenu(String menuSort) {
		return menuDAO.selectMenu(menuSort);
	}

}
