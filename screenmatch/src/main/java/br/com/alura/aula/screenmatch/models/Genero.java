package br.com.alura.aula.screenmatch.models;

public enum Genero {
    ACAO("Action"),
    COMEDIA("Comedy"),
    CRIME("Crime"),
    DRAMA("Drama"),
    ROMANCE("Romance");

    private String generoOmbd;

    Genero(String generoOmbd){
        this.generoOmbd = generoOmbd;
    }

    public static Genero fromString(String type) {
        for(Genero genero : Genero.values()) {
            if (genero.generoOmbd.equalsIgnoreCase(type)){
                return genero;
            }
        }
        throw new IllegalArgumentException("Nenhum genero encontrado a partir da String passada");
    }
}
