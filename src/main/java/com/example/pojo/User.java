package com.example.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class User {
    private int userId;
    private String nameplateNumber;
    private String headshot;
    private String nickname;
    private String email;
    private String phone;
    private String sex;
    private String ipTerritory;
    private String birthday;
    private String area;
    private String school;
    private String tagWords;
    private String slogan;
    private String userPermissions;
    private String able;
    private String token;
    private String openId;
}
