package com.Projectmanagement.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
/*
@Service
public class JwtService {
	
	private String secretKey="";
	public JwtService() {
		try {
			System.out.println(" JWTService constructor");
			KeyGenerator keyGenerate = KeyGenerator.getInstance("HmacSHA256");
			SecretKey key = keyGenerate.generateKey();
			secretKey = Base64.getEncoder().encodeToString(key.getEncoded());
			System.out.println("Secrete Key : "+secretKey);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public String generateToken(String id) {
		
		Map<String , Object> claims=new HashMap<String, Object>();
		return Jwts.builder().setClaims(claims).subject(id).issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+(1000L*60*60*7))).signWith(getKey()).compact();
		
	}
	
	private SecretKey getKey() {
		
		byte[] keyBytes=Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
		
	}
	public boolean validateToken(String token, UserDetails userDetails) {
		final String userName = extractUserName(token);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	public String extractUserName(String token) {
		// extract the username from jwt token
		return extractClaim(token, Claims::getSubject);
	}
	
	private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		try {
		token=token.trim();
		return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
		}catch(MalformedJwtException e) {
			 System.err.println("Malformed JWT: " + e.getMessage());
		        throw e;
		}
	}
	public Claims validateToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("JWT token is expired");
        } catch (Exception e) {
            throw new RuntimeException("Invalid JWT signature");
        }
    }
	 // Extract user ID from JWT token
	 public String extractEmpId(String token) {
	        return validateToken(token).getSubject();
	    }

	    // Extract role from JWT token
	    public String extractRole(String token) {
	        return (String) validateToken(token).get("role");
	    }
}
*/