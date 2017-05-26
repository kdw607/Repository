<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import = "net.slipp.user.User" %>
<%@ page import = "net.slipp.db.Database" %>
<%
	String userId = request.getParameter("userId");
	String password = request.getParameter("password");
	String name = request.getParameter("name");
	String email = request.getParameter("email");

	User user = new User(userId, password, name, email);
	Database.addUser(user);
	
	
	response.sendRedirect("/slipp/users/form.jsp");
%>