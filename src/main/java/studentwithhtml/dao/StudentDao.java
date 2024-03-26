package studentwithhtml.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import studentwithhtml.dto.Student;

public class StudentDao {

	public EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("vinod").createEntityManager();
	}
	
	public void saveStudent(Student student) {
		EntityManager entityManager=getEntityManager();
		EntityTransaction entityTransaction=entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(student);
		entityTransaction.commit();
	}

	public List<Student> getAllStudents() {
		EntityManager entityManager=getEntityManager();
		Query  query=entityManager.createQuery("select s from Student s");
		List<Student> list=query.getResultList();
		return list;
	}

	public Student findStudentById(int id) {
		EntityManager entityManager=getEntityManager();
		Student dbStudent=entityManager.find(Student.class, id);
		return dbStudent;
	}
	
	
	

	public Student deleteStudent(int id) {
		EntityManager entityManager=getEntityManager();
		Student dbStudent=entityManager.find(Student.class, id);
		if(dbStudent!=null) {
//			id is present so you can delete the data
			EntityTransaction entityTransaction=entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.remove(dbStudent);
			entityTransaction.commit();
			return dbStudent;
		}else {
			return null;
		}
	}

	public Student updateStudent(int id, Student student) {
		EntityManager entityManager=getEntityManager();
		Student dbStudent=entityManager.find(Student.class, id);
		if(dbStudent!=null) {
			EntityTransaction entityTransaction=entityManager.getTransaction();
			entityTransaction.begin();
			student.setId(id);
			entityManager.merge(student);
			entityTransaction.commit();
			return student;
		}else {
			return null;
		}
	}
	
	
}
