package com.hecanqi.entity;

/**
 * Created by user on 2015/10/27.
 */
public class Translate {
    private String translation;//翻译结果
    private String usPhonetic;//美式发音
    private String ukPhonetic;//英式发音
    private String explainsN;//说明
    private String explainsA;//说明
    private String explainsD;//说明
    private String value;//相关语句
    private String key;//对应

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getUsPhonetic() {
        return usPhonetic;
    }

    public void setUsPhonetic(String usPhonetic) {
        this.usPhonetic = usPhonetic;
    }

    public String getUkPhonetic() {
        return ukPhonetic;
    }

    public String getExplainsA() {
        return explainsA;
    }

    public void setExplainsA(String explainsA) {
        this.explainsA = explainsA;
    }

    public String getExplainsD() {
        return explainsD;
    }

    public void setExplainsD(String explainsD) {
        this.explainsD = explainsD;
    }

    public void setUkPhonetic(String ukPhonetic) {

        this.ukPhonetic = ukPhonetic;
    }

    public String getExplainsN() {
        return explainsN;
    }

    public void setExplainsN(String explainsN) {
        this.explainsN = explainsN;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


}
