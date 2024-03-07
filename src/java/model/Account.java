/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Xuanphuc
 */
public class Account {
    int id;
    String name;
    String avatar;
    String username;
    String email;
    String password;
    String phonenumber;
    String story;
    int role_id;

    public Account() {
    }
    
    public Account(int id, String username, String password, String name) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
    }
    
    public Account(int id, String name, String avatar, String username, String email, String password, int role_id) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role_id = role_id;
    }

    public Account(String email, String username, String password, int role_id) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.role_id = role_id;
    }

    public Account(String email, String username, String password, String name, int role_id) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
        this.role_id = role_id;
    }

    public Account(int id, String name, String avatar, String username, String email, String password, String phonenumber, String story, int role_id) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phonenumber = phonenumber;
        this.story = story;
        this.role_id = role_id;
    }
    
    public Account(int id, String name, String username, String email, int role_id) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.role_id = role_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }
 
}
