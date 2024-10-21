package main.java.DAL.Cours;

import java.util.ArrayList;
import main.java.BL.Cours.Cours;

public interface ICoursDAO {

    public ArrayList<Cours> getCours();

    public int getIDCours(String nom);

    public boolean updateCours(int id, String nom);

    public boolean deleteCours(int id);

    public boolean addCours(int idSection, String nom);

    public ArrayList<String> getCoursAndSection();
}
