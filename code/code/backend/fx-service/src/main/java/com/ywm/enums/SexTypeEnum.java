package com.ywm.enums;

/**
 * 性别类型枚举
 *
 * @Author: ywm
 * @Date     2020/8/20 10:52
 */
public enum SexTypeEnum {

    //性别(1-男 2-女)
    MAN(1),

    WOMAN(2);

    private Integer type;


    SexTypeEnum(Integer type) {
        this.type = type;
    }

    /**
     * 根据code返回枚举值
     * @param type
     * @return
     */
    public static SexTypeEnum getEnumByType(int type){
        for(SexTypeEnum testEnums: SexTypeEnum.values()) {
            if(testEnums.getType() == type) {
                return testEnums;
            }
        }
        return null;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer code) {
        this.type = code;
    }


}
