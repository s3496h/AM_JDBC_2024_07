package org.koreait;

import java.sql.*;

public class JDBCDeleteTest {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM GOODSINFO WHERE CODE = ?";
        String code = "";

        try {


            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://127.0.0.1:3306/AM_JDBC_2024_07?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
            conn = DriverManager.getConnection(url, "root", "");

            System.out.println(sql);
            System.out.println("삭제할 코드를 입력하세요.");

            code = sc.next();
             pstmt.setString(1, code);
             int cnt = pstmt.executeUpdate();
            System.out.println(cnt);
            pstmt = conn.prepareStatement(sql);

            int affectedRows = pstmt.executeUpdate();

            System.out.println("affected rows: " + affectedRows);

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패" + e);
        } catch (SQLException e) {
            System.out.println("에러 : " + e);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (pstmt != null && !pstmt.isClosed()) {
                    pstmt.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}