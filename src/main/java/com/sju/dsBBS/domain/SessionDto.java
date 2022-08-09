package com.sju.dsBBS.domain;

import com.mysql.cj.protocol.x.Notice;

import java.util.Date;

public class SessionDto {
    private String sessionId;
    private String userId;
    private Date sessionLimit;

    public SessionDto() {}
    public SessionDto(String sessionId, String userId, Date sessionLimit) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.sessionLimit = sessionLimit;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Date getSessionLimit() {
        return sessionLimit;
    }

    public void setSessionLimit(Date sessionLimit) {
        this.sessionLimit = sessionLimit;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "SessionDto{" +
                "sessionId='" + sessionId + '\'' +
                ", sessionLimit=" + sessionLimit +
                ", userId='" + userId + '\'' +
                '}';
    }
}
