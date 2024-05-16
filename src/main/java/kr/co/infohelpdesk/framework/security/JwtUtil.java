package kr.co.infohelpdesk.framework.security;//package com.cms.commons.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import java.util.Date;
//import java.util.List;
//import java.util.function.Function;
//
//public class JwtUtil {
//
//    private String secretKey = "secret";
//
//    public String generateToken(String username, List<String> roles) {
//        Claims claims = Jwts.claims().setSubject(username); // set username
//        claims.put("roles", roles); // 사용자 권한 추가
//
//        return Jwts.builder()
//                   .setClaims(claims)
//                   .setIssuedAt(new Date(System.currentTimeMillis()))
//                   .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10시간 후 만료
//                   .signWith(SignatureAlgorithm.HS256, secretKey)
//                   .compact();
//    }
//
//    public Boolean validateToken(String token, String username) { // 토큰 유효성 검사
//        final String usernameFromToken = extractUsername(token);
//        return (username.equals(usernameFromToken) && !isTokenExpired(token));
//    }
//
//    public String extractUsername(String token) { // 토큰에서 username 추출
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public List<String> extractRoles(String token) { // 토큰에서 roles 추출
//        return extractClaim(token, claims -> claims.get("roles", List.class));
//    }
//
//    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) { // 토큰에서 정보 추출
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    private Claims extractAllClaims(String token) { // 토큰에서 모든 정보 추출
//        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
//    }
//
//    private Boolean isTokenExpired(String token) { // 토큰 만료 여부 확인
//        return extractAllClaims(token).getExpiration().before(new Date());
//    }
//
//}
