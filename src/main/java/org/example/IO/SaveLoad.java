package org.example.IO;

import org.example.Model.Entity.Player;
import org.example.Model.Main.AssetsSetter;
import org.example.Model.Main.GameCFG;
import org.example.Model.Object.Object;

import java.io.*;

public class SaveLoad {

    GameCFG gameCFG;

    public SaveLoad(GameCFG gameCFG){this.gameCFG=gameCFG;}

    public void save() {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DataStorage dataStorage = new DataStorage();

        dataStorage.setMaxHP(gameCFG.getPlayer().getMaxHP());
        dataStorage.setHP(gameCFG.getPlayer().getHP());
        dataStorage.setMaxMana(gameCFG.getPlayer().getMaxMana());
        dataStorage.setMana(gameCFG.getPlayer().getMana());
        dataStorage.setLvl(gameCFG.getPlayer().getLvl());
        dataStorage.setExp(gameCFG.getPlayer().getExp());
        dataStorage.setDamage(gameCFG.getPlayer().getDamage());
        dataStorage.setInventoryCapacity(gameCFG.getPlayer().getInventoryCapacity());
        if(gameCFG.getPlayer().getCurrentWeapon()!=null){
            dataStorage.setCurrentWeapon(gameCFG.getPlayer().getCurrentWeapon().getName());}
        if(gameCFG.getPlayer().getCurrentChest()!=null){
            dataStorage.setCurrentChest(gameCFG.getPlayer().getCurrentChest().getName());}
        if(gameCFG.getPlayer().getCurrentHelmet()!=null){
            dataStorage.setCurrentHelmet(gameCFG.getPlayer().getCurrentHelmet().getName());}
        if(gameCFG.getPlayer().getCurrentBoots()!=null){
            dataStorage.setCurrentBoots(gameCFG.getPlayer().getCurrentBoots().getName());}
        for (Object object : gameCFG.getPlayer().getInventory()) {
            dataStorage.getInventory().add(object.getName());
        }
        for (Object object : gameCFG.getObjects()){
            Integer [] tmp = new Integer[3];
            tmp[0] = object.getX();
            tmp[1] = object.getY();
            tmp[2] = object.getId();
            dataStorage.getObjects().put(object.getName(),tmp);
        }
        try {
            objectOutputStream.writeObject(dataStorage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void load()  {

        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(new File("save.dat")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DataStorage dataStorage = null;
        try {
            dataStorage = (DataStorage) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        gameCFG.getPlayer().setMaxHP(dataStorage.getMaxHP());
        gameCFG.getPlayer().setHP(dataStorage.getHP());
        gameCFG.getPlayer().setMaxMana(dataStorage.getMaxMana());
        gameCFG.getPlayer().setMana(dataStorage.getMana());
        gameCFG.getPlayer().setLvl(dataStorage.getLvl());
        gameCFG.getPlayer().setExp(dataStorage.getExp());
        gameCFG.getPlayer().setDamage(dataStorage.getDamage());
        gameCFG.getPlayer().setCurrentWeapon(gameCFG.getAssetsSetter().chooseObjName(dataStorage.getCurrentWeapon()));
        gameCFG.getPlayer().setCurrentChest(gameCFG.getAssetsSetter().chooseObjName(dataStorage.getCurrentChest()));
        gameCFG.getPlayer().setCurrentHelmet(gameCFG.getAssetsSetter().chooseObjName(dataStorage.getCurrentHelmet()));
        gameCFG.getPlayer().setCurrentBoots(gameCFG.getAssetsSetter().chooseObjName(dataStorage.getCurrentBoots()));
        gameCFG.getPlayer().getInventory().clear();
        for (String string : dataStorage.getInventory()) {
            if(string!=null) {
                gameCFG.getPlayer().getInventory().add(gameCFG.getAssetsSetter().chooseObjName(string));
            }
            else gameCFG.getPlayer().getInventory().add(new Object(gameCFG));
        }
        gameCFG.getPlayer().setInventoryCapacity(dataStorage.getInventoryCapacity());
        gameCFG.getObjects().clear();
        for (var entry : dataStorage.getObjects().entries()){
            for (int i = 0; i<entry.getValue().length;i++) {
                Object object = gameCFG.getAssetsSetter().chooseObjName(entry.getKey());
                object.setX(entry.getValue()[i]);
                object.setY(entry.getValue()[i+1]);
                object.setId(entry.getValue()[i+2]);
                i+=2;
                gameCFG.setObject(object);
//                System.out.println(object.getName() + "  X  " + object.getX() + "  Y " + object.getY());
            }
        }
    }
}
