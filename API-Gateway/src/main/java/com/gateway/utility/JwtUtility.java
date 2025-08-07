package com.gateway.utility;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtUtility {
	
	private String jwtSecret = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
	
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String extractRole(String token) {
		Claims claims = Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody();
		return (String)claims.get("ROLE");
	}

}
