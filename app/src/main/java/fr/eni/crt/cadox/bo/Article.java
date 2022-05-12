package fr.eni.crt.cadox.bo;

import android.icu.text.SimpleDateFormat;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.util.Date;

@Entity(tableName = "Articles", indices = @Index(value={"Nom"},unique = true))
public class Article implements Parcelable
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    private int     id;
    @ColumnInfo(name = "Nom")
    private String  intitule;
    @ColumnInfo(name = "Description")
    private String  description;
    @ColumnInfo(name = "Prix")
    private Float   prix;
    @ColumnInfo(name = "Acheté")
    private boolean achete; //acheté ou non
    @ColumnInfo(name = "DateAchat")
    private LocalDate    dateAchat;
    @ColumnInfo(name = "Satisfaction")
    private byte    niveau; // 0 - 5 (+ ou - satisfait)
    @ColumnInfo(name = "Lien")
    private String  url;

    public Article(
                   int       id,
                   String    intitule,
                   String    description,
                   Float     prix,
                   boolean   achete,
                   byte      niveau,
                   String    url,
                   LocalDate dateAchat)
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

    @Ignore
    public Article(
            String    intitule,
            String    description,
            Float     prix,
            byte      niveau,
            String    url)
    {
        this.id          = id;
        this.intitule    = intitule;
        this.description = description;
        this.prix        = prix;
        this.niveau      = niveau;
        this.url         = url;
    }

    @Ignore
    public Article(
            String    intitule,
            String    description,
            Float     prix,
            boolean   achete,
            byte      niveau,
            String    url,
            LocalDate dateAchat)
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

    @Ignore
    protected Article(Parcel in)
    {
        id = in.readInt();
        intitule = in.readString();
        description = in.readString();
        if (in.readByte() == 0)
        {
            prix = null;
        }
        else
        {
            prix = in.readFloat();
        }
        achete = in.readByte() != 0;
        niveau = in.readByte();
        url = in.readString();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>()
    {
        @Override
        public Article createFromParcel(Parcel in)
        {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size)
        {
            return new Article[size];
        }
    };

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

    public LocalDate getDateAchat()
    {
        SimpleDateFormat forDate = new SimpleDateFormat("dd/MM/YYYY");

        return dateAchat;
    }

    public void setDateAchat(LocalDate dateAchat) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(id);
        parcel.writeString(intitule);
        parcel.writeString(description);
        if (prix == null)
        {
            parcel.writeByte((byte) 0);
        }
        else
        {
            parcel.writeByte((byte) 1);
            parcel.writeFloat(prix);
        }

        parcel.writeByte((byte) (achete ? 1 : 0));
        parcel.writeByte(niveau);
        parcel.writeString(url);
    }
}
