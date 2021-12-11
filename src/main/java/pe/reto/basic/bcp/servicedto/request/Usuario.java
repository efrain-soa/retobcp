package pe.reto.basic.bcp.servicedto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

	private String usuario;
	private String pwd;
	private String token;

	

}
