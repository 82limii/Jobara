package kr.co.jobara.contact.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.contact.vo.ContactVO;

@Mapper
public interface ContactDAO {

	public List<ContactVO> selectContactList(PagingVO<ContactVO> pagingVO);
	
	public List<ContactVO> selectContactListPro(PagingVO<ContactVO> pagingVO);
	
	public ContactVO selectContact(int contacSn);
	
	public int selectTotalRecord(PagingVO<ContactVO> pagingVO);
	
	public int selectTotalRecordPro(PagingVO<ContactVO> pagingVO);
	
	public int insertContact(ContactVO contact);
	
	public int updateContact(ContactVO contact);
	
	public int updateContactPro(ContactVO contact);
	
	public int deleteContact(int contacSn);
	
}
