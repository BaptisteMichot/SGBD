package main;

import java.util.ArrayList;
import main.java.BL.Section.Section;
import main.java.DAL.Section.ISectionDAO;
import main.java.DAL.Cours.ICoursDAO;
import main.java.DAL.Status.IStatusDAO;
import main.java.BL.Status.Status;
import main.java.DAL.Personne.IPersonneDAO;
import main.java.DAL.Cours_Personne.ICours_PersonneDAO;
import main.java.DAL.DAOFactory;


public class Main {
    public static void main(String[] args) throws Exception {

        //pattern factory
        DAOFactory factory = new DAOFactory();

        ISectionDAO sectionDAO = factory.createSectionDAO();
        ICoursDAO coursDAO = factory.createCoursDAO();
        IStatusDAO statusDAO = factory.createStatusDAO();
        IPersonneDAO personneDAO = factory.createPersonneDAO();
        ICours_PersonneDAO coursPersonneDAO = factory.createCoursPersonneDAO();

        //création des sections
        sectionDAO.addSection("Informatique de gestion");
        sectionDAO.addSection("Droit");

        ArrayList<Section> sections = sectionDAO.getSections();

        //création des cours et ajout dans les sections
        for (Section sec : sections) {
            if (sec.getNom().equals("Informatique de gestion")) {
                coursDAO.addCours(sec.getId(), "Bases des réseaux");
                coursDAO.addCours(sec.getId(), "Systèmes d'exploitation");
                coursDAO.addCours(sec.getId(), "Programmation orientée objet");
            } else if (sec.getNom().equals("Droit")) {
                coursDAO.addCours(sec.getId(), "Droit civil");
                coursDAO.addCours(sec.getId(), "Droit commercial");
            }
        }

        //affichage des sections et des cours
        ArrayList<String> coursAndSections = coursDAO.getCoursAndSection();

        for (String cs : coursAndSections) {
            System.out.println(cs);
        }
        System.out.println("-----------------------------------------");

        //création des status
        statusDAO.addStatus("Étudiant");
        statusDAO.addStatus("Chargé de cours");
        statusDAO.addStatus("Employé administratif");

        ArrayList<Status> status = statusDAO.getStatus();

        //création des personnes et ajout d'un status
        for (Status sta : status) {
            if (sta.getNom().equals("Étudiant")) {
                personneDAO.addPersonne(sta.getId(), "Durant", "Richard");
                personneDAO.addPersonne(sta.getId(), "Ortiz", "Valerie");
            } else if (sta.getNom().equals("Chargé de cours")) {
                personneDAO.addPersonne(sta.getId(), "Poulet", "Gilles");
                personneDAO.addPersonne(sta.getId(), "Godissart", "Emmanuel");
            } else if (sta.getNom().equals("Employé administratif")) {
                personneDAO.addPersonne(sta.getId(), "Lai", "Valeria");
                personneDAO.addPersonne(sta.getId(), "Mairesse", "David");
            }
        }

        //affichage des status et des personnes
        ArrayList<String> personnesAndStatus = personneDAO.getPersonneAndStatus();

        for(String ps : personnesAndStatus) {
            System.out.println(ps);
        }
        System.out.println("-----------------------------------------");


        //lien entre les personnes et les cours
        coursPersonneDAO.addCoursPersonne(3, 2);
        coursPersonneDAO.addCoursPersonne(4, 1);
        coursPersonneDAO.addCoursPersonne(1, 2);
        coursPersonneDAO.addCoursPersonne(1, 1);

        //affichage des cours et des personnes liées
        ArrayList<String> personnesCours = coursPersonneDAO.getPersonnesByCours();

        for(String pc : personnesCours) {
            System.out.println(pc);
        }
        
        //fermeture connexion
        factory.close();
    }
}
