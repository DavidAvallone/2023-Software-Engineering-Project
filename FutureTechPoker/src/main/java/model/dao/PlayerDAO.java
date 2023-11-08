package model.dao;

import model.entity.PlayerEntity;

public class PlayerDAO extends GenericDAO<PlayerEntity> {

    public PlayerDAO(){
        super(PlayerEntity.class);
    }
}
