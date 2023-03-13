package com.example.player.controller;

import java.util.ArrayList;

import com.example.player.model.Player;
import com.example.player.service.PlayerH2Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
 
 @RestController
 public class PlayerController{

    @Autowired
    PlayerH2Service playerservice;
    
    @GetMapping("/players")
    public ArrayList<Player> getAllPlayers(){
        return playerservice.getAllPlayers();
    }

    @PostMapping("/players")

    public Player addPlayer(@RequestBody Player player){
        return playerservice.addPlayer(player);
    }

    @GetMapping("/players/{playerId}")

    public Player getSpecificPlayer(@PathVariable("playerId") int playerId){
        return playerservice.getPlayer(playerId);
    }

    @PutMapping("/players/{playerId}")

    public Player updatePlayer(@PathVariable("playerId") int playerId,@RequestBody Player player){
        return playerservice.updatePlayer(playerId, player);
    }
    
    @DeleteMapping("/players/{playerId}")
    public void deletePlayer(@PathVariable("playerId") int playerId){
        playerservice.deletePlayer(playerId);
    }
 }