package kr.co.jobara.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.FrameworkServlet;

import kr.co.jobara.listener.event.ELogoutSuccessEvent;
import kr.co.jobara.listener.event.LogoutSuccessEvent;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.member.vo.MemberVOWrapper;
import kr.co.jobara.member.vo.PmemberVO;


public class CustomLogoutSuccessHandler implements LogoutSuccessHandler{
	
	@Inject
	private WebApplicationContext rootContext;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		EmemberVO ememUser = ((MemberVOWrapper)authentication.getPrincipal()).getEmemUser();
		PmemberVO pmemUser = ((MemberVOWrapper)authentication.getPrincipal()).getPmemUser();
		
		WebApplicationContext childContext = null;
		if(ememUser != null && pmemUser == null) {
			rootContext.publishEvent(new ELogoutSuccessEvent(ememUser));
			childContext = WebApplicationContextUtils.getWebApplicationContext(rootContext.getServletContext(), FrameworkServlet.SERVLET_CONTEXT_PREFIX+"appServlet");
		} else if (ememUser == null && pmemUser != null) {
			rootContext.publishEvent(new LogoutSuccessEvent(pmemUser));
			childContext = WebApplicationContextUtils.getWebApplicationContext(rootContext.getServletContext(), FrameworkServlet.SERVLET_CONTEXT_PREFIX+"appServlet");
		}
		
		response.sendRedirect(request.getContextPath() + "/index.do");
	}

}











