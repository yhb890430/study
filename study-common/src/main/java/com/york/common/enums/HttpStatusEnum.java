package com.york.common.enums;

import java.io.Serializable;
import java.util.AbstractList;

/**
 * @description: Http状态码枚举类
 * @author: york
 * @date: 2019/7/15 15:59
 * @version: <1.0>
 */
public enum HttpStatusEnum implements Serializable {

    HTTP_OK,HTTP_ERROR,HTTP_UNAUTHORIZED;

    HttpStatusEnum() {
    }

}
