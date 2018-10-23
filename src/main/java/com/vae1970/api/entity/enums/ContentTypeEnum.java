package com.vae1970.api.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author vae
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ContentTypeEnum {

    /**
     * application/form-data
     */
    FORM_DATA("application/form-data"),
    /**
     * application/x-www-form-urlencoded
     */
    X_WWW_FORM_URLENCODED("application/x-www-form-urlencoded"),
    /**
     * application/json
     */
    JSON("application/json"),
    ;

    private String value;

}
