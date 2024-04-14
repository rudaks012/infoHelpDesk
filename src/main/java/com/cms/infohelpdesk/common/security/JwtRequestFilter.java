package com.cms.infohelpdesk.common.security;//package com.cms.commons.security;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.stream.Collectors;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//// JWT 요청 필터 클래스. 한 요청에 한 번만 수행되는 필터를 확장합니다.
//public class JwtRequestFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    // HTTP 요청이 들어올 때마다 이 함수가 호출됩니다.
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//        throws ServletException, IOException {
//
//        final String authorizationHeader = request.getHeader("Authorization");
//
//        String username = null;
//        String jwt = null;
//
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            jwt = authorizationHeader.substring(7);
//            username = jwtUtil.extractUsername(jwt);
//        }
//
//        // 사용자 이름이 존재하며, 현재 context에 인증 정보가 없는 경우
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            // userDetailsService로부터 사용자의 정보를 가져옵니다.
//            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//
//            // 토큰이 유효한지 체크합니다.
//            if (jwtUtil.validateToken(jwt, userDetails.getUsername())) {
//                // 토큰에서 역할(role)을 추출하여 권한으로 변환합니다.
//                List<SimpleGrantedAuthority> authorities = jwtUtil.extractRoles(jwt)
//                                                                  .stream()
//                                                                  .map(SimpleGrantedAuthority::new)
//                                                                  .collect(Collectors.toList());
//                // userDetails와 권한 정보를 이용하여 AuthenticationToken을 생성합니다.
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
//                // AuthenticationToken에 자세한 정보를 설정합니다.
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                // SecurityContextHolder의 인증 객체를 설정합니다.
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        }
//        // 필터 체인을 계속합니다.
//        chain.doFilter(request, response);
//    }
//}
//
