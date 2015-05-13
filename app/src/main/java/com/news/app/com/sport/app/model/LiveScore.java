package com.news.app.com.sport.app.model;

/**
 * Created by simpumind on 5/7/15.
 */
public class LiveScore {

    public Integer leagueId;
    public String macthName;
    public String matchTime;
    public String localTeam;
    public String visitorTeam;
    public String date;
    public String local_score;
    public String visitor_score;

    public LiveScore(Integer leagueId, String macthName, String matchTime, String localTeam, String visitorTeam,
                     String date, String local_score, String visitor_score) {
        this.leagueId = leagueId;
        this.macthName = macthName;
        this.matchTime = matchTime;
        this.localTeam = localTeam;
        this.visitorTeam = visitorTeam;
        this.date = date;
        this.local_score = local_score;
        this.visitor_score = visitor_score;
    }

    public Integer getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
    }

    public String getLocal_score() {
        return local_score;
    }

    public void setLocal_score(String local_score) {
        this.local_score = local_score;
    }

    public String getVisitor_score() {
        return visitor_score;
    }

    public void setVisitor_score(String visitor_score) {
        this.visitor_score = visitor_score;
    }

    public String getMacthName() {
        return macthName;
    }

    public void setMacthName(String macthName) {
        this.macthName = macthName;
    }

    public String getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(String matchTime) {
        this.matchTime = matchTime;
    }

    public String getLocalTeam() {
        return localTeam;
    }

    public void setLocalTeam(String localTeam) {
        this.localTeam = localTeam;
    }

    public String getVisitorTeam() {
        return visitorTeam;
    }

    public void setVisitorTeam(String visitorTeam) {
        this.visitorTeam = visitorTeam;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
