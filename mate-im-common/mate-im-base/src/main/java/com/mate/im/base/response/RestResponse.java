package com.mate.im.base.response;

import com.alibaba.fastjson2.JSONObject;
import lombok.Getter;
import lombok.Setter;

/**
 * 远程调用出参
 *
 * @author yuanxmo
 */
@Getter
@Setter
public class RestResponse extends BaseResponse {

    private JSONObject data;

    private JSONObject error;

    @Override
    public Boolean getSuccess() {
        return this.error != null;
    }

    @Override
    public String getResponseMessage() {
        if (this.error != null) {
            return this.error.getString("message");
        }
        return null;
    }

    @Override
    public String getResponseCode() {
        if (this.error != null) {
            return this.error.getString("code");
        }
        return null;
    }
}
