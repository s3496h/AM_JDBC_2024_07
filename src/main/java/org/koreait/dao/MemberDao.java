package org.koreait.dao;

import org.koreait.util.DButil;
import org.koreait.util.SecSql;

import java.sql.Connection;

public class MemberDao {
    public static boolean isLoginIdDup(Connection conn, String loginId) {

        SecSql sql = new SecSql();

        sql.append("SELECT COUNT(*) > 0");
        sql.append("FROM `member`");
        sql.append("WHERE loginId = ?;", loginId);

        return DButil.selectRowBooleanValue(conn, sql);
    }
}
