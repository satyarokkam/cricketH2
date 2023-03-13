package com.example.player.repository;

import java.util.*;

import com.example.player.model.*;

public interface PlayerRepository{
    ArrayList<Player>getAllPlayers();

    Player addPlayer(Player player);

    Player getPlayer(int id);

    Player updatePlayer(int playerId,Player player);

    void deletePlayer(int playerId);
}

