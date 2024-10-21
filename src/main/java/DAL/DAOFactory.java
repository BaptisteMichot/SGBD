package main.java.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import main.java.DAL.Section.ISectionDAO;
import main.java.DAL.Section.SectionDAO;
import main.java.DAL.Status.IStatusDAO;
import main.java.DAL.Status.StatusDAO;
import main.java.DAL.Personne.IPersonneDAO;
import main.java.DAL.Personne.PersonneDAO;
import main.java.DAL.Cours.ICoursDAO;
import main.java.DAL.Cours.CoursDAO;
import main.java.DAL.Cours_Personne.ICours_PersonneDAO;
import main.java.DAL.Cours_Personne.Cours_PersonneDAO;


public class DAOFactory implements IDAOFactory {

    private Connection connexion;

    final String url = "jdbc:postgresql://127.0.0.1/ue1396";
    final String user = "postgres";
    final String password = "toto1234";

    public DAOFactory(){

        try{
            this.connexion = DriverManager.getConnection(url, user, password);

        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    public boolean close() {

        if (this.connexion != null) {
            try {
                this.connexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }


    public ISectionDAO createSectionDAO() {

        return new SectionDAO(this.connexion);
    }
    
    public IStatusDAO createStatusDAO() {

        return new StatusDAO(this.connexion);
    }

    public IPersonneDAO createPersonneDAO() {

        return new PersonneDAO(this.connexion);
    }

    public ICoursDAO createCoursDAO() {

        return new CoursDAO(this.connexion);
    }

    public ICours_PersonneDAO createCoursPersonneDAO() {

        return new Cours_PersonneDAO(this.connexion);
    }
}
