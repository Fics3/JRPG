package org.example.IO;

import org.example.Model.Entity.Entity;
import org.example.Model.Main.GameCFG;
import org.example.Model.Object.ObjectModel;

import java.io.*;
import java.util.Objects;

public class SaveLoad {
    boolean load=false;

    GameCFG gameCFG;

    public SaveLoad(GameCFG gameCFG){this.gameCFG=gameCFG;}

    public void save() {
        ObjectOutputStream objectOutputStream = null;
        try {
            if (Objects.equals(gameCFG.getName(), "world")) {
                objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("src/main/resources/Saves/save.dat")));
            }
            else if(Objects.equals(gameCFG.getName(), "custom")){
                objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("src/main/resources/Saves/custom.dat")));

            }
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
        for (ObjectModel objectModel : gameCFG.getPlayer().getInventory()) {
            dataStorage.getInventory().add(objectModel.getName());
        }
        for (ObjectModel objectModel : gameCFG.getObjects()){
            Integer [] tmp = new Integer[3];
            tmp[0] = objectModel.getX();
            tmp[1] = objectModel.getY();
            tmp[2] = objectModel.getId();
            dataStorage.getObjects().put(objectModel.getName(),tmp);
        }
        for (Entity entity : gameCFG.getNpcs()){
            Integer [] tmp = new Integer[3];
            tmp[0] = entity.getX();
            tmp[1] = entity.getY();
            tmp[2] = entity.getId();
            dataStorage.getEntities().put(entity.getName(),tmp);
        }
        dataStorage.setMaxWorldCol(gameCFG.getMaxWorldCol());
        dataStorage.setMaxWorldRow(gameCFG.getMaxWorldRow());
        dataStorage.setMap(gameCFG.getMapDataNum());
        dataStorage.setDataMap(gameCFG.getDataMap());
//        dataStorage.setDataMap(new int[dataStorage.getMaxWorldCol()][dataStorage.getMaxWorldRow()]);
//        for (int i = 0; i < gameCFG.getMaxWorldCol(); i++) {
//            for (int j = 0; j < gameCFG.getMaxWorldRow(); j++) {
//                if (gameCFG.getDataMap()[i][j]<=1){
//                    dataStorage.getDataMap()[i][j]=gameCFG.getDataMap()[i][j];
//                }
//            }
//        }
        try {
            objectOutputStream.writeObject(dataStorage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void load(String name) {
        System.out.println(name);
        ObjectInputStream objectInputStream = null;
        File file = new File(name + ".dat");
        if (file.exists()) {
            try {
                objectInputStream = new ObjectInputStream(new FileInputStream(file));
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
                if (string != null) {
                    gameCFG.getPlayer().getInventory().add(gameCFG.getAssetsSetter().chooseObjName(string));
                } else gameCFG.getPlayer().getInventory().add(new ObjectModel(gameCFG));
            }
            gameCFG.getPlayer().setInventoryCapacity(dataStorage.getInventoryCapacity());
            gameCFG.getObjects().clear();
            for (var entry : dataStorage.getObjects().entries()) {
                for (int i = 0; i < entry.getValue().length; i++) {
                    System.out.println(entry.getKey());
                    ObjectModel objectModel = gameCFG.getAssetsSetter().chooseObjName(entry.getKey());
                    objectModel.setX(entry.getValue()[i]);
                    objectModel.setY(entry.getValue()[i + 1]);
                    objectModel.setId(entry.getValue()[i + 2]);
                    i += 2;
                    gameCFG.setObject(objectModel);
                }
            }
            gameCFG.getNpcs().clear();
            for (var entry : dataStorage.getEntities().entries()) {
                for (int i = 0; i < entry.getValue().length; i++) {
                    Entity entity = gameCFG.getAssetsSetter().chooseEntityName(entry.getKey());
                    entity.setX(entry.getValue()[i]);
                    entity.setY(entry.getValue()[i + 1]);
                    entity.setId(entry.getValue()[i + 2]);
                    i += 2;
                    gameCFG.setNpcs(entity);
                }
            }
            gameCFG.setMapDataNum(dataStorage.getMap());
            gameCFG.setDataMap(dataStorage.getDataMap());
            gameCFG.setMaxWorldRow(dataStorage.getMaxWorldRow());
            gameCFG.setMaxWorldCol(dataStorage.getMaxWorldCol());
            load = true;
        }
    }

    public boolean isLoad() {
        return load;
    }

    public void setLoad(boolean load) {
        this.load = load;
    }
}
