//package com.house.common.utils.auth;
//
//import com.house.common.entity.auth.AuthUser;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @Description JWT生成令牌、验证令牌、获取令牌
// * @Author huangW
// * @Date 2020/4/22
// * @Version V1.0
// */
//@Component
//public class JwtTokenUtil {
//    //私钥
//    private static final String SECRET_KEY = "abunio-study";
//
//    // 过期时间 毫秒,设置默认1周的时间过期
//    private  static final long EXPIRATION_TIME = 3600000L * 24*7;
//
//    /**
//     * 生成令牌
//     *
//     * @param authUser 用户
//     * @return 令牌
//     */
//    public String generateToken(AuthUser authUser) {
//        Map<String, Object> claims = new HashMap<>(2);
//        claims.put(Claims.SUBJECT, authUser.getUsername());
//        claims.put(Claims.ISSUER, authUser.getUserNumber());
//        claims.put(Claims.ISSUED_AT, new Date());
//        return generateToken(claims);
//    }
//
//    /**
//     * 从令牌中获取用户名
//     *
//     * @param token 令牌
//     * @return 用户名
//     */
//    public String getUsernameFromToken(String token) {
//        String username = null;
//        try {
//            Claims claims = getClaimsFromToken(token);
//            System.out.println("claims = " + claims.toString());
//            username = claims.getSubject();
//        } catch (Exception e) {
//            System.out.println("e = " + e.getMessage());
//        }
//        return username;
//    }
//
//    /**
//     * 从令牌中获取用户名编号
//     * @param token 令牌
//     * @return 户名编号
//     */
//    public String getUserNumberFromToken(String token) {
//        String userNumber = null;
//        try {
//            Claims claims = getClaimsFromToken(token);
//            System.out.println("claims = " + claims.toString());
//            userNumber = claims.getIssuer();
//        } catch (Exception e) {
//            System.out.println("e = " + e.getMessage());
//        }
//        return userNumber;
//    }
//
//    /**
//     * 判断令牌是否过期
//     *
//     * @param token 令牌
//     * @return 是否过期
//     */
//    public Boolean isTokenExpired(String token) throws  Exception{
//        try {
//            Claims claims = getClaimsFromToken(token);
//            Date expiration = claims.getExpiration();
//            return expiration.before(new Date());
//        } catch (Exception e) {
//            new Throwable(e);
//        }
//        return true;
//    }
//
//    /**
//     * 刷新令牌
//     *
//     * @param token 原令牌
//     * @return 新令牌
//     */
//    public String refreshToken(String token) {
//        String refreshedToken;
//        try {
//            Claims claims = getClaimsFromToken(token);
//            claims.put(Claims.ISSUED_AT, new Date());
//            refreshedToken = generateToken(claims);
//        } catch (Exception e) {
//            refreshedToken = null;
//        }
//        return refreshedToken;
//    }
//
//    /**
//     * 验证令牌
//     *
//     * @param token       令牌
//     * @param userDetails 用户
//     * @return 是否有效
//     */
//    public Boolean validateToken(String token, UserDetails userDetails) throws Exception {
//        AuthUser user = (AuthUser) userDetails;
//        String username = getUsernameFromToken(token);
//        return (username.equals(user.getUsername()) && !isTokenExpired(token));
//    }
//
//    /**
//     * 从数据声明生成令牌
//     *
//     * @param claims 数据声明
//     * @return 令牌
//     */
//    private String generateToken(Map<String, Object> claims) {
//        Date expirationDate = new Date(System.currentTimeMillis()+ EXPIRATION_TIME);
//        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
//    }
//
//    /**
//     * 从令牌中获取数据声明
//     *
//     * @param token 令牌
//     * @return 数据声明
//     */
//    private Claims getClaimsFromToken(String token) throws Exception {
//        Claims claims = null;
//        try {
//            claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
//        } catch (Exception e) {
//            new Throwable(e);
//        }
//        return claims;
//    }
//}
