package com.ywm.enums;


/**
 * 功能描述：店铺分类状态枚举
 *
 * @Author: ywm
 * @Date: 2022/4/10 18:53
 */
public enum ShopTypeStatusEnum {

    //状态(0:启用 -1:已删除)
    ENABLE(0, "启用"),

    DELETED(-1, "已删除")

    ;

    private Integer status;

    private String name;

    ShopTypeStatusEnum(Integer status, String name) {
        this.status = status;
        this.name = name;
    }

    /**
     * 根据code返回枚举值
     * @param status
     * @return
     */
    public static ShopTypeStatusEnum getEnumByStatus(Integer status){
        for(ShopTypeStatusEnum testEnums: ShopTypeStatusEnum.values()) {
            if(testEnums.getStatus() == status) {
                return testEnums;
            }
        }
        return null;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer code) {
        this.status = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
