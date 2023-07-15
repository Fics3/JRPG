package org.example.Model.Main;

import org.example.IO.InOut;
import org.example.Model.Entity.Enemies.Enemy_Ghost;
import org.example.Model.Entity.Enemies.Enemy_Goblin;
import org.example.Model.Entity.Enemies.Enemy_GreenBoy;
import org.example.Model.Entity.Enemies.Enemy_Orc;
import org.example.Model.Entity.Entity;
import org.example.Model.Entity.NPC.NPC_GreenBoy;
import org.example.Model.Object.*;
import org.example.Model.Object.Armor.*;
import org.example.Model.Object.Consumable.OBJ_healthPotion;
import org.example.Model.Object.Consumable.OBJ_manaPotion;
import org.example.Model.Object.OBJ_chest;
import org.example.Model.Object.Object;
import org.example.Model.Object.Weapon.OBJ_ironSword;
import org.example.Model.Object.Weapon.OBJ_legendarySword;
import org.example.Model.Object.Weapon.OBJ_woodSword;

import java.util.Objects;

public class AssetsSetter {

    private final GameCFG gameCFG;
    private int[][] mapColData;

    public AssetsSetter(GameCFG gameCFG){
        this.gameCFG=gameCFG;
        mapColData = new int[gameCFG.getMaxWorldCol()][gameCFG.getMaxWorldRow()];
        InOut inOut = new InOut();
        mapColData = inOut.getDataMap(gameCFG);
    }


    public void setObject() {
        for (int i = 0; i < gameCFG.getMaxWorldCol(); i++) {
            for (int j = 0; j < gameCFG.getMaxWorldRow(); j++) {
                int id = mapColData[i][j];
                if(id>1 && id < 18){
                    Object object = chooseObj(id);
                    if(object!=null) {
                        object.setX(i * gameCFG.getTileSize());
                        object.setY(j * gameCFG.getTileSize());
                        object.setSolidAreaDefault(i * gameCFG.getTileSize(), j * gameCFG.getTileSize());
                        object.setId(gameCFG.getObjects().size());
                        gameCFG.setObject(object);
                    }
                }
                if(id>=18){
                    Entity entity = chooseEntity(id);
                    if(entity!=null){
                        entity.setDefaultX(i * gameCFG.getTileSize());
                        entity.setDefaultY(j * gameCFG.getTileSize());
                        entity.setX(i * gameCFG.getTileSize());
                        entity.setY(j * gameCFG.getTileSize());
                        entity.setId(gameCFG.getNpcs().size());
                        gameCFG.setNpcs(entity);
                    }
                }
            }
        }
    }

//    public void setNpc()  {
//        gameCFG.setNpcs(new NPC_GreenBoy(gameCFG,gameCFG.getMaxWorldWight()/2,gameCFG.getMaxWorldHeight()/2+ gameCFG.getTileSize()*2,5));
//    }
//    public void setEnemy() {
//        gameCFG.setNpcs(new Enemy_Orc(gameCFG,gameCFG.getMaxWorldWight()/2+gameCFG.getTileSize(),gameCFG.getMaxWorldHeight()/2+ gameCFG.getTileSize()*4,4));
//    }
    public Object chooseObj(int id){
        if(id == 2){
            return new OBJ_chest(gameCFG);
        }
        if(id == 3){
            return new OBJ_dungeon(gameCFG);
        }
        if(id == 4 ){
            return new OBJ_healthPotion(gameCFG);
        }
        if(id == 5){
            return new OBJ_woodSword(gameCFG);
        }
        if(id == 6){
            return new OBJ_ironSword(gameCFG);
        }
        if(id == 7){
            return new OBJ_leatherChestplate(gameCFG);
        }
        if(id == 8){
            return new OBJ_leatherHelmet(gameCFG);
        }
        if(id == 9 ){
            return new OBJ_leatherBoots(gameCFG);
        }
        if(id == 10){
            return new OBJ_ironChestplate(gameCFG);
        }
        if(id == 11){
            return new OBJ_ironHelmet(gameCFG);
        }
        if(id == 12 ){
            return new OBJ_ironBoots(gameCFG);
        }
        if(id == 13){
            return new OBJ_legendaryChestplate(gameCFG);
        }
        if(id == 14){
            return new OBJ_legendaryHelmet(gameCFG);
        }
        if(id == 15 ){
            return new OBJ_legendaryBoots(gameCFG);
        }
        if(id == 16){
            return new OBJ_legendarySword(gameCFG);
        }
        if(id == 17){
            return new OBJ_manaPotion(gameCFG);
        }

        return null;
    }
    public Entity chooseEntity(int id){
        if(id == 18){
            return new Enemy_Ghost(gameCFG);
        }
        if(id == 19){
            return new Enemy_Goblin(gameCFG);
        }
        if(id == 20){
            return new Enemy_Orc(gameCFG);
        }
        if(id == 21){
            return new Enemy_Ghost(gameCFG);
        }
        return null;
    }
    public Object chooseObjName(String name) {
        if(Objects.equals(name, "chest")){
            return new OBJ_chest(gameCFG);
        }
        if(Objects.equals(name, "dungeon") ){
            return new OBJ_dungeon(gameCFG);
        }
        if(Objects.equals(name, "healthPotion")){
            return new OBJ_healthPotion(gameCFG);
        }
        if(Objects.equals(name, "woodSword")){
            return new OBJ_woodSword(gameCFG);
        }
        if(Objects.equals(name, "ironSword")){
            return new OBJ_ironSword(gameCFG);
        }
        if(Objects.equals(name, "leatherChestplate")){
            return new OBJ_leatherChestplate(gameCFG);
        }
        if(Objects.equals(name, "leatherHelmet")){
            return new OBJ_leatherHelmet(gameCFG);
        }
        if(Objects.equals(name, "leatherBoots")){
            return new OBJ_leatherBoots(gameCFG);
        }
        if(Objects.equals(name, "ironChestplate")){
            return new OBJ_ironChestplate(gameCFG);
        }
        if(Objects.equals(name, "ironHelmet")){
            return new OBJ_ironHelmet(gameCFG);
        }
        if(Objects.equals(name, "ironBoots") ){
            return new OBJ_ironBoots(gameCFG);
        }
        if(Objects.equals(name, "legendaryChestplate")){
            return new OBJ_legendaryChestplate(gameCFG);
        }
        if(Objects.equals(name, "legendaryHelmet")){
            return new OBJ_legendaryHelmet(gameCFG);
        }
        if(Objects.equals(name, "legendaryBoots") ){
            return new OBJ_legendaryBoots(gameCFG);
        }
        if(Objects.equals(name, "legendarySword")){
            return new OBJ_legendarySword(gameCFG);
        }
        if(Objects.equals(name, "manaPotion")){
            return new OBJ_manaPotion(gameCFG);
        }


        return null;
    }
}
