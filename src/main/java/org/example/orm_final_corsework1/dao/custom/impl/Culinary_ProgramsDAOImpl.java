package org.example.orm_final_corsework1.dao.custom.impl;

import org.example.orm_final_corsework1.config.FactoryConfiguration;
import org.example.orm_final_corsework1.dao.custom.Culinary_ProgramsDAO;
import org.example.orm_final_corsework1.entity.Culinary_Programs;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class Culinary_ProgramsDAOImpl implements Culinary_ProgramsDAO {


    @Override
    public boolean add(Culinary_Programs entity) throws SQLException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Culinary_Programs culinaryPrograms = new Culinary_Programs();
            culinaryPrograms.setProgramID(entity.getProgramID());
            culinaryPrograms.setProgramName(entity.getProgramName());
            culinaryPrograms.setDuration(entity.getDuration());
            culinaryPrograms.setFee(entity.getFee());

            session.save(culinaryPrograms);
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
    public boolean update(Culinary_Programs entity) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Culinary_Programs culinaryPrograms = session.get(Culinary_Programs.class, entity.getProgramID());

            if (culinaryPrograms == null) {
                return false;
            }

            culinaryPrograms.setProgramName(entity.getProgramName());
            culinaryPrograms.setDuration(entity.getDuration());
            culinaryPrograms.setFee(entity.getFee());


            session.update(culinaryPrograms);

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
    public boolean delete(Culinary_Programs entity) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Culinary_Programs culinaryPrograms = session.get(Culinary_Programs.class, entity.getProgramID());


            if (culinaryPrograms == null) {
                return false;
            }


            session.delete(culinaryPrograms);
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
    public List<Culinary_Programs> getAll() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Culinary_Programs>culinaryProgramsList= session.createQuery("FROM org.example.orm_final_corsework1.entity.Culinary_Programs", Culinary_Programs.class).list();

        transaction.commit();
        session.close();

        return  culinaryProgramsList;

    }

    @Override
    public List<String> getIds() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<String> idList = session.createQuery("SELECT cp.programID FROM org.example.orm_final_corsework1.entity.Culinary_Programs cp", String.class).list();

        transaction.commit();
        session.close();

        return idList;
    }

    @Override
    public Culinary_Programs searchByID(String programID) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Culinary_Programs culinaryPrograms = session.get(Culinary_Programs.class, programID);

        transaction.commit();
        session.close();

        return culinaryPrograms;
    }
}
