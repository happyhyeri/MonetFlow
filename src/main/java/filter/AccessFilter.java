package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import java.io.IOException;



	import java.io.IOException;
	import java.io.PrintWriter;

	import jakarta.servlet.FilterChain;
	import jakarta.servlet.ServletException;
	import jakarta.servlet.annotation.WebFilter;
	import jakarta.servlet.http.HttpFilter;
	import jakarta.servlet.http.HttpServletRequest;
	import jakarta.servlet.http.HttpServletResponse;

	//필터의 순서를 정하고 싶을경우에는 web.xml에서 여러개 설정해주면 위에서부터 우선순위로 필터를 지정해줄수 있다. 
	//지금 페이지의 webfiller에서는 우선순위 설정을 못한다. 

	@WebFilter({ "/private/*" })
	public class AccessFilter extends HttpFilter {
		
		@Override
		protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
				throws IOException, ServletException {
			
			String uri = request.getRequestURI();
			System.out.println("AccessFilter.doFilter at " + uri);
			// 필터를 적용시킨 경로는 기본이 차단이 됨.
			// uri.endsWith("/menu/login.jsp") || uri.endsWith("/menu/login_handle.jsp")

			// 필터를 걸어두고 하는일은 특정 조건을 만족했을 때만 접근 허용시키게 구현을 함.
			if (request.getSession().getAttribute("logonUser") != null) {
				chain.doFilter(request, response); // 그 다음 필터를 작동 시킴.
				// 더 이상 작동시킬 필터가 없으면 해당 목적지로 접근이 됨.
			} else {
				String path = request.getServletContext().getContextPath();
				response.sendRedirect(path+"/menu/login");
			}
		}
	}