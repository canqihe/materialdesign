package com.example.mwqi.myapplication;

import java.util.List;

/**
 * ============================================================
 * <p/>
 * 版      权 ： 黑马程序员教育集团 版权所有 (c) 2015
 * <p/>
 * 作      者  :  马伟奇
 * <p/>
 * 版      本 ： 1.0
 * <p/>
 * 创建日期 ： 2015/6/21  14:13
 * <p/>
 * 描      述 ：
 * <p/>
 * <p/>
 * 修订历史 ：
 * <p/>
 * ============================================================
 */
public class HM {


    /**
     * gender : man
     * name : 王五
     * addr : {"code":"300000","province":"fujian","city":"quanzhou"}
     * age : 15
     * height : 140cm
     * hobby : [{"code":"1","name":"billiards"},{"code":"2","name":"computerGame"}]
     */
    private String gender;
    private String name;
    private AddrEntity addr;
    private int age;
    private String height;
    private List<HobbyEntity> hobby;

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddr(AddrEntity addr) {
        this.addr = addr;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setHobby(List<HobbyEntity> hobby) {
        this.hobby = hobby;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public AddrEntity getAddr() {
        return addr;
    }

    public int getAge() {
        return age;
    }

    public String getHeight() {
        return height;
    }

    public List<HobbyEntity> getHobby() {
        return hobby;
    }

    public class AddrEntity {
        /**
         * code : 300000
         * province : fujian
         * city : quanzhou
         */
        private String code;
        private String province;
        private String city;

        public void setCode(String code) {
            this.code = code;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCode() {
            return code;
        }

        public String getProvince() {
            return province;
        }

        public String getCity() {
            return city;
        }
    }

    public class HobbyEntity {
        /**
         * code : 1
         * name : billiards
         */
        private String code;
        private String name;

        public void setCode(String code) {
            this.code = code;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }
}
