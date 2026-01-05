package com.usermanager.model;

public class ClientModel {

        private Integer id;
        private String nom;
        private String prenom;
        private String email;
        private String adress;
        private String cin;
        private String numero;

    public ClientModel() {}

    public ClientModel(String nom, String prenom, String email, String adress, String cin, String numer) {
        this();
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

        public Integer getId() {
        return id;
    }

        public void setId(Integer id) {
        this.id = id;
    }

        public String getNom() {
        return nom;
    }

        public void setNom(String nom) {
        this.nom = nom;
    }

        public String getPrenom() {
        return prenom;
    }

        public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


        public String getEmail() {
        return email;
    }

        public void setEmail(String email) {
        this.email = email;
    }

        public String getAdress() {
        return adress;
    }

        public void setAdress(String adress) {
        this.adress = adress;
    }

        public String getCin() {
        return cin;
    }

        public void setCin(String cin) {
        this.cin = cin;
    }

        public String getNumero() {
        return numero;
    }

        public void setNumero(String numero) {
        this.numero = numero;
    }

        @Override
        public String toString() {
        return "User{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
