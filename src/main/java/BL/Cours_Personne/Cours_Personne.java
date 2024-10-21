package main.java.BL.Cours_Personne;

public class Cours_Personne {

    //variables
    private int idPersonne;
    private int idCours;

    //constructeur
    public Cours_Personne(int idPersonne, int idCours){

        this.idPersonne = idPersonne;
        this.idCours = idCours;
    }
    

    /*
     * @return int retourne l'id de la personne
     */
    public int getIdPersonne() {
        return this.idPersonne;
    }

    /*
     * @param idPersonne L'identifiant de la personne à mettre
     */
    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    /*
     * @return int retourne l'id du cours
     */
    public int getIdCours() {
        return this.idCours;
    }

    /*
     * @param idPersonne L'identifiant du cours à mettre
     */
    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }
}
