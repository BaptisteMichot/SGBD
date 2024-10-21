package main.java.BL.Status;

public class Status {
    
    //variables
    private final int id;
    private String nom;

    
    /*
     * Classe permettant la modélisation d'un status/ d'une profession.
     *  @param id Identifiant du status
     * @param nom Titre de la profession/ du status
     */
    public Status(int id, String nom){

        this.id = id;
        this.nom = nom;
    }

    
    /*
     *  @return int retourne l'identifiant
     */
    public int getId(){

        return this.id;
    }

    /*
     * @return String retourne le status
     */
    public String getNom(){

        return this.nom;
    }

    /*
     * @param nom Le status à mettre
     */
    public void setNom(String nom){

        this.nom = nom;
    } 
}
