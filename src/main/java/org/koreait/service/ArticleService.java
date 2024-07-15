package org.koreait.service;

import org.koreait.dto.Article;
import org.koreait.dao.ArticleDao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class ArticleService {
    private ArticleDao articleDao;
    public ArticleService(Connection conn) {
        this.articleDao = new ArticleDao(conn);
    }

    public int dowrite(String title, String body) {

        return articleDao.dowrite(title ,body );
    }

    public List<Article> getArticles() {

        return articleDao.getArticles();
    }

    public Map<String, Object> getArticlesById(int id) {

        return articleDao.getArticlesById(id);
    }

    public void doupdate( int id ,String title, String body ) {

        articleDao.doupdate(id, title, body);
    }

    public void dodelete(int id) {
        articleDao.dodelete(id);
    }
}
