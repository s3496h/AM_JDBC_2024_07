package org.koreait;

import java.sql.*;
import java.util.Scanner;

import org.koreait.Controller.ArticleController;
import org.koreait.Controller.MemberController;
import org.koreait.container.Container;

public class App {

    private Scanner sc;

    public App() {
        Container.init();
        this.sc = Container.sc;
    }

    public void run() {
        System.out.println("==프로그램 시작==");


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

                Container.conn = conn;
                int actionResult = action(cmd);

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

    private int action(String cmd) {
        MemberController memberController = Container.memberController;
        ArticleController articleController = Container.articleController;


        if (cmd.equals("exit")) {
            return -1;
        }
        if (cmd.equals("member logout")) {
            memberController.logout();
        } else if (cmd.equals("member profile")) {
            memberController.showProfile();
        } else if (cmd.equals("member login")) {
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


