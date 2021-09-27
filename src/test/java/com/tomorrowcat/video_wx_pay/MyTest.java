package com.tomorrowcat.video_wx_pay;

import com.tomorrowcat.video_wx_pay.domain.User;
import com.tomorrowcat.video_wx_pay.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;

/**
 * @description:
 * @author: kim
 * @create: 2021-06-28 18:33
 * @version: 1.0.0
 */
public class MyTest {

    /**
     * @description: 生成jwt
     * @param:
     * @return: void
     */
    @Test
    public void testGenJwt(){
        User user = new User();
        user.setId(1);
        user.setHeadImg("www.xdclass.net");
        user.setName("kim");
        String token = JwtUtils.genJsonWebToken(user);
        System.out.println(token);
    }

    /**
     * @description: 校验jwt
     * @param: 
     * @return: void
     */
    @Test
    public void testCheck(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0b21vcnJvd2NhdCIsImlkIjo5OTksIm5hbWUiOiJ4ZCIsImltZyI6Ind3dy54ZGNsYXNzLm5ldCIsImlhdCI6MTYyNDg3NjYxNSwiZXhwIjoxNjI1NDgxNDE1fQ.YhMpsjiuQqoK2bFoRD-WhEiX9xMS7wHtmvd9nwW6UG0";
        Claims claims = JwtUtils.checkJWT(token);
        if(claims != null){
            String name = (String) claims.get("name");
            String img = (String)claims.get("img");
            int id =(Integer) claims.get("id");
            System.out.println(name);
            System.out.println(img);
            System.out.println(id);
        }else {
            System.out.println("非法token");
        }
    }
}