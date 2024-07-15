package org.koreait;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.koreait.Controller.ArticleController;
import org.koreait.Controller.MemberController;
import org.koreait.util.DButil;
import org.koreait.util.SecSql;

import java.util.Map;

public class App {

    public void run() {
        System.out.println("==프로그램 시작==");
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("명령어 > ");
            String cmd = sc.nextLine().trim();

            Connection conn = null;

            try {
                Class.forName("org.mariadb.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            String url = "jdbc:mariadb://127.0.0.1:3306/AM_JDBC_2024_07?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";

            try {
                conn = DriverManager.getConnection(url, "root", "");

                int actionResult = Action(conn, sc, cmd);

                if (actionResult == -1) {
                    System.out.println("==프로그램 종료==");
                    sc.close();
                    break;
                }

            } catch (SQLException e) {
                System.out.println("에러 1 : " + e);
            } finally {
                try {
                    if (conn != null && !conn.isClosed()) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private int Action(Connection conn, Scanner sc, String cmd) {
        MemberController memberController = new MemberController(sc, conn);
        ArticleController articleController = new ArticleController(sc, conn);


        if (cmd.equals("exit")) {
            return -1;
        }
        if (cmd.equals("member login")) {
            memberController.login();
        } else if (cmd.equals("member join")) {
            memberController.dojoin();
        } else if (cmd.equals("article write")) {
           articleController.dowrite();
        } else if (cmd.equals("article list")) {
          articleController.showlist();
        } else if (cmd.startsWith("article modify")) {
            articleController.domodifi(cmd);
        } else if (cmd.startsWith("article detail")) {
            articleController.showdetail(cmd);
        } else if (cmd.startsWith("article delete")) {
            articleController.dodelete(cmd);
        }else {
            System.out.println("사용 할 수 없는 명령어");
        }
        return 0;
    }
}


