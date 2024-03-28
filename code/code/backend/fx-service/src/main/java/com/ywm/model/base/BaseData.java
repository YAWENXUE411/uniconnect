/**
 * 
 */
package com.ywm.model.base;


import com.alibaba.fastjson2.JSONObject;

import java.io.Serializable;

/**
 *
 * @author
 *
 */
public abstract class BaseData implements Serializable {
	
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @return json string 返回json字符串
	 */
    public String toJSONString() {
         return JSONObject.toJSONString(this);
    }
}
