package main.java.DAL.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import main.java.BL.Status.Status;

public class StatusDAO implements IStatusDAO {
    
    private PreparedStatement getIDStatus;
    private PreparedStatement getStatus;
    private PreparedStatement addStatus;
    private PreparedStatement updateStatus;
    private PreparedStatement deleteStatus;
    
    private Connection connexion;
    private Statement requete;
    

    //constructeur
    public StatusDAO(Connection co){

        try {
            //connexion
            this.connexion = co;
            this.requete = this.connexion.createStatement();
            
            //création de la table Status
            try {
                this.requete.executeUpdate("CREATE TABLE IF NOT EXISTS Status (id SERIAL PRIMARY KEY, nom VARCHAR(30) UNIQUE)");
                this.requete.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //PreparedStatements
            this.getIDStatus = this.connexion.prepareStatement("SELECT id FROM Status WHERE nom=?");
            this.getStatus = this.connexion.prepareStatement("SELECT id, nom FROM Status ORDER BY id ASC");
            this.addStatus = this.connexion.prepareStatement("INSERT INTO Status (nom) VALUES (?)");
            this.updateStatus = this.connexion.prepareStatement("UPDATE Status SET nom=? WHERE id=?");
            this.deleteStatus = this.connexion.prepareStatement("DELETE FROM Status WHERE id=?");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public ArrayList<Status> getStatus() {

        ArrayList<Status> listeStatus = new ArrayList<Status>();
        try {
            ResultSet set = this.getStatus.executeQuery();

            while (set.next()) {

                Status status = new Status(set.getInt(1), set.getString(2));
                listeStatus.add(status);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return listeStatus;
    }


    @Override
    public int getIDStatus(String nom) {

        int id = -1;
        
        try {
            this.getIDStatus.setString(1, nom);
            ResultSet set = this.getIDStatus.executeQuery();

            while (set.next()) {
                id = set.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }


    @Override
    public boolean updateStatus(int id, String nom) {

        try {
            this.updateStatus.setString(1, nom);
            this.updateStatus.setInt(2, id);
            this.updateStatus.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public boolean deleteStatus(int id) {

        try {
            this.deleteStatus.setInt(1,id);
            this.deleteStatus.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public boolean addStatus(String nom) {

        try {
            this.addStatus.setString(1, nom);
            this.addStatus.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
