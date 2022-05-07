package team.bahor.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JwtUtils {
    public static Integer expiry = 200 * 600_000;
    public static String secret = "ASDQW#@!$#@%$#DSFSDFRT%$#%34543terg45%^%$";

    public static Date getExpiry() {
        return new Date(System.currentTimeMillis() + expiry);
    }

    public static Date getExpiryForRefreshToken() {
        return new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(30));
    }

    public static Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secret.getBytes());
    }

    public static JWTVerifier getVerifier() {
        return JWT.require(getAlgorithm()).build();
    }
}
