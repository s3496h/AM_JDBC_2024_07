package org.koreait.service;

import org.koreait.dao.MemberDao;

import org.koreait.dto.Member;
import java.sql.Connection;

public class MemberService {

    private MemberDao memberDao;

    public MemberService() {
        this.memberDao = new MemberDao();
    }

    public boolean isLoginIdDup(Connection conn, String loginId) {


        return MemberDao.isLoginIdDup(conn, loginId);
    }

    public int doJoin(String loginId, String loginPw, String name) {

        return memberDao.dojoin(loginId,loginPw,name);
    }
    public Member getMemberByLoginId(String loginId) {
        return memberDao.getMemberByLoginId(loginId);
    }
}
