package pe.reto.basic.bcp.servicedto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTipoCambio {

	
	private double monto;
	private String monedaOrigen;
	private String monedaDestino;
	private double tipoCambio;
	
}
