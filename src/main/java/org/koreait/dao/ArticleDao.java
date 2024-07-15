package org.koreait.dao;

import org.koreait.dto.Article;
import org.koreait.util.DButil;
import org.koreait.util.SecSql;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ArticleDao {
    Connection conn;
    public ArticleDao(Connection conn) {
        this.conn = conn;
    }
    public int dowrite(String title, String body) {
        SecSql sql = new SecSql();

        sql.append("INSERT INTO article");
        sql.append("SET regDate = NOW(),");
        sql.append("updateDate = NOW(),");
        sql.append("title = ?,", title);
        sql.append("`body`= ?;", body);

        return DButil.insert(conn, sql);
    }

    public List<Article> getArticles() {
        SecSql sql = new SecSql();
        sql.append("SELECT *");
        sql.append("FROM article");
        sql.append("ORDER BY id DESC");

        List<Map<String, Object>> articleListMap = DButil.selectRows(conn, sql);

        List<Article> articles = new ArrayList<>();

        for (Map<String, Object> articleMap : articleListMap) {
            articles.add(new Article(articleMap));
        }
        return articles;
    }

    public Map<String, Object> getArticlesById(int id) {
        SecSql sql = new SecSql();
        sql.append("SELECT *");
        sql.append("FROM article");
        sql.append("WHERE id = ?", id);

        return  DButil.selectRow(conn, sql);
    }

    public void doupdate(int id, String title, String body  ) {

        SecSql sql = new SecSql();

        sql.append("UPDATE article");
        sql.append("SET updateDate = NOW()");
        if (title.length() > 0) {
            sql.append(",title = ?", title);
        }
        if (body.length() > 0) {
            sql.append(",`body` = ?", body);
        }
        sql.append("WHERE id = ?", id);
        DButil.update(conn, sql);
    }

    public void dodelete(int id ) {
        SecSql sql = new SecSql();
        sql.append("DELETE FROM article");
        sql.append("WHERE id = ?", id);

        DButil.delete(conn, sql);
    }
}
