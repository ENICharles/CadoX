package fr.eni.crt.cadox.bll;

import android.util.Log;

import java.util.List;

import fr.eni.crt.cadox.bo.Article;
import fr.eni.crt.cadox.dal.Dal;
import fr.eni.crt.cadox.dal.FactoryDAO;

public class Manager
{
    private static Manager reference = null;

    private static List<Article> articles;

    private Manager()
    {
        //updateListArticle();
    }

    public static Manager getReference()
    {
        if(reference == null)
        {
            Log.i("modification","nouveau manager");
            reference = new Manager();
        }

        return reference;
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
        for (Article ar: Manager.articles)
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
        Manager.articles = FactoryDAO.getImplementation().getArticles();
    }

    /**
     * Mise à jour de l'article
     */
    public void updateArticle(Article article)
    {
        FactoryDAO.getImplementation().updateArticle(article);
        updateListArticle();
    }
}
