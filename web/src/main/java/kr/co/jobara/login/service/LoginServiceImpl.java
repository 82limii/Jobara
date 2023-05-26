package kr.co.jobara.login.service;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import kr.co.jobara.admin.dao.AdminDAO;
import kr.co.jobara.admin.vo.AdminVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.member.dao.EmemberDAO;
import kr.co.jobara.member.dao.PmemberDAO;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.utils.SecurityUtils;

@Service
public class LoginServiceImpl implements LoginService {

	@Inject
	private PmemberDAO pmemberDAO;
	
	@Inject
	private EmemberDAO ememberDAO;
	
	@Inject
	private AdminDAO adminDAO;
	
		
	@Override
	public ServiceResult login(PmemberVO inputData) {
		try {
			ServiceResult result = null;
			PmemberVO saved = pmemberDAO.selectPmemberForAuth(inputData.getPmemId());
			if(saved==null) {
				result = ServiceResult.NOTEXIST;
			}else {
				String inputPass = inputData.getPmemPass();
				String encoded = SecurityUtils.encryptSha512(inputPass);
				String savedPass = saved.getPmemPass();
				if(savedPass.equals(encoded)) {
					result = ServiceResult.OK;
						try {
							BeanUtils.copyProperties(inputData, saved);
						} catch (IllegalAccessException | InvocationTargetException e) {
							throw new RuntimeException(e);
						}
				}else {
					result = ServiceResult.INVALIDPASSWORD;
				}	
			}	
			return result;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	@Override
	public ServiceResult elogin(EmemberVO inputData) {
		try {
			ServiceResult result = null;
			EmemberVO saved = ememberDAO.selectEmemberForAuth(inputData.getEmemId());
			if(saved==null) {
				result = ServiceResult.NOTEXIST;
			}else {
				String inputPass = inputData.getEmemPass();
				String encoded = SecurityUtils.encryptSha512(inputPass);
				String savedPass = saved.getEmemPass();
				if(savedPass.equals(encoded)) {
					result = ServiceResult.OK;
						try {
							BeanUtils.copyProperties(inputData, saved);
						} catch (IllegalAccessException | InvocationTargetException e) {
							throw new RuntimeException(e);
						}
				}else {
					result = ServiceResult.INVALIDPASSWORD;
				}	
			}	
			return result;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	@Override
	public ServiceResult alogin(AdminVO inputData) {
		try {
			ServiceResult result = null;
			AdminVO saved = adminDAO.selectAdminForAuth(inputData.getAdminId());
			if(saved==null) {
				result = ServiceResult.NOTEXIST;
			}else {
				String inputPass = inputData.getAdminPass();
				String encoded = SecurityUtils.encryptSha512(inputPass);
				String savedPass = saved.getAdminPass();
				if(savedPass.equals(encoded)) {
					result = ServiceResult.OK;
					try {
						BeanUtils.copyProperties(inputData, saved);
					} catch (IllegalAccessException | InvocationTargetException e) {
						throw new RuntimeException(e);
					}
				}else {
					result = ServiceResult.INVALIDPASSWORD;
				}	
			}	
			return result;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
}
