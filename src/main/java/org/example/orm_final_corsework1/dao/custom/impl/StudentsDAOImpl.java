package org.example.orm_final_corsework1.dao.custom.impl;

import org.example.orm_final_corsework1.config.FactoryConfiguration;
import org.example.orm_final_corsework1.dao.custom.StudentsDAO;
import org.example.orm_final_corsework1.entity.Students;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class StudentsDAOImpl implements StudentsDAO {
    @Override
    public boolean add(Students entity) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Students students = new Students();
            students.setStudentID(entity.getStudentID());
            students.setDate(entity.getDate());
            students.setFirstName(entity.getFirstName());
            students.setLastName(entity.getLastName());
            students.setEmail(entity.getEmail());
            students.setMobileNumber(entity.getMobileNumber());
            students.setAddress(entity.getAddress());
            students.setSelectedCourse(entity.getSelectedCourse());
            students.setCulinaryPrograms(entity.getCulinaryPrograms());

            session.save(students);
            transaction.commit();
            return true;

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(Students entity) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Students students = session.get(Students.class, entity.getStudentID());

            if (students == null) {
                return false;
            }

            students.setDate(entity.getDate());
            students.setFirstName(entity.getFirstName());
            students.setLastName(entity.getLastName());
            students.setEmail(entity.getEmail());
            students.setMobileNumber(entity.getMobileNumber());
            students.setAddress(entity.getAddress());
            students.setSelectedCourse(entity.getSelectedCourse());
            students.setCulinaryPrograms(entity.getCulinaryPrograms());


            session.update(students);

            transaction.commit();
            return true;

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(Students entity) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Students students = session.get(Students.class, entity.getStudentID());


            if (students == null) {
                return false;
            }


            session.delete(students);
            transaction.commit();

            return true;

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Students> getAll() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Students>studentsList= session.createQuery("FROM org.example.orm_final_corsework1.entity.Students", Students.class).list();

        transaction.commit();
        session.close();

        return  studentsList;
    }

    @Override
    public List<String> getIds() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<String> idList = session.createQuery("SELECT s.studentID FROM org.example.orm_final_corsework1.entity.Students s", String.class).list();

        transaction.commit();
        session.close();

        return idList;
    }

    @Override
    public Students searchByID(String studentID) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Students students = session.get(Students.class, studentID);

        transaction.commit();
        session.close();

        return students;
    }

    @Override
    public String getCurrentStudentID() throws SQLException {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction= session.beginTransaction();



        String hql = "SELECT s.studentID FROM org.example.orm_final_corsework1.entity.Students s ORDER BY s.studentID DESC";


        Query<String> query = session.createQuery(hql, String.class);
        query.setMaxResults(1);

        String oid = query.uniqueResult();

        transaction.commit();
        session.close();


        return oid;
    }
}
