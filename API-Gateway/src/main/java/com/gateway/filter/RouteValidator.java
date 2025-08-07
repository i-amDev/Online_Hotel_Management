package com.gateway.filter;

import java.util.List;
import java.util.function.Predicate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {

	public static final List<String> openAPIEndpoints = List.of("/auth/addUser", "/auth/generateToken",
			"/auth/validateToken/**", "/eureka");

	public Predicate<ServerHttpRequest> isSecured = request -> openAPIEndpoints.stream()
			.noneMatch(uri -> request.getURI().getPath().contains(uri));

}
