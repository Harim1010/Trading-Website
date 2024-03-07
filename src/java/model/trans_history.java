/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author caoth
 */
public class trans_history {

    private int id;
    private Double money;
    private boolean type;
    private boolean work;
    private String note;
    private String create_by;
    private Timestamp create_at;
    private Timestamp update_at;

    public trans_history() {
    }

    public trans_history(int id, Double money, boolean type, boolean work, String note, String create_by, Timestamp create_at, Timestamp update_at) {
        this.id = id;
        this.money = money;
        this.type = type;
        this.work = work;
        this.note = note;
        this.create_by = create_by;
        this.create_at = create_at;
        this.update_at = update_at;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public boolean isWork() {
        return work;
    }

    public void setWork(boolean work) {
        this.work = work;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }

    public Timestamp getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Timestamp update_at) {
        this.update_at = update_at;
    }

    @Override
    public String toString() {
        return "trans_history{" + "id=" + id + ", money=" + money + ", type=" + type + ", work=" + work + ", note=" + note + ", create_by=" + create_by + ", create_at=" + create_at + ", update_at=" + update_at + '}';
    }

}
