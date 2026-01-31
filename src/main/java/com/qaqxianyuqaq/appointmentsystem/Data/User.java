package com.qaqxianyuqaq.appointmentsystem.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String userType;
    private String createTime;
    private String updateTime;
    private Boolean idDelete;

    public String toJsonString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t{\n");
        sb.append("\t\t\"id\":").append(this.id == null ? "" : this.id).append(",\n");
        sb.append("\t\t\"username\":\"").append(this.username == null ? "" : this.username).append("\",\n");
        sb.append("\t\t\"email\":\"").append(this.email == null ? "" : this.email).append("\",\n");
        sb.append("\t\t\"password\":\"").append(this.password == null ? "" : this.password).append("\",\n");
        sb.append("\t\t\"userType\":\"").append(this.userType == null ? "" : this.userType).append("\",\n");
        sb.append("\t\t\"createTime\":\"").append(this.createTime == null ? "" : this.createTime).append("\",\n");
        sb.append("\t\t\"updateTime\":\"").append(this.updateTime == null ? "" : this.updateTime).append("\",\n");
        sb.append("\t\t\"idDelete\":\"").append(this.idDelete == null ? "" : this.idDelete).append("\",\n");
        sb.append("\t}");
        return sb.toString();
    }
}
