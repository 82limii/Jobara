package kr.co.jobara.admin.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.admin.vo.AdminVO;

/**
 * @author PC-17
 * 장인슬
 */
@Mapper
public interface AdminDAO {
	
	/**
	 * 식별자로 한사람의 사용자를 조회
	 * @param 조회할 사람의 아이디
	 * @return 존재하지 않는 경우, null 반환.
	 */
	public AdminVO selectAdminForAuth(String adminId);
	
}
