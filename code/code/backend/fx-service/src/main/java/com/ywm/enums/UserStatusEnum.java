package com.ywm.enums;

/**
 * 用户状态枚举
 *
 * @Author: ywm
 * @Date     2020/8/20 10:52
 */
public enum UserStatusEnum {

    //用户状态(1-注册未授权 2-已授权)
    UNAUTHORIZED(0, "新注册未授权"),

    AUTHORIZED(1, "已授权")

    ;

    private Integer status;

    private String name;

    UserStatusEnum(Integer status, String name) {
        this.status = status;
        this.name = name;
    }

    /**
     * 根据code返回枚举值
     * @param status
     * @return
     */
    public static UserStatusEnum getEnumByStatus(int status){
        for(UserStatusEnum testEnums: UserStatusEnum.values()) {
            if(testEnums.getStatus() == status) {
                return testEnums;
            }
        }
        return null;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
