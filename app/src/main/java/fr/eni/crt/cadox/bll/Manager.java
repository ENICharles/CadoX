package fr.eni.crt.cadox.bll;

import java.util.List;

import fr.eni.crt.cadox.bo.Article;
import fr.eni.crt.cadox.dal.Dal;
import fr.eni.crt.cadox.dal.FactoryDAO;

public class Manager
{
    private static Manager reference = null;

    private List<Article> articles;

    private Manager()
    {
        updateListArticle();
        articles = getArticles();
    }

    public static Manager getReference()
    {
        if(reference == null)
        {
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
        this.articles = FactoryDAO.getImplementation().getArticles();
    }

    /**
     * Mise à jour de l'article
     */
    public void updateArticle(Article article)
    {
        FactoryDAO.getImplementation().updateArticle(article);
    }
}
