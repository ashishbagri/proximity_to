package challenge.proximity.user.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class AuthenticationService {

    static final long EXPIRATIONTIME = 864_000_00; //1 day in milliseconds
    static final String SIGNINGKEY = "SecretKey";
    static final String PREFIX = "Bearer";

    //Add token to Authorization header
    static public void addToken(HttpServletResponse res, String username, Collection<? extends GrantedAuthority> authorities){
        String JwtToken = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis()+ EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SIGNINGKEY)
                .compact();

        res.addHeader("Authorization", PREFIX + " "+ JwtToken);
        res.addHeader("Access-Control-Expose-Headers", "Authorization");
    }

    //Get Token from Request Header
    static public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token != null){
            String user = Jwts.parser()
                    .setSigningKey(SIGNINGKEY)
                    .parseClaimsJws(token.replace(PREFIX,""))
                    .getBody()
                    .getSubject();

            if(user != null) {
                boolean isInstuctor = user.equalsIgnoreCase("instructor");
                GrantedAuthority ins = new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return "instructor";
                    }
                };
                GrantedAuthority stu = new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return "instructor";
                    }
                };

                return new UsernamePasswordAuthenticationToken(user, null, isInstuctor ? Arrays.asList(ins):Arrays.asList(stu));
            }
        }

        return null;
    }

}
