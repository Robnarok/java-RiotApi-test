package com.robnarok.yoneban.wrapper;

public class Ban {
    private String championId;
    private String pickTurn;

    public Ban(String championId, String pickTurn){
        this.championId = championId;
        this.pickTurn = pickTurn;
    }

    public Ban() {

    }

    public String getChampionId() {
        return championId;
    }

    public String getPickTurn() {
        return pickTurn;
    }

    public void setChampionId(String championId){
        this.championId = championId;
    }

    public void setPickTurn(String pickTurn) {
        this.pickTurn = pickTurn;
    }

    public boolean isChampion (String championId){
        return championId.equals(this.championId);
    }

    @Override
    public String toString() {
        return "BanDTO{" +
                "championId='" + championId + '\'' +
                ", pickTurn='" + pickTurn + '\'' +
                '}';
    }
}
