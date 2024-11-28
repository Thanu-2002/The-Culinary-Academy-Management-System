package org.example.orm_final_corsework1.dao.custom.impl;

import org.example.orm_final_corsework1.config.FactoryConfiguration;
import org.example.orm_final_corsework1.dao.custom.ResetUsernamePasswordAdminDAO;
import org.example.orm_final_corsework1.entity.ResetUsernamePasswordAdmin;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.SQLException;
import java.util.List;

public class ResetUsernamePasswordAdminDAOImpl implements ResetUsernamePasswordAdminDAO {
    @Override
    public boolean add(ResetUsernamePasswordAdmin entity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(ResetUsernamePasswordAdmin entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(ResetUsernamePasswordAdmin entity) throws SQLException {
        return false;
    }

    @Override
    public List<ResetUsernamePasswordAdmin> getAll() throws SQLException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException {
        return null;
    }

    @Override
    public boolean isValidUserPassword(String adminUserID, String adminUsername) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        boolean isValid = session.createQuery("SELECT 1 FROM Admin a WHERE a.adminUserID = :adminUserID AND a.adminUsername = :adminUsername")
                .setParameter("adminUserID", adminUserID)
                .setParameter("adminUsername", adminUsername)
                .uniqueResult() != null;

        transaction.commit();
        session.close();

        return isValid;
    }

    @Override
    public boolean resetPassword(String adminUserID, String adminPassword) throws SQLException {
        // Hash the new password using BCrypt
        String hashedPassword = BCrypt.hashpw(adminPassword, BCrypt.gensalt());

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        int result = session.createQuery("UPDATE Admin a SET a.adminPassword = :hashedPassword WHERE a.adminUserID = :adminUserID")
                .setParameter("hashedPassword", hashedPassword)
                .setParameter("adminUserID", adminUserID)
                .executeUpdate();

        transaction.commit();
        session.close();

        return result > 0;
    }

    @Override
    public boolean isValidUserUsername(String adminUserID, String adminPassword) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        // Query to get the hashed password for the provided adminUserID
        String hql = "SELECT a.adminPassword FROM org.example.orm_final_corsework1.entity.Admin a WHERE a.adminUserID = :adminUserID";
        String hashedPassword = (String) session.createQuery(hql)
                .setParameter("adminUserID", adminUserID)
                .uniqueResult();

        transaction.commit();
        session.close();

        // Check if the hashed password matches the provided adminPassword
        return hashedPassword != null && BCrypt.checkpw(adminPassword, hashedPassword);
    }

    @Override
    public boolean resetUsername(String adminUserID, String adminUsername) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        // Query to update the username for the given adminUserID
        int result = session.createQuery("UPDATE org.example.orm_final_corsework1.entity.Admin a SET a.adminUsername = :newUsername WHERE a.adminUserID = :adminUserID")
                .setParameter("newUsername", adminUsername)
                .setParameter("adminUserID", adminUserID)
                .executeUpdate();

        transaction.commit();
        session.close();

        // Return true if the update was successful
        return result > 0;
    }
}
