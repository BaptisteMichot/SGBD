package main.java.DAL;

import main.java.DAL.Cours.ICoursDAO;
import main.java.DAL.Cours_Personne.ICours_PersonneDAO;
import main.java.DAL.Personne.IPersonneDAO;
import main.java.DAL.Section.ISectionDAO;
import main.java.DAL.Status.IStatusDAO;

public interface IDAOFactory {
    
    public boolean close();

    public ISectionDAO createSectionDAO();

    public IStatusDAO createStatusDAO();

    public IPersonneDAO createPersonneDAO();

    public ICoursDAO createCoursDAO();

    public ICours_PersonneDAO createCoursPersonneDAO();
}
