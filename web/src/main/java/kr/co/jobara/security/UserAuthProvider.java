package kr.co.jobara.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import kr.co.jobara.member.vo.PmemberVO;

public class UserAuthProvider implements AuthenticationProvider{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub

	      System.out.println("authentication : " + authentication);

	      String loginUserName = String.valueOf(authentication.getPrincipal());
	      String loginPassword = String.valueOf(authentication.getCredentials());
	      System.out.println("loginUserName : " + loginUserName);
	      System.out.println("loginPassword : " + loginPassword);

	      PmemberVO user = (PmemberVO) userDetailsService.loadUserByUsername(loginUserName);

	      if(!matchPassword(loginPassword, user.getPmemPass())) {


	         throw new BadCredentialsException(loginUserName);
	      }
	      
	      Set<String> authes = new HashSet<>();
	      authes.add("P_MEMBER");
	      List<GrantedAuthority> auth = authes.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	      
	      return new UsernamePasswordAuthenticationToken(loginUserName, loginPassword, auth);
	}

	private boolean matchPassword(String loginPassword, String password) {

	      return loginPassword.equals(password);
	}
	
	
	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
