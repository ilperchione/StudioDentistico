package com.example.studiodentistico;

public class Inserimento {
    private String cognome;
    private String nome;
    private String data;
    private String patologia;

    public Inserimento(String cognome, String nome, String data, String patologia) {
        this.cognome = cognome;
        this.nome = nome;
        this.data = data;
        this.patologia = patologia;
    }

    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getPatologia() {
        return patologia;
    }
    public void setPatologia(String patologia) {
        this.patologia = patologia;
    }

}
