package com.robnarok.yoneban.services;

import com.robnarok.yoneban.model.PersistentMatch;
import com.robnarok.yoneban.wrapper.Matchdata;
import com.robnarok.yoneban.wrapper.MatchdataList;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BanService {

    @Autowired
    JDA jda;

    @Autowired
    DiscordPrint discordPrint;


    public MatchdataList removeInvalid(MatchdataList matchdataList){
        MatchdataList returnList = new MatchdataList();
        for (Matchdata matchdata : matchdataList.getMatchdata()){
            if (matchdata.isValid()){
                returnList.addMatchdata(matchdata);
            }

        }
        return returnList;
    }

    public MatchdataList filterWithPersistenMatch(List<PersistentMatch> persistentMatchList, MatchdataList matchdataList){

        MatchdataList returnList = new MatchdataList();
        boolean checkIfDouble = false;

        for (Matchdata matchdata : matchdataList.getMatchdata()){
            for (PersistentMatch persistentMatch : persistentMatchList){
                if(persistentMatch.getMatchID().equals(matchdata.getMatchId())){
                    checkIfDouble = true;
                    break; //Performance MinMax
                }
            }
            if (!checkIfDouble) {
                returnList.addMatchdata(matchdata);
            }
            checkIfDouble = false;
        }

        return returnList;
    }

    public void printNewBanEvents (List<PersistentMatch> persistentMatchList){
        for (PersistentMatch persistentMatch : persistentMatchList){
            discordPrint.printToDiscord("Champion has been banned!");
        }
    }

}
