package main.java.BL.Personne;

public class Personne {

    //variables
    private final int id;
    private String nom;
    private String prenom;
    private int idStatus;

    /*
     * Classe permettant la modélisation d'une personne.
     * 
     * @param id Identifiant de personne
     * @param nom Nom de la personne
     * @param prenom Prénom de la personne
     * @param idStatus Identifiant du status de la personne
     */
    public Personne(int id, int idStatus, String nom, String prenom) {
        this.id = id;
        this.idStatus = idStatus;
        this.nom = nom;
        this.prenom = prenom;
    }

    /*
     * @return int retourne l'identifiant
     */
    public int getId() {
        return this.id;
    }

    /*
     * @return int retourne l'id du status
     */
    public int getIdStatus() {
        return this.idStatus;
    }

    /*
     * @param idStatus L'identifiant du status à mettre
     */
    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
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

    /*
     * @return String retourne le prénom
     */
    public String getPrenom() {
        return this.prenom;
    }

    /*
     * @param nom Le prénom à mettre
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
