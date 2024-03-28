package com.ywm.enums;

/**
 * 角色类型枚举
 * @Date     2020/8/20 10:52
 * @param
 * @return
 */
public enum RoleIdEnum {

    //角色类型(1-管理员 2-普通用户)
    SUPER_ADMINISTRATOR(1, "管理员"),

    GENERAL_USER(2, "普通用户");

    private Integer type;

    private String name;

    RoleIdEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    /**
     * 根据code返回枚举值
     * @param type
     * @return
     */
    public static RoleIdEnum getEnumByType(int type){
        for(RoleIdEnum testEnums: RoleIdEnum.values()) {
            if(testEnums.getType() == type) {
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
