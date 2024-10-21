package main.java.DAL.Cours_Personne;

import java.util.ArrayList;

public interface ICours_PersonneDAO {
    
    public boolean addCoursPersonne(int idPersonne, int idCours);

    public boolean deleteCoursPersonne(int idPersonne, int idCours);

    public ArrayList<String> getPersonnesByCours();
}
