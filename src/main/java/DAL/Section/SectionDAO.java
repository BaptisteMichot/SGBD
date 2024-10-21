package main.java.DAL.Section;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import main.java.BL.Section.Section;


public class SectionDAO implements ISectionDAO {
    
    private PreparedStatement getIDSection;
    private PreparedStatement getSections;
    private PreparedStatement addSection;
    private PreparedStatement updateSection;
    private PreparedStatement deleteSection;
    
    private Connection connexion;
    private Statement requete;
    

    //constructeur
    public SectionDAO(Connection co) {

        try {
            //connexion
            this.connexion = co;
            this.requete = this.connexion.createStatement();

            //création de la table Section
            try {
                this.requete.executeUpdate("CREATE TABLE IF NOT EXISTS Section (id SERIAL PRIMARY KEY, nom VARCHAR(30) UNIQUE)");
                this.requete.close();
            } catch (SQLException e) {
                // La table existe déjà.
                e.printStackTrace();
            }

            //PreparedStatements
            this.getIDSection = this.connexion.prepareStatement("SELECT id FROM Section WHERE nom=?");
            this.getSections = this.connexion.prepareStatement("SELECT id, nom FROM Section ORDER BY id ASC");
            this.addSection = this.connexion.prepareStatement("INSERT INTO Section (nom) VALUES (?)");
            this.updateSection = this.connexion.prepareStatement("UPDATE Section SET nom=? WHERE id=?");
            this.deleteSection = this.connexion.prepareStatement("DELETE FROM Section WHERE id=?");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Section> getSections() {

        ArrayList<Section> listeSection = new ArrayList<Section>();
        try {
            ResultSet set = this.getSections.executeQuery();

            while (set.next()) {

                Section section = new Section(set.getInt(1), set.getString(2));
                listeSection.add(section);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return listeSection;
    }


    @Override
    public int getIDSection(String nom) {

        int id = -1;
        
        try {
            this.getIDSection.setString(1, nom);
            ResultSet set = this.getIDSection.executeQuery();

            while (set.next()) {
                id = set.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }


    @Override
    public boolean updateSection(int id, String nom) {

        try {
            this.updateSection.setString(1, nom);
            this.updateSection.setInt(2, id);
            this.updateSection.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public boolean deleteSection(int id) {

        try {
            this.deleteSection.setInt(1,id);
            this.deleteSection.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public boolean addSection(String nom) {

        try {
            this.addSection.setString(1, nom);
            this.addSection.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
