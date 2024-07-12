package org.koreait.util;

public class Memeber {
    private String regDate;
    private int logInId;
    private String logInPw;
    private String UserName;
    public Memeber(String regDate, int logInId, String logInPw, String UserName) {
        this.regDate = regDate;
        this.logInId = logInId;
        this.logInPw = logInPw;
        this.UserName = UserName;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public int getLogInId() {
        return logInId;
    }

    public void setLogInId(int logInId) {
        this.logInId = logInId;
    }

    public String getLogInPw() {
        return logInPw;
    }

    public void setLogInPw(String logInPw) {
        this.logInPw = logInPw;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
}
