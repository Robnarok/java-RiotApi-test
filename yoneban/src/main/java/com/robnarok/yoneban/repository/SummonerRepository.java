package com.robnarok.yoneban.repository;

import com.robnarok.yoneban.model.Summoner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SummonerRepository extends CrudRepository<Summoner, String> {

    List<Summoner> findAll();

}
