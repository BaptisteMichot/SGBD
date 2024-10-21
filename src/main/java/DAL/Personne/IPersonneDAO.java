package main.java.DAL.Personne;

import java.util.ArrayList;
import main.java.BL.Personne.Personne;

public interface IPersonneDAO {

    public ArrayList<Personne> getPersonne();

    public int getIDPersonne(String nom, String prenom);

    public boolean updatePersonne(int id, String nom, String prenom);

    public boolean deletePersonne(int id);

    public boolean addPersonne(int idSection, String nom, String prenom);

    public ArrayList<String> getPersonneAndStatus();    
}
