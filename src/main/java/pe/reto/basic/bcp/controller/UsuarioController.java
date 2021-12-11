package pe.reto.basic.bcp.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import pe.reto.basic.bcp.servicedto.request.Usuario;

@RestController
public class UsuarioController {

	@PostMapping("user")
	public Usuario login(@RequestParam("user") String usuario, @RequestParam("password") String pwd) {
		
		String token = getJWTToken(usuario);
		Usuario user = new Usuario();
		user.setUsuario(usuario);
		user.setToken(token);		
		return user;
		
	}

	private String getJWTToken(String usuario) {
		String secretKey = "llaveBCP";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("retoBcpJTW")
				.setSubject(usuario)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}
