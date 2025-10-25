package com.mate.im.web.util;

import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.SecureUtil;
import com.mate.im.cache.constant.CacheConstant;

import java.nio.charset.StandardCharsets;

public class TokenUtil {
    private static final String TOKEN_AES_KEY = "token_by_mate_im_0";

    public static final String TOKEN_PREFIX = "token:";

    public static String getTokenValueByKey(String tokenKey) {
        if (tokenKey == null) {
            return null;
        }
        String uuid = UUID.randomUUID().toString();
        String tokenValue = tokenKey + CacheConstant.CACHE_KEY_SEPARATOR + uuid;

        return SecureUtil.aes(TOKEN_AES_KEY.getBytes(StandardCharsets.UTF_8)).encryptBase64(tokenValue);
    }

    public static String getTokenKeyByValue(String tokenValue) {
        if (tokenValue == null) {
            return null;
        }
        String decryptTokenValue = SecureUtil.aes(TOKEN_AES_KEY.getBytes(StandardCharsets.UTF_8)).decryptStr(tokenValue);
        return decryptTokenValue.substring(0, decryptTokenValue.lastIndexOf(CacheConstant.CACHE_KEY_SEPARATOR));
    }
}
