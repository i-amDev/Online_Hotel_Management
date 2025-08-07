package com.gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import com.gateway.utility.JwtUtility;

@Component
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {

	@Autowired
	private RouteValidator routeValidator;

	@Autowired
	private JwtUtility jwtUtility;

	public static class Config {

	}

	public JwtAuthenticationFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {
			if (routeValidator.isSecured.test(exchange.getRequest())) {
				if (!exchange.getRequest().getHeaders().containsKey("Authorization")) {
					throw new RuntimeException("Missing authorization header");
				}
				String header = exchange.getRequest().getHeaders().get("Authorization").get(0);
				String token = null;
				if (header != null && header.startsWith("Bearer ")) {
					token = header.substring(7);
				}
				try {
					jwtUtility.validateToken(token);
					String role = jwtUtility.extractRole(token);
					String path = exchange.getRequest().getURI().getPath();
					if (!checkRoleAccess(role, path)) {
						throw new RuntimeException("Unauthorized access");
					}
				} catch (Exception e) {
					System.out.println("Invalid access...!");
					throw new RuntimeException("un-authorized access to application");
				}
			}
			return chain.filter(exchange);
		});
	}

	private boolean checkRoleAccess(String role, String path) {
		if (role.equals("OWNER")) {
			return true;
		} else if (role.equals("MANAGER")) {
			return (path.startsWith("/guest/") || path.startsWith("/reservation/") || path.startsWith("/room/")
					|| path.startsWith("/department/"));
		} else if (role.equals("RECEPTIONIST")) {
			return (path.startsWith("/reservation/") || path.startsWith("/guest/") || path.startsWith("/room/"));
		} else if (role.equals("USER")) {
			return (path.startsWith("/reservation/addreservation") || path.startsWith("/reservation/getByPhone/") ||
					path.startsWith("/reservation/delete/")
					|| path.startsWith("/reservation/updateName/") || path.startsWith("/reservation/updateAddress/")
					|| path.startsWith("/room/"));
		}
		return false;
	}

}
