package com.ywm.utils;

/**
 * 功能描述：处理分页相关
 *
 * @Author: ywm
 * @Date: 2023/10/23 23:34
 */
public class PageUtils {

    /**
     * 计算分页信息
     *
     * @param pageNo   页码
     * @param pageSize 每页条数
     * @return firstResult
     */
    public static int getOffset(int pageNo, int pageSize) {
        //分页的起始记录行号
        int firstResult = 0;
        //首页时
        if (pageNo == 1) {
            firstResult = 1;
        } else if (pageNo > 1) {
            firstResult = (pageNo - 1) * pageSize + 1;
        }
        return firstResult;
    }

    /**
     * 获取分页的总页数
     *
     * @param totalCount 总记录数
     * @param pageSize   每页条数
     * @return 返回总页数
     */
    public static Long getTotalPage(long totalCount, int pageSize) {
        return (totalCount / pageSize) + (totalCount % pageSize == 0 ? 0 : 1);
    }

    /**
     * 计算分页信息
     *
     * @param pageNo 页码
     * @param pageSize  每页条数
     * @return maxResult
     */
    public static int getMaxResult(int pageNo, int pageSize) {
        // 分页的起始记录行号
        int firstResult = 0;
        int maxResult = 0;
        if (pageNo <= 1) {
            firstResult = 1;
            maxResult = pageSize;
        } else {
            firstResult = (pageNo - 1) * pageSize + 1;
            maxResult = pageSize + firstResult - 1;
        }
        return maxResult;
    }

}
