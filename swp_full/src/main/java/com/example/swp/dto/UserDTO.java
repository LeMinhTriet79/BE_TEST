package com.example.swp.dto;


import com.example.swp.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class UserDTO {

    private String username;
    private String email;


    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}

