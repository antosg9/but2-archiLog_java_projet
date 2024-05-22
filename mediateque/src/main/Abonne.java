package main;

public class Abonne {
    private String nomAbonne;
    private String prenomAbonne;
    private int age;
    private static int ageAdult = 18;

    public Abonne (String nomAbonne, String prenomAbonne, int age){
        this.nomAbonne = prenomAbonne;
        this.prenomAbonne = nomAbonne;
        this.age = age
    }

    public boolean isAdult (){
        return this.age => ageAdult;
    }
}
