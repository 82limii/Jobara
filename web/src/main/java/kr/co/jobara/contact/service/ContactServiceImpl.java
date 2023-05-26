package kr.co.jobara.contact.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.contact.dao.ContactDAO;
import kr.co.jobara.contact.vo.ContactVO;
import kr.co.jobara.enumpkg.ServiceResult;

@Service
public class ContactServiceImpl implements ContactService {
	
	@Inject
	private ContactDAO contactDAO;

	@Override
	public List<ContactVO> retrieveContactList(PagingVO<ContactVO> pagingVO) {
		int totalRecord = contactDAO.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<ContactVO> contactList = contactDAO.selectContactList(pagingVO);
		pagingVO.setDataList(contactList);
		return contactList;
	}
	
	@Override
	public List<ContactVO> retrieveContactListPro(PagingVO<ContactVO> pagingVO) {
		int totalRecord = contactDAO.selectTotalRecordPro(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<ContactVO> contactList = contactDAO.selectContactListPro(pagingVO);
		pagingVO.setDataList(contactList);
		return contactList;
	}

	@Override
	public ContactVO retrieveContact(int contacSn) {
		ContactVO contact = contactDAO.selectContact(contacSn);
		if(contact == null)
			throw new PKNotFoundException(contacSn+"에 해당하는 연락처가 없음.");
		return contact;
	}

	@Override
	public ServiceResult createContact(ContactVO contact) {
		int rowcnt = contactDAO.insertContact(contact);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public ServiceResult modifyContact(ContactVO contact) {
		ContactVO vo = retrieveContact(contact.getContacSn());
		if(vo == null) {
			throw new PKNotFoundException("존재하지 않는 연락처입니다.");
		}
		int rowcnt = contactDAO.updateContact(contact);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}
	
	@Override
	public ServiceResult modifyContactPro(ContactVO contact) {
		ContactVO vo = retrieveContact(contact.getContacSn());
		if(vo == null) {
			throw new PKNotFoundException("존재하지 않는 연락처입니다.");
		}
		int rowcnt = contactDAO.updateContactPro(contact);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}
	
	@Override
	public ServiceResult removeContact(int contacSn) {
		int rowcnt = contactDAO.deleteContact(contacSn);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}


}
