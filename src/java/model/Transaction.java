/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author User
 */
public class Transaction {

    private int ID;
    private String status;
    private int Wallet_id;
    private String Payment_Code;
    private String Time;
    private String Description;
    private String BankCode;

    public Transaction() {
    }

    public Transaction(int ID, String status, int Wallet_id, String Payment_Code, String Time, String Description, String BankCode) {
        this.ID = ID;
        this.status = status;
        this.Wallet_id = Wallet_id;
        this.Payment_Code = Payment_Code;
        this.Time = Time;
        this.Description = Description;
        this.BankCode = BankCode;
    }

    public Transaction(int ID, String status, int Wallet_id) {
        this.ID = ID;
        this.status = status;
        this.Wallet_id = Wallet_id;
    }

    public Transaction(String status, int Wallet_id, String Payment_Code, String Time, String Description, String BankCode) {
        //this.ID = ID;
        this.status = status;
        this.Wallet_id = Wallet_id;
        this.Payment_Code = Payment_Code;
        this.Time = Time;
        this.Description = Description;
        this.BankCode = BankCode;
    }

    public String getPayment_Code() {
        return Payment_Code;
    }

    public void setPayment_Code(String Payment_Code) {
        this.Payment_Code = Payment_Code;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getBankCode() {
        return BankCode;
    }

    public void setBankCode(String BankCode) {
        this.BankCode = BankCode;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getWallet_id() {
        return Wallet_id;
    }

    public void setWallet_id(int Wallet_id) {
        this.Wallet_id = Wallet_id;
    }

    @Override
    public String toString() {
        return "Transaction{" + "ID=" + ID + ", status=" + status + ", Wallet_id=" + Wallet_id + ", Payment_Code=" + Payment_Code + ", Time=" + Time + ", Description=" + Description + ", BankCode=" + BankCode + '}';
    }

}
