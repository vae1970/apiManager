package com.vae1970.api.util;

import com.vae1970.api.entity.enums.ErrorCode;
import com.vae1970.api.entity.pojo.dto.ResultBean;

/**
 * @author vae
 */
public class ResultUtil {

    public static ResultBean toResult (ErrorCode errorCode, Object o) {
        ResultBean resultBean = new ResultBean();
        resultBean.setStatus(errorCode.getStatus());
        resultBean.setData(o);
        return resultBean;
    }

}
