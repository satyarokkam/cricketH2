package com.example.player.service;

import java.util.ArrayList;
import java.util.List;

import com.example.player.model.Player;
import com.example.player.model.PlayerRowMapper;
import com.example.player.repository.PlayerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PlayerH2Service implements PlayerRepository{

    @Autowired
    private JdbcTemplate db;

    @Override 
    public ArrayList<Player>getAllPlayers(){
        List<Player>playerList=db.query("select * from Team",new PlayerRowMapper());
        ArrayList<Player>players=new ArrayList<>(playerList);
        return players;
    }

    @Override

    public Player addPlayer(Player player){
        db.update("insert into Team (playerName,jerseyNumber,role) values(?,?,?)",player.getPlayerName(),player.getJerseyNumber(),player.getRole());

        Player addedplayer=db.queryForObject("select * from Team where jerseyNumber=? and playerName=? and role=?",new PlayerRowMapper(),player.getJerseyNumber(),player.getPlayerName(),player.getRole());

        return addedplayer;
    }

    public Player getPlayer(int id){
        try{
            Player specificPlayer=db.queryForObject("select * from Team where playerId=?",new PlayerRowMapper(),id);
            return specificPlayer;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        
        
    }

    public Player updatePlayer(int playerId,Player player){
        
        if(player.getPlayerName()!=null){
            db.update("update Team set playerName=? where playerId=?",player.getPlayerName(),playerId);
        }

        if(player.getRole()!=null){
            db.update("update Team set role=? where playerId=?",player.getRole(),playerId);
        }

        if(player.getJerseyNumber()!=0){
            db.update("update Team set jerseyNumber=? where playerId=?",player.getJerseyNumber(),playerId);
        }

        return getPlayer(playerId);

    }

    public void deletePlayer(int playerId){
        db.update("DELETE FROM TEAM WHERE playerId=?",playerId);
    }


}