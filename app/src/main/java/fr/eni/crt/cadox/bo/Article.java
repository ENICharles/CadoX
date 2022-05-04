package fr.eni.crt.cadox.bo;

import android.icu.text.SimpleDateFormat;

import java.util.Date;

public class Article
{
    private int     id;
    private String  intitule;
    private String  description;
    private Float   prix;
    private boolean achete; //acheté ou non
    private Date    dateAchat;
    private byte    niveau; // 0 - 5 (+ ou - satisfait)
    private String  url;

    public Article(int id, String intitule, String description, Float prix, boolean achete, byte niveau, String url,Date    dateAchat)
    {
        this.id          = id;
        this.intitule    = intitule;
        this.description = description;
        this.prix        = prix;
        this.achete      = achete;
        this.dateAchat   = dateAchat;
        this.niveau      = niveau;
        this.url         = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public boolean isAchete() {
        return achete;
    }

    public void setAchete(boolean achete) {
        this.achete = achete;
    }

    public String getDateAchat()
    {
        SimpleDateFormat forDate = new SimpleDateFormat("dd/MM/YYYY");

        return forDate.format(dateAchat);
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public byte getNiveau() {
        return niveau;
    }

    public void setNiveau(byte niveau) {
        this.niveau = niveau;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}