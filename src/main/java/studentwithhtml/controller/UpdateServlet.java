package studentwithhtml.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentwithhtml.dao.StudentDao;
import studentwithhtml.dto.Student;

public class UpdateServlet extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String name=req.getParameter("name");
	String address=req.getParameter("address");
	String email=req.getParameter("email");
	String password=req.getParameter("password");
	long phone=Long.parseLong(req.getParameter("phone"));
	int id=Integer.parseInt(req.getParameter("id"));
	
	Student student=new Student();
	student.setAddress(address);
	student.setEmail(email);
	student.setName(name);
	student.setPassword(password);
	student.setPhone(phone);
	StudentDao dao=new StudentDao();
	Student dbStudent=dao.updateStudent(id,student);
	PrintWriter printWriter=resp.getWriter();
	if(dbStudent!=null) {
		printWriter.print("Data updated successfully");
	}else {
//		id is not present
		printWriter.print("Sorry Id is not present");
	}
}
}
