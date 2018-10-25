package com.vae1970.api.entity.pojo.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vae
 */
@Getter
@Setter
public class ResultBean {

    private Integer status;

    private String message;

    private Object data;

}
