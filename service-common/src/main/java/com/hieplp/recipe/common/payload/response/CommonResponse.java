package com.hieplp.recipe.common.payload.response;

import com.hieplp.recipe.common.enums.response.ResponseCode;
import lombok.Data;

@Data
public class CommonResponse {
    protected String code;
    protected Object data;
    protected String message;

    public CommonResponse(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.data = new Object();
    }

    public CommonResponse(ResponseCode response, Object data) {
        this.code = response.getCode();
        this.message = response.getMessage();
        this.data = data;
    }
}