package net.slipp.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/users/login")
public class LoginServlet extends HttpServlet {

	public static final String SESSION_USER_ID = "userId";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String userId = req.getParameter(SESSION_USER_ID);
		String password = req.getParameter("password");
		
		try{
			User.login(userId, password);
			HttpSession session = req.getSession();
			session.setAttribute(SESSION_USER_ID, userId);//session 다른 jsp 에서 데이터 접근 가능
			resp.sendRedirect("/");
			
		}catch(UserNotFoundException e){//없는 사용자 로그인할때
				
			forwardJSP(req, resp, "존재하지 않는 사용자 입니다. 다시 로그인하세요.");
			
		}catch(PasswordMismatchException e){//비밀번호 틀렸을 시

			forwardJSP(req, resp, "비밀번호가 틀립니다. 다시 로그인하세요");
		}
	
	}
	
	private void forwardJSP(HttpServletRequest req, HttpServletResponse resp, String errorMessage) throws ServletException, IOException {
		
		req.setAttribute("errorMessage", errorMessage);
		RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
		rd.forward(req, resp);
	}
	
}