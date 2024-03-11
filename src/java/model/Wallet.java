/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Wallet {
    private int wallet_id;
    private int user_id;
    private double balance;

    public Wallet(int wallet_id) {
        this.wallet_id = wallet_id;
    }

    public Wallet(int wallet_id, int user_id, double balance) {
        this.wallet_id = wallet_id;
        this.user_id = user_id;
        this.balance = balance;
    }

    public Wallet() {
    }

    public int getWallet_id() {
        return wallet_id;
    }

    public void setWallet_id(int wallet_id) {
        this.wallet_id = wallet_id;
    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    
}
