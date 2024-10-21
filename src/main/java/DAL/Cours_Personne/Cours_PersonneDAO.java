package main.java.DAL.Cours_Personne;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Cours_PersonneDAO implements ICours_PersonneDAO {

    private PreparedStatement addRelation;
    private PreparedStatement deleteRelation;
    private PreparedStatement getPersonnesByCours;

    private Connection connexion;
    private Statement requete;

    //constructeur
    public Cours_PersonneDAO(Connection co) {

        try {
            //connexion
            this.connexion = co;
            this.requete = this.connexion.createStatement();

            //création de la table Cours_Personne
            try {
                this.connexion.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Cours_Personne (id_personne INT, id_cours INT, PRIMARY KEY (id_personne, id_cours), FOREIGN KEY (id_personne) REFERENCES Personne(id), FOREIGN KEY (id_cours) REFERENCES Cours(id))");
                this.requete.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //PreparedStatements
            this.addRelation = this.connexion.prepareStatement("INSERT INTO Cours_Personne (id_personne, id_cours) VALUES (?, ?)");
            this.deleteRelation = this.connexion.prepareStatement("DELETE FROM Cours_Personne WHERE id_personne=? AND id_cours=?");
            this.getPersonnesByCours = this.connexion.prepareStatement("SELECT Cours.nom, Personne.nom, Personne.prenom FROM Cours_Personne JOIN Cours ON Cours_Personne.id_cours = Cours.id JOIN Personne ON Cours_Personne.id_personne = Personne.id");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //méthode pour ajouter une relation entre une personne et un cours
    public boolean addCoursPersonne(int idPersonne, int idCours) {
        try {
            this.addRelation.setInt(1, idPersonne);
            this.addRelation.setInt(2, idCours);
            this.addRelation.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //méthode pour supprimer une relation entre une personne et un cours
    public boolean deleteCoursPersonne(int idPersonne, int idCours) {
        try {
            this.deleteRelation.setInt(1, idPersonne);
            this.deleteRelation.setInt(2, idCours);
            this.deleteRelation.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    //méthode pour récupérer une liste de personnes associées à chaque cours
    public ArrayList<String> getPersonnesByCours() {

        ArrayList<String> personnesCours = new ArrayList<>();

        try {
            ResultSet set = this.getPersonnesByCours.executeQuery();

            while (set.next()) {
                String cours = set.getString(1);
                String nom = set.getString(2);
                String prenom = set.getString(3);

                personnesCours.add(cours + " : " + prenom + " " + nom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personnesCours;
    }
}
