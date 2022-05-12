package fr.eni.crt.cadox.bll;

import android.util.Log;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import fr.eni.crt.cadox.bo.Article;
import fr.eni.crt.cadox.dal.Dal;
import fr.eni.crt.cadox.dal.FactoryDAO;

public class Manager
{
    private static Manager reference = null;
    private static Dal     dao;

    //private static List<Article> articles;

    private Manager()
    {
        dao = FactoryDAO.getImplementation();
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
    public void getArticles(RepositoryEventCalback<List<Article>> callback)
    {
        Executor asyncTask = Executors.newSingleThreadExecutor();

        asyncTask.execute(()->
        {
            List<Article> list = dao.getArticles();
            callback.onComplete(list);
        });
    }

    public void add(Article article,RepositoryEventCalback<Article> callback)
    {
        Executor asyncTask = Executors.newSingleThreadExecutor();

        asyncTask.execute(()->
        {
            dao.insert(article);

            callback.onComplete(article);
        });
    }


    /**
     * Mise à jour de l'article
     */
    public void updateArticle(Article article)
    {
        Executor asyncTask = Executors.newSingleThreadExecutor();

        asyncTask.execute(()->
        {
            dao.updateArticle(article);
        });
    }
}
