package fr.eni.crt.cadox.dal;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import fr.eni.crt.cadox.bo.Article;

@Dao
public interface Dal
{
//    @Query("UPDATE Articles SET  Nom = 'nouvelle valeur' WHERE condition")
//    public void          updateArticle(Article article);

    @Query("SELECT * FROM Articles ORDER BY Nom")
    public List<Article> getArticles();

    @Update()
    void updateArticle(Article article);

    @Insert()
    void insert(Article data);
}
