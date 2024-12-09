package org.example.orm_final_corsework1.dao.custom.impl;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.orm_final_corsework1.config.FactoryConfiguration;
import org.example.orm_final_corsework1.dao.custom.AdminCoordinatorDAO;
import org.example.orm_final_corsework1.dto.Admission_CoordinatorDTO;
import org.example.orm_final_corsework1.entity.Admission_Coordinator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCrypt;



import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AdminCoordinatorDAOImpl implements AdminCoordinatorDAO {
    @Override
    public boolean add(Admission_Coordinator entity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Admission_Coordinator entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Admission_Coordinator entity) throws SQLException {
        return false;
    }

    @Override
    public List<Admission_Coordinator> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean saveAdmissionCoordinator(String admissionCoUserID, String admissionCoUsername, String admissionCoPassword) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Admission_Coordinator admissionCoordinator = new Admission_Coordinator();

            admissionCoordinator.setAdmissionCoUserID(admissionCoUserID);
            admissionCoordinator.setAdmissionCoUsername(admissionCoUsername);
            admissionCoordinator.setAdmissionCoPassword(admissionCoPassword);

            session.save(admissionCoordinator);
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
    public List<String> getIds() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<String> idList = session.createQuery("SELECT ac.admissionCoUserID FROM org.example.orm_final_corsework1.entity.Admission_Coordinator ac", String.class).list();

        transaction.commit();
        session.close();

        return idList;
    }

    @Override
    public String getCurrentAdmissionCoordinatorID() throws SQLException {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction= session.beginTransaction();

        String hql = "SELECT ac.admissionCoUserID FROM org.example.orm_final_corsework1.entity.Admission_Coordinator ac ORDER BY ac.admissionCoUserID DESC";

        Query<String> query = session.createQuery(hql, String.class);
        query.setMaxResults(1);

        String oid = query.uniqueResult();

        transaction.commit();
        session.close();


        return oid;
    }


    @Override
    public void checkCredential(String admissionCoUsername,String admissionCoPassword,AnchorPane root) throws SQLException, IOException {
        Session session = null;
        Transaction transaction = null;

        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            // HQL query to find the Admin by adminUsername
            Query<Admission_Coordinator> query = session.createQuery("FROM  org.example.orm_final_corsework1.entity.Admission_Coordinator ac WHERE ac.admissionCoUsername = :admissionCoUsername", Admission_Coordinator.class);
            query.setParameter("admissionCoUsername", admissionCoUsername);

            Admission_Coordinator admissionCoordinator = query.uniqueResult();

            if (admissionCoordinator != null) {
                String dbPassword = admissionCoordinator.getAdmissionCoPassword();

                // Assuming you're using BCrypt, you would compare with BCrypt
                if (BCrypt.checkpw(admissionCoPassword, dbPassword)) {
                    navigateToAdmissionCoordinatorHomepage(root);
                } else {
                    new Alert(Alert.AlertType.ERROR, "Sorry! Entered password is incorrect!").show();
                }
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Sorry! Entered user ID can't be found!").show();
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

    }

    private void navigateToAdmissionCoordinatorHomepage(AnchorPane root) throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage)root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard Form");
    }


    @Override
    public boolean isValidUserPassword(String adminUserID, String adminUsername) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        boolean isValid = session.createQuery("SELECT 1 FROM Admission_Coordinator a WHERE a.admissionCoUserID = :adminUserID AND a.admissionCoUsername  = :adminUsername")
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

        int result = session.createQuery("UPDATE Admission_Coordinator a SET a.admissionCoPassword = :hashedPassword WHERE a.admissionCoUserID = :adminUserID")
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
        String hql = "SELECT a.admissionCoPassword FROM org.example.orm_final_corsework1.entity.Admission_Coordinator a WHERE a.admissionCoUserID = :adminUserID";
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
        int result = session.createQuery("UPDATE org.example.orm_final_corsework1.entity.Admission_Coordinator a SET a.admissionCoUsername = :newUsername WHERE a.admissionCoUserID = :adminUserID")
                .setParameter("newUsername", adminUsername)
                .setParameter("adminUserID", adminUserID)
                .executeUpdate();

        transaction.commit();
        session.close();

        // Return true if the update was successful
        return result > 0;
    }

}