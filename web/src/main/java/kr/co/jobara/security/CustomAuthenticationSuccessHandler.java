package kr.co.jobara.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.FrameworkServlet;

import kr.co.jobara.listener.event.ELoginSuccessEvent;
import kr.co.jobara.listener.event.LoginSuccessEvent;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.member.vo.MemberVOWrapper;
import kr.co.jobara.member.vo.PmemberVO;

public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

	@Inject
	private WebApplicationContext rootContext;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		super.onAuthenticationSuccess(request, response, authentication);

		EmemberVO ememUser = ((MemberVOWrapper)authentication.getPrincipal()).getEmemUser();
		PmemberVO pmemUser = ((MemberVOWrapper)authentication.getPrincipal()).getPmemUser();
		
		WebApplicationContext childContext = null;
		if(ememUser != null && pmemUser == null) {
			rootContext.publishEvent(new ELoginSuccessEvent(ememUser));
			childContext = WebApplicationContextUtils.getWebApplicationContext(rootContext.getServletContext(), FrameworkServlet.SERVLET_CONTEXT_PREFIX+"appServlet");
			request.getSession().setAttribute("authMember", ememUser);
		} else if (ememUser == null && pmemUser != null) {
			rootContext.publishEvent(new LoginSuccessEvent(pmemUser));
			childContext = WebApplicationContextUtils.getWebApplicationContext(rootContext.getServletContext(), FrameworkServlet.SERVLET_CONTEXT_PREFIX+"appServlet");
			request.getSession().setAttribute("authMember", pmemUser);
		}
		
	}
}





