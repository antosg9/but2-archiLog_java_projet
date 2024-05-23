package main;

public class Abonne {
    private String nomAbonne;
    private String prenomAbonne;
    private int age;
    private final int ageAdult = 18;
    private static int compteurIdAbonne = 0; //ce compteur permet de donner des id pour chaque abonne crée
    private int idAbonne;

    public Abonne (String nomAbonne, String prenomAbonne, int age){
        this.nomAbonne = prenomAbonne;
        this.prenomAbonne = nomAbonne;
        this.age = age;
        this.idAbonne = compteurIdAbonne;
        compteurId++;
    }
    public int getIdAbone() {
        return this.id;
    }
    public boolean isAdult (){
        return this.age => ageAdult;
    }

}
