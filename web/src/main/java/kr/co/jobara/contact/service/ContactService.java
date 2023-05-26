package kr.co.jobara.contact.service;

import java.util.List;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.contact.vo.ContactVO;
import kr.co.jobara.enumpkg.ServiceResult;

public interface ContactService {
	
	public List<ContactVO> retrieveContactList(PagingVO<ContactVO> pagingVO);
	
	public List<ContactVO> retrieveContactListPro(PagingVO<ContactVO> pagingVO);
	
	public ContactVO retrieveContact(int contacSn);
	
	public ServiceResult createContact(ContactVO contact);
	
	public ServiceResult modifyContact(ContactVO contact);
	
	public ServiceResult modifyContactPro(ContactVO contact);
	
	public ServiceResult removeContact(int contacSn);
	
}
