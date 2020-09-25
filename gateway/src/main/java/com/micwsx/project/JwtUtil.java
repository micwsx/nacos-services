package com.micwsx.project;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * jwt工具类，颁发token和验证token
 */
public class JwtUtil {
    public static final String SECRET = "qazwsx123444$#%#()*&& asdaswwi1235 ?;!@#kmmmpom in***xx**&";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_USERID = "userid";
    //token超时时间
    public static final int TOKEN_EXPIRATION_MINUTE = 30;
    //token的redis超时时间
    public static final int TOKEN_REDIS_EXPIRATION_DAY = 7;

    public static Map<String, String> validateToken(String token) {
        HashMap<String, String> tokenMap = new HashMap<String, String>();
        if (StringUtils.isEmpty(token)) {
            return tokenMap;
        }
        try {
            Map<String, Object> tokenBody = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
            String userId = String.valueOf(tokenBody.get(HEADER_USERID));
            tokenMap.put(HEADER_USERID, userId);
        }catch (ExpiredJwtException e){
            e.printStackTrace();
        }
        return tokenMap;
    }

    /**
     * 移到jwtUtil中去
     *
     * @param token
     * @return
     */
    public static Map<String, String> validateTokenAndUser(String token, String userIdIn) {
        Map<String, String> tokenResultMap = new HashMap<>();
        if (StringUtils.isEmpty(token) || StringUtils.isEmpty(userIdIn)) {
            return tokenResultMap;
        }
        tokenResultMap = validateToken(token);
        if (StringUtils.isEmpty(token) || StringUtils.isEmpty(userIdIn)) {
            return tokenResultMap;
        }
        //判断传入的userid和token是否匹配
        String userIdOri = tokenResultMap.get(HEADER_USERID);
        if (!userIdIn.equals(userIdOri)) {
            return new HashMap<String,String>();
        }
        return tokenResultMap;
    }
}
