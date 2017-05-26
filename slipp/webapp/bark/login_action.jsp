<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="net.slipp.user.User" %>
<%@ page import="net.slipp.db.Database" %>
<%@ page import="javax.servlet.RequestDispatcher" %>
<%@ page import="net.slipp.user.PasswordMismatchException"%>
<%@ page import="net.slipp.user.UserNotFoundException"%>
<%
	String userId = request.getParameter("userId");
	String password = request.getParameter("password");
	
	try{
	
		User.login(userId, password);
		session.setAttribute("userId", userId);//session 다른 jsp 에서 데이터 접근 가능
		
		response.sendRedirect("/slipp/form.jsp");
	}catch(UserNotFoundException e){//없는 사용자 로그인할때
		request.setAttribute("errorMessage", "존재하지 않는 사용자 입니다. 다시 로그인하세요");
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
		
	}catch(PasswordMismatchException e){//비밀번호 틀렸을 시
		request.setAttribute("errorMessage", "비밀번호가 틀립니다. 다시 로그인하세요");
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}


	/*
	if(user ==null){
		//사용자가 존재하지 않는다는 것을 에러메시지로
		
	}
	
	if(password.equals(user.getPassword())){
		//로그인처리
		
	}else{
		
	}*/
%>