package fr.eni.crt.cadox.dal;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import fr.eni.crt.cadox.bo.Article;

public class DAOImp implements Dal
{
    public DAOImp()
    {

    }

    public List<Article> getArticles()
    {
        List<Article> articles = new ArrayList<Article>();
        Article article;

        Date date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();

        article = new Article(
                1,
                "Des lunettes de soleil (memory)",
                "RAY-BAN RB 425999 601/19 51/20",
                85.0F,
                true,
                (byte) 4,
                "http://www.optical-center.fr/lunettes-de-soleil-ray-ban-6.html",
                date);

        articles.add(article);

        return articles;
    }

    /**
     * Met à jour la liste des articles
     */
    public void updateArticle(Article article)
    {
        Log.i("dal","mise à jour de l'article en base " + article.getDescription());
    }
}
