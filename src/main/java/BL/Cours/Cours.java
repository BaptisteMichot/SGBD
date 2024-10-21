package main.java.BL.Cours;

public class Cours {

    //variables
    private final int id;
    private String nom;
    private int idSection;

    /*
     * Classe permettant la modélisation d'un cours.
     * 
     * @param id Identifiant du cours
     * @param nom Nom du cours
     * @param idSection Identifiant de la section à laquelle le cours appartient
     */
    public Cours(int id, int idSection, String nom) {
        this.id = id;
        this.nom = nom;
        this.idSection = idSection;
    }

    /*
     * @return int retourne l'identifiant
     */
    public int getId() {
        return this.id;
    }

    /*
     * @return int retourne l'id de la section
     */
    public int getIdSection() {
        return this.idSection;
    }

    /*
     * @param idSection L'identifiant de la section à mettre
     */
    public void setIdSection(int idSection) {
        this.idSection = idSection;
    }


    /*
     * @return String retourne le nom
     */
    public String getNom() {
        return this.nom;
    }

    /*
     * @param nom Le nom à mettre
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
}
