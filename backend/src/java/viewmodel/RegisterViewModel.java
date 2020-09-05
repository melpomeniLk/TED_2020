/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

public class RegisterViewModel {
    String username;
    String password;
    String name;
    String lastName;
    String email;
    String phone;
    String photo;
    boolean isHost;
    
    public RegisterViewModel() {}

    public RegisterViewModel(String username, String password, String name, String last_name, String email, String phone, String photo, boolean isHost) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = last_name;
        this.email = email;
        this.phone = phone;
        this.photo = photo;
        this.isHost = isHost;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isIsHost() {
        return isHost;
    }

    public void setIsHost(boolean isHost) {
        this.isHost = isHost;
    }

    
}
