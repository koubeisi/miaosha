package com.koubeisi.dataobject;

public class UserDO {
    private Integer id;

    private String name;

    private Byte gender;

    private Integer age;

    private String telephone;

    private String registreMode;

    private String thirdPartyId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getRegistreMode() {
        return registreMode;
    }

    public void setRegistreMode(String registreMode) {
        this.registreMode = registreMode == null ? null : registreMode.trim();
    }

    public String getThirdPartyId() {
        return thirdPartyId;
    }

    public void setThirdPartyId(String thirdPartyId) {
        this.thirdPartyId = thirdPartyId == null ? null : thirdPartyId.trim();
    }

    @Override
    public String toString() {
        return "[Id:" + id +
                "] [Name:" + name +
                "] [Gender:" + gender +
                "] [Age:" + age +
                "] [Telephone:" + telephone +
                "] [RegisterMode:" + registreMode +
                "] [ThirdPartyId:" + thirdPartyId +
                "]";
    }
}