package fr.eni.crt.cadox.dal;

import java.util.List;

import fr.eni.crt.cadox.bo.Article;

public interface Dal
{
    public void          updateArticle(Article article);
    public List<Article> getArticles();
}
