package org.example.IO;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable {

    private int lvl;
    private int maxHP;
    private int HP;
    private int maxMana;
    private int mana;
    private int damage;
    private int exp;
    private String currentWeapon;
    private String currentHelmet;
    private String currentChest;
    private String currentBoots;
    private ArrayList<String> inventory = new ArrayList<>();
    private int inventoryCapacity;
    private Multimap<String,Integer[]> objects = ArrayListMultimap.create();

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public int getLvl() {
        return lvl;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getHP() {
        return HP;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public int getMana() {
        return mana;
    }

    public int getDamage() {
        return damage;
    }

    public int getExp() {
        return exp;
    }

    public ArrayList<String> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<String> inventory) {
        this.inventory = inventory;
    }

    public int getInventoryCapacity() {
        return inventoryCapacity;
    }

    public void setInventoryCapacity(int inventoryCapacity) {
        this.inventoryCapacity = inventoryCapacity;
    }

    public String getCurrentWeapon() {
        return currentWeapon;
    }

    public void setCurrentWeapon(String currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public String getCurrentHelmet() {
        return currentHelmet;
    }

    public void setCurrentHelmet(String currentHelmet) {
        this.currentHelmet = currentHelmet;
    }

    public String getCurrentChest() {
        return currentChest;
    }

    public void setCurrentChest(String currentChest) {
        this.currentChest = currentChest;
    }

    public String getCurrentBoots() {
        return currentBoots;
    }

    public void setCurrentBoots(String currentBoots) {
        this.currentBoots = currentBoots;
    }

    public Multimap<String, Integer[]> getObjects() {
        return objects;
    }

    public void setObjects(Multimap<String, Integer[]> objects) {
        this.objects = objects;
    }
}
