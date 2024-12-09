package org.example.orm_final_corsework1.dao.custom.impl;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.orm_final_corsework1.config.FactoryConfiguration;
import org.example.orm_final_corsework1.controller.HomepageAdminController;
import org.example.orm_final_corsework1.dao.custom.AdminDAO;
import org.example.orm_final_corsework1.entity.Admin;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {
    @Override
    public boolean add(Admin entity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Admin entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Admin entity) throws SQLException {
        return false;
    }

    @Override
    public List<Admin> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean saveAdmin(String adminUserID, String adminUsername,String Email, String adminPassword) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Admin admin = new Admin();

            admin.setAdminUserID(adminUserID);
            admin.setAdminUsername(adminUsername);
            admin.setAdminEmail(Email);
            admin.setAdminPassword(adminPassword);

            session.save(admin);
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

        List<String> idList = session.createQuery("SELECT a.adminUserID FROM org.example.orm_final_corsework1.entity.Admin a", String.class).list();

        transaction.commit();
        session.close();

        return idList;
    }

    public void checkCredential(String adminUsername, String adminPassword,AnchorPane root) throws SQLException, IOException{

        Session session =FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query<Admin> query = session.createQuery("FROM  org.example.orm_final_corsework1.entity.Admin a WHERE a.adminUsername = :adminUsername", Admin.class);
        query.setParameter("adminUsername",adminUsername);

        Admin admin = query.uniqueResult();

        if (admin != null) {
            String dbPassword = admin.getAdminPassword();

            if (BCrypt.checkpw(adminPassword, dbPassword)) {
                new Alert(Alert.AlertType.INFORMATION, "Welcome!").show();
                navigateToAdminHomepage(root);
            } else {
                new Alert(Alert.AlertType.ERROR, "Sorry! Entered password is incorrect!").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Sorry! Entered user ID can't be found!").show();
        }

        transaction.commit();



    }

    private void navigateToAdminHomepage(AnchorPane root) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/AdminDashboard_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage)root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard Form");
    }


    @Override
    public String getCurrentAdminID() throws SQLException {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction= session.beginTransaction();



        String hql = "SELECT a.adminUserID FROM Admin a ORDER BY a.adminUserID DESC";


        Query<String> query = session.createQuery(hql, String.class);
        query.setMaxResults(1);

        String oid = query.uniqueResult();

        transaction.commit();
        session.close();


        return oid;
    }
}