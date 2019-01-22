package com.koubeisi.service.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Description
 * @Author koubeisi
 * @Date 2019/1/13 16:20
 * @Version 1.0
 **/
public class UserModel {

    private Integer id;

    @NotBlank(message = "用户名不能为空")
    private String name;

    @NotNull(message = "性别不能为空")
    private Byte gender;

    @NotNull(message = "年龄不能为空")
    @Min(value = 0, message = "年龄不能小于0")
    @Max(value = 150, message = "年龄不能大于150")
    private Integer age;

    @NotBlank(message = "手机号不能为空")
    private String telephone;

    private String registreMode;

    private String thirdPartyId;

    @NotBlank(message = "密码不能为空")
    private String encrptPassword;


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

    public String getEncrptPassword() {
        return encrptPassword;
    }

    public void setEncrptPassword(String encrptPassword) {
        this.encrptPassword = encrptPassword;
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
