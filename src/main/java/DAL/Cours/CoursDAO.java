package main.java.DAL.Cours;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import main.java.BL.Cours.Cours;

public class CoursDAO implements ICoursDAO {

    private PreparedStatement getIDCours;
    private PreparedStatement getCours;
    private PreparedStatement addCours;
    private PreparedStatement updateCours;
    private PreparedStatement deleteCours;
    private PreparedStatement getCoursAndSection;
    
    private Connection connexion;
    private Statement requete;

    //constructeur
    public CoursDAO(Connection co) {

        try {
            //connexion
            this.connexion = co;
            this.requete = this.connexion.createStatement();
            
            //création table Cours
            try {
                this.requete.executeUpdate("CREATE TABLE IF NOT EXISTS Cours (id SERIAL PRIMARY KEY, id_section INT, nom VARCHAR(50) UNIQUE, FOREIGN KEY (id_section) REFERENCES Section(id))");
                this.requete.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //PreparedStatements
            this.getIDCours = this.connexion.prepareStatement("SELECT id FROM Cours WHERE nom=?");
            this.getCours = this.connexion.prepareStatement("SELECT id, id_section, nom FROM Cours ORDER BY id ASC");
            this.addCours = this.connexion.prepareStatement("INSERT INTO Cours (id_section, nom) VALUES (?, ?)");
            this.updateCours = this.connexion.prepareStatement("UPDATE Cours SET nom=? WHERE id=?");
            this.deleteCours = this.connexion.prepareStatement("DELETE FROM Cours WHERE id=?");
            this.getCoursAndSection = this.connexion.prepareStatement("SELECT Cours.nom, Section.nom FROM Cours JOIN Section ON Cours.id_section = Section.id");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Cours> getCours() {
        ArrayList<Cours> listeCours = new ArrayList<>();
        try {
            ResultSet set = this.getCours.executeQuery();

            while (set.next()) {
                Cours cours = new Cours(set.getInt(1), set.getInt(2), set.getString(3));
                listeCours.add(cours);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeCours;
    }

    @Override
    public int getIDCours(String nom) {

        int id = -1;
        
        try {
            this.getIDCours.setString(1, nom);
            ResultSet set = this.getIDCours.executeQuery();

            if (set.next()) {
                id = set.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public boolean updateCours(int id, String nom) {

        try {
            this.updateCours.setString(1, nom);
            this.updateCours.setInt(2, id);
            this.updateCours.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteCours(int id) {

        try {
            this.deleteCours.setInt(1, id);
            this.deleteCours.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean addCours(int idSection, String nom) {
        try {
            this.addCours.setInt(1, idSection);
            this.addCours.setString(2, nom);
            this.addCours.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<String> getCoursAndSection(){

        ArrayList<String> coursAndSection = new ArrayList<>();

        try{
            ResultSet set = this.getCoursAndSection.executeQuery();

            while (set.next()) {
                String cours = set.getString(1);
                String section = set.getString(2);
                coursAndSection.add("Le cours " + cours + " fait partie de la section " + section);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coursAndSection;
    }
}
