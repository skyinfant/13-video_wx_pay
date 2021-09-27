package com.tomorrowcat.video_wx_pay.utils;

import com.tomorrowcat.video_wx_pay.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @description: jwt
 * @author: kim
 * @create: 2021-06-28 18:17
 * @version: 1.0.0
 */
public class JwtUtils {

    private static final String SUBJECT = "tomorrowcat";
    private static final long EXPIRE = 1000*60*60*24*7;  //过期时间，毫秒，一周
    //密钥
    private static final String APPSECERT = "kim666";

    /**
     * @description:  生成token
     * @param: user
     * @return: String
     */
    public static String genJsonWebToken(User user){
        if(user == null || user.getId() == null || user.getName() == null
                || user.getHeadImg()==null){
            return null;
        }
        String token = Jwts.builder().setSubject(SUBJECT)

                .claim("id", user.getId())
                .claim("name", user.getName())
                .claim("img", user.getHeadImg())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE))

                .signWith(SignatureAlgorithm.HS256,APPSECERT).compact();
        return token;
    }

    /**
     * @description: 校验token
     * @param: token
     * @return: Claims
     */
    public static Claims checkJWT(String token){
        try{
            Claims claims = Jwts.parser().setSigningKey(APPSECERT)
                    .parseClaimsJws(token).getBody();

            return claims;
        }catch (Exception e){
            return null;
        }
    }

}