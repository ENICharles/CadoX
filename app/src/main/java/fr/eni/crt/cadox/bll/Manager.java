package fr.eni.crt.cadox.bll;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import fr.eni.crt.cadox.bo.Article;
import fr.eni.crt.cadox.dal.Dal;

public class Manager
{
    private List<Article> articles;

    public Manager()
    {
        updateListArticle();
        articles = getArticles();
    }

    /**
     * retourne la liste des articles
     * @return
     */
    public List<Article> getArticles()
    {
        return articles;
    }

    /**
     * retourne l'article choisi par son ID
     * @param id
     * @return
     */
    public Article getArticleById(int id)
    {
        Article ret = null;
        for (Article ar: this.articles)
        {
            if (ar.getId() == id)
            {
                ret = ar;
            }
        }

        return ret;
    }

    /**
     * Met à jour la liste des articles
     */
    public void updateListArticle()
    {
        Dal dal = new Dal();

        articles = dal.getArticles();
    }

    /**
     * Met à jour la liste des articles
     */
    public void updateArticle(Article article)
    {
        Dal dal = new Dal();

        dal.updateArticle(article);
    }
}
