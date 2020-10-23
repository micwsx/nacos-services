package com.micwsx.project;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * jwt工具类，颁发token和验证token
 */
public class JwtUtil {

    public static final String SECRET = "qazwsx123444$#%#()*&& asdaswwi1235 ?;!@#kmmmpom in***xx**&";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_AUTH = "authorization";
    public static final String HEADER_USERID = "userid";
    //token超时时间
    public static final int TOKEN_EXPIRATION_MINUTE = 30;
    //token的redis超时时间
    public static final int TOKEN_REDIS_EXPIRATION_DAY = 7;


    public static String generateToken(String userId) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, TOKEN_EXPIRATION_MINUTE); //得到前一天
        Date date = calendar.getTime();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.format(date);
        //todo 优化token的生层规则
        HashMap<String, Object> map = new HashMap<>();
        map.put(HEADER_USERID, userId);
        String jwt = Jwts.builder()
                .setSubject(HEADER_USERID).setClaims(map)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return TOKEN_PREFIX + " " + jwt;
    }

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
        } catch (ExpiredJwtException e) {
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
            return new HashMap<String, String>();
        }
        return tokenResultMap;
    }

    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger("logger");
//        String accessToken = generateToken("10001");
//        System.out.println(accessToken);
//        Map<String, String> map = validateToken(accessToken);
//        System.out.println(map);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 30, TimeUnit.SECONDS, new SynchronousQueue<>());

        try {
            for (int i = 1; i < 55; i++) {
                int val = i;
                threadPoolExecutor.execute(() -> {
                    try {
                        logger.info("PoolSize:" + threadPoolExecutor.getPoolSize() + " [" + Thread.currentThread().getName() + "]打印" + val);
                        TimeUnit.MICROSECONDS.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        } finally {
            threadPoolExecutor.shutdown();
        }


    }
}
