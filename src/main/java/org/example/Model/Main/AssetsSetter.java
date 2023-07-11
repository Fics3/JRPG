package org.example.Model.Main;

import org.example.IO.InOut;
import org.example.Model.Entity.Enemies.Enemy_GreenBoy;
import org.example.Model.Entity.NPC.NPC_GreenBoy;
import org.example.Model.Object.*;
import org.example.Model.Object.Armor.OBJ_leatherBoots;
import org.example.Model.Object.Armor.OBJ_leatherChestplate;
import org.example.Model.Object.Armor.OBJ_leatherHelmet;
import org.example.Model.Object.OBJ_chest;
import org.example.Model.Object.Object;

public class AssetsSetter {

    private GameCFG gameCFG;
    private int mapColData[][];

    public AssetsSetter(GameCFG gameCFG){
        this.gameCFG=gameCFG;
        mapColData = new int[gameCFG.getMaxWorldCol()][gameCFG.getMaxWorldRow()];
        InOut inOut = new InOut();
            inOut.getDataMap(gameCFG);
        mapColData = inOut.getDataMap(gameCFG);
    }


    public void setObject() {
        for (int i = 0; i < gameCFG.getMaxWorldCol(); i++) {
            for (int j = 0; j < gameCFG.getMaxWorldRow(); j++) {
                int obj = mapColData[i][j];
                Object object = chooseObj(obj);
                if(obj>1 && object!=null){
                    object.setX(i* gameCFG.getTileSize());
                    object.setY(j* gameCFG.getTileSize());
                    object.setSolidAreaDefault(i* gameCFG.getTileSize(),j* gameCFG.getTileSize());
                    object.setId(gameCFG.getObjects().size());
                    System.out.println(object.getId());
                    gameCFG.setObject(object);
                }
            }
        }
    }

    public void setNpc()  {
        gameCFG.setNpcs(new NPC_GreenBoy(gameCFG,gameCFG.getMaxWorldWight()/2,gameCFG.getMaxWorldHeight()/2+ gameCFG.getTileSize()*2,5));

    }
    public void setEnemy() {
        gameCFG.setNpcs(new Enemy_GreenBoy(gameCFG,gameCFG.getMaxWorldWight()/2+gameCFG.getTileSize(),gameCFG.getMaxWorldHeight()/2+ gameCFG.getTileSize()*4,4));
    }
    public Object chooseObj(int id){
        if(id == 2){
            OBJ_chest objChest = new OBJ_chest(gameCFG);
            return objChest;
        }
        if(id == 3){
            OBJ_dungeon objDungeon = new OBJ_dungeon(gameCFG);
            return objDungeon;
        }
        if(id == 4 ){
            OBJ_healthPotion objPotion = new OBJ_healthPotion(gameCFG);
            return objPotion;
        }
        if(id == 5){
            OBJ_woodSword objWoodSword = new OBJ_woodSword(gameCFG);
            return objWoodSword;
        }
        if(id == 6){
            OBJ_ironSword objIronSword = new OBJ_ironSword(gameCFG);
            return objIronSword;
        }
        if(id == 7){
            OBJ_leatherChestplate objLeatherChestplate = new OBJ_leatherChestplate(gameCFG);
            return objLeatherChestplate;
        }
        if(id == 8){
            OBJ_leatherHelmet objLeatherHelmet = new OBJ_leatherHelmet(gameCFG);
            return objLeatherHelmet;
        }
        if(id == 9 ){
            OBJ_leatherBoots objLeatherBoots = new OBJ_leatherBoots(gameCFG);
            return objLeatherBoots;
        }
        return null;
    }
    public int getMapColData(int i,int i1) {
        return mapColData[i][i1];
    }
}
