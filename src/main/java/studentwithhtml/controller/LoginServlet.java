package studentwithhtml.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentwithhtml.dao.StudentDao;
import studentwithhtml.dto.Student;

public class LoginServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		StudentDao dao=new StudentDao();
		List<Student> students=dao.getAllStudents();
		boolean value=false;
		String dbPassword=null;
		for(Student student:students) {
			if(email.equals(student.getEmail())) {
				value=true;
				dbPassword=student.getPassword();
				break;
				}
		}
		if(value) {
//			value =true email is present in the database
			if(password.equals(dbPassword)) {
//				password matches then it is login Success
				resp.sendRedirect("https://www.javatpoint.com");
			}else {
				RequestDispatcher dispatcher=req.getRequestDispatcher("login.html");
				dispatcher.include(req, resp);
			}
		}else {
//			value=false
			RequestDispatcher dispatcher=req.getRequestDispatcher("login.html");
			dispatcher.include(req, resp);
		}
	}
}
