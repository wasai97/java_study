package com.countdownlatch;

public enum CountryEnum {
    one(1,"齐国"),two(2,"蜀国"),three(3,"赵国"),
    four(4,"猛国"), five(5,"美国"),six(6,"德国");
    private Integer reCode;
    private String name;

    CountryEnum(Integer reCode, String name) {
        this.reCode = reCode;
        this.name = name;
    }
    public static CountryEnum getCountryEnum(Integer i){
        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum value : values) {
            if (i == value.getReCode()){
                return value;
            }
        }
        return null;
    }

    public Integer getReCode() {
        return reCode;
    }

    public void setReCode(Integer reCode) {
        this.reCode = reCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
