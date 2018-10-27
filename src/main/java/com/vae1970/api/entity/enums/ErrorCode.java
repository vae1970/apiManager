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
public enum ErrorCode {
    /**
     * NO_ERROR
     */
    NO_ERROR(200);

    private Integer status;

}
