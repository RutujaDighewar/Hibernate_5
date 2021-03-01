/**
 * 
 */
package com.app.client;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.app.entity.Employee;
import com.app.utility.HibernateUtility;

/**
 * @author Rutuja
 *
 */
public class Test {

	
	public void save() {
		Employee emp=new Employee();
		emp.setName("Rutuja");
		Session session=HibernateUtility.getSessionFactory().openSession();
		session.save(emp);
		session.beginTransaction().commit();
		System.out.println("Saved successfully");
	}
	
	public void findAll() {
		Session session=HibernateUtility.getSessionFactory().openSession();
		CriteriaQuery<Employee> cq =session.getCriteriaBuilder().createQuery(Employee.class);
		cq.from(Employee.class);
		List<Employee> empList=session.createQuery(cq).getResultList();
		//System.out.println(empList);
		empList.forEach(System.out::println);
	}
	
	public Employee findOne() {
		Session session=HibernateUtility.getSessionFactory().openSession();
		CriteriaBuilder builder=session.getCriteriaBuilder();
		CriteriaQuery<Employee> cq= session.getCriteriaBuilder().createQuery(Employee.class);
		Root<Employee> root= cq.from(Employee.class);
		cq.select(root);
		cq.where(builder.equal(root.get("id"),3));
		Employee emp=session.createQuery(cq).getSingleResult();
		System.out.println(emp);
		return emp;
	}
	
	public void delete() {
		Employee emp=findOne();
		Session session=HibernateUtility.getSessionFactory().openSession();
		session.delete(emp);
		session.beginTransaction().commit();
		System.out.println("Deleted successfully");
	}
	
	public void update() {
		Employee emp=findOne();
		Session session=HibernateUtility.getSessionFactory().openSession();
		emp.setName("Rohan");
		session.update(emp);
		session.beginTransaction().commit();
		System.out.println("Updated successfully"); 
	}
	
	public static void main(String[] args) {
              Test t=new Test();
             // t.save();
              t.findAll();
              t.findOne();
             // t.delete();
              t.update();

	}

}
