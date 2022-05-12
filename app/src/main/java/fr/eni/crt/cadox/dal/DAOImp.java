package fr.eni.crt.cadox.dal;

import android.util.Log;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import fr.eni.crt.cadox.bll.Manager;
import fr.eni.crt.cadox.bo.Article;

public class DAOImp implements Dal
{
    private List<Article> articles = new ArrayList<Article>();
    private static DAOImp reference = null;

    private DAOImp()
    {

    }

    public static  DAOImp getReference()
    {
        if(reference == null)
        {
            Log.i("modification","nouveau dao");
            reference = new DAOImp();
        }

        return reference;
    }

    public List<Article> getArticles()
    {
        Random r = new Random();
        int low = 10;
        int high = 100;


        if(articles.size()==0)
        {
            Log.i("modification","création d'un nouvel article");

            Article article;
            Date date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();

            for(int iloop=1;iloop<10;iloop++)
            {
                article = new Article(
                        iloop,
                        "Des lunettes de soleil (memory)",
                        "RAY-BAN RB 425999 601/19 51/20",
                        Float.valueOf (r.nextInt(100-10) + 10),
                        true,
                        (byte)(r.nextInt(4) + 1),
                        "http://www.optical-center.fr/lunettes-de-soleil-ray-ban-6.html",
                        LocalDate.now());

                articles.add(article);
            }
        }

        return articles;
    }

    @Override
    public void insert(Article data)
    {
        Log.i("modification","Dois pas passer par ici");
    }

    /**
     * Met à jour la liste des articles
     */
    public void updateArticle(Article article)
    {
        articles.clear();
        articles.add(article);

        Log.i("modification","mise à jour de l'article en base " + article.getDescription());
        Log.i("modification","mise à jour de l'article en base " + " " + articles.get(0).getId() + " " + articles.get(0).getDescription());
    }
}
