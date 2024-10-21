package main.java.DAL.Personne;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import main.java.BL.Personne.Personne;

public class PersonneDAO implements IPersonneDAO {

    private PreparedStatement getIDPersonne;
    private PreparedStatement getPersonne;
    private PreparedStatement addPersonne;
    private PreparedStatement updatePersonne;
    private PreparedStatement deletePersonne;
    private PreparedStatement getPersonneAndStatus;
    
    private Connection connexion;
    private Statement requete;

    //constructeur
    public PersonneDAO(Connection co) {

        try {
            //connexion
            this.connexion = co;
            this.requete = this.connexion.createStatement();
            
            //création table Personne
            try {
                this.requete.executeUpdate("CREATE TABLE IF NOT EXISTS Personne (id SERIAL PRIMARY KEY, id_status INT, nom VARCHAR(30), prenom VARCHAR(30), FOREIGN KEY (id_status) REFERENCES Status(id))");
                this.requete.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //PreparedStatements
            this.getIDPersonne = this.connexion.prepareStatement("SELECT id FROM Personne WHERE nom=? AND prenom=?");
            this.getPersonne = this.connexion.prepareStatement("SELECT id, id_status, nom, prenom FROM Personne ORDER BY id ASC");
            this.addPersonne = this.connexion.prepareStatement("INSERT INTO Personne (id_status, nom, prenom) VALUES (?, ?, ?)");
            this.updatePersonne = this.connexion.prepareStatement("UPDATE Personne SET nom=?, prenom=? WHERE id=?");
            this.deletePersonne = this.connexion.prepareStatement("DELETE FROM Personne WHERE id=?");
            this.getPersonneAndStatus = this.connexion.prepareStatement("SELECT Personne.nom, Personne.prenom, Status.nom FROM Personne JOIN Status ON Personne.id_status = Status.id");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public ArrayList<Personne> getPersonne() {
        ArrayList<Personne> listePersonnes = new ArrayList<>();
        try {
            ResultSet set = this.getPersonne.executeQuery();

            while (set.next()) {
                Personne personne = new Personne(set.getInt(1), set.getInt(2), set.getString(3), set.getString(4));
                listePersonnes.add(personne);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listePersonnes;
    }

    @Override
    public int getIDPersonne(String nom, String prenom) {

        int id = -1;
        
        try {
            this.getIDPersonne.setString(1, nom);
            this.getIDPersonne.setString(2, prenom);
            ResultSet set = this.getIDPersonne.executeQuery();

            if (set.next()) {
                id = set.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public boolean updatePersonne(int id, String nom, String prenom) {

        try {
            this.updatePersonne.setInt(1, id);
            this.updatePersonne.setString(2, nom);
            this.updatePersonne.setString(3,prenom);
            this.updatePersonne.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deletePersonne(int id) {

        try {
            this.deletePersonne.setInt(1, id);
            this.deletePersonne.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean addPersonne(int idStatus, String nom, String prenom) {
        try {
            this.addPersonne.setInt(1, idStatus);
            this.addPersonne.setString(2, nom);
            this.addPersonne.setString(3, prenom);
            this.addPersonne.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<String> getPersonneAndStatus(){

        ArrayList<String> personneAndStatus = new ArrayList<>();

        try{
            ResultSet set = this.getPersonneAndStatus.executeQuery();

            while (set.next()) {
                String nom = set.getString(1);
                String prenom = set.getString(2);
                String status = set.getString(3);
                personneAndStatus.add(nom + " " + prenom + " a le status " + status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personneAndStatus;
    }
}
