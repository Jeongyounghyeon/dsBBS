package com.sju.dsBBS.domain;

public class UserDto {
    private String id;
    private String pwd;
    private String name;
    private String pos;
    private String email;

    public UserDto() {}
    public UserDto(String id, String pwd, String name, String pos, String email) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.pos = pos;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id='" + id + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", pos='" + pos + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


}
