package com.example.jwttutorial.jwt;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
//빈이 생성이 되고 의존성주입까지 받은후 오바라이딩한 afterPropertiesSet으로
// 주입받은 secret 값을 우리가 설정한 base64로 디코딩한다음 키변수에 할당하기 위함
public class TokenProvider implements InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    private static final String AUTHORITIES_KEY = "auth";

    private final String secret;
    private final long tokenValidityInMilliseconds;

    private Key key;


    public TokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds) {
        this.secret = secret;
        this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000;
    }

    @Override
    public void afterPropertiesSet() {
//        base64로 디코딩 (우리가 설정했던 HS512 알고리즘은 512비트이기에 64바이트 Base64로 디코딩)
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

//    authentication 파라미터를 받아서 권한과, yml 파일에서 설정한 토큰의 만료값을 설정해준다음 jwt토큰을 생성후 리턴해준다.
    public String createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

//    토큰의 담겨있는 정보를 이용해서 Authentication 객체를 리턴하는 메서드 생성

    public Authentication getAuthentication(String token) {
//        토큰으로 클레임을 만들고 이를 통해 유저 객체를 만들어서 최종적으로 Authentication 객체를 리턴
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);
//      여기서 우리가 폼인증 방식등에서 사용한 필터인 UsernamePsswordAuthenticationFilter의 토큰 처럼
//        토큰을 반환해준다.
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }


//    토큰의 유효성 검사를 할수있는 토큰큰
   public boolean validateToken(String token) {
        try {
//            토큰을 파라미터로 받아서 파싱을 하고
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
//            나오는 exception들을 catch한다.
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            logger.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            logger.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            logger.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            logger.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }
}
