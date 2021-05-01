package com.robnarok.yoneban.repository;

import com.robnarok.yoneban.model.PersistentMatch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface PersistentMatchRepository extends CrudRepository <PersistentMatch, Long> {

    List<PersistentMatch> findAll();

    List<PersistentMatch> findAllByChampionID(String championId);

    int countByChampionIDAndGotBannedIsTrue(String championId);

}
