package com.ywm.enums;


/**
 * 功能描述：店铺分类状态枚举
 *
 * @Author: ywm
 * @Date: 2022/4/10 18:53
 */
public enum ShopStatusEnum {

    //状态(0:未审核 1:审核中 2:审核通过 -1:已注销 -2:审核不通过)
    UNREVIEWED(0, "未审核"),

    UNDER_REVIEW(1, "审核中"),

    PASSED(2, "审核通过"),

    CANCELLED(-1, "已注销"),

    REVIEW_FAILED(-2, "审核不通过"),

    ;

    private Integer status;

    private String name;

    ShopStatusEnum(Integer status, String name) {
        this.status = status;
        this.name = name;
    }

    /**
     * 根据code返回枚举值
     * @param status
     * @return
     */
    public static ShopStatusEnum getEnumByStatus(Integer status){
        for(ShopStatusEnum testEnums: ShopStatusEnum.values()) {
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
