package com.robnarok.yoneban.wrapper;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ban {
    private String championId;
    private String pickTurn;


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
