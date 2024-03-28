package com.ywm.enums;

/**
 * 列表类型枚举
 *
 * @Author: ywm
 * @Date     2020/8/20 10:52
 */
public enum ListTypeEnum {

    //类型(1-college 2-专业 3-学位 4-研究生机构)
    college(1, "college"),

    specialized(2, "specialized"),

    degree(3, "degree"),

    institution(4, "institution"),

    ;

    private Integer type;

    private String name;

    ListTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    /**
     * 根据type返回枚举值
     * @param type
     * @return
     */
    public static ListTypeEnum getEnumByType(Integer type){
        for(ListTypeEnum testEnums: ListTypeEnum.values()) {
            if(testEnums.getType().equals(type)) {
                return testEnums;
            }
        }
        return null;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
