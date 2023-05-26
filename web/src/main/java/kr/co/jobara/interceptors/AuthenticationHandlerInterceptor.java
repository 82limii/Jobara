package kr.co.jobara.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class AuthenticationHandlerInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		Object authMember = session.getAttribute("authMember");
		
		boolean pass = authMember!=null;
		if(!pass) {
			response.sendRedirect(request.getContextPath() + "/login/loginForm.do");
		}
		return pass;
	}
}
