package pe.reto.basic.bcp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import pe.reto.basic.bcp.entity.Tipocambio;
import pe.reto.basic.bcp.service.tipocambio.TipoCambioService;
import pe.reto.basic.bcp.servicedto.request.RequestTipoCambio;
import pe.reto.basic.bcp.servicedto.request.RequestUpdateTC;
import pe.reto.basic.bcp.servicedto.response.ResponseTipoCambio;

@RestController
@RequestMapping(value = "/api/tipocambio")
public class TipoCambioRestController {

	 @Autowired
	    private TipoCambioService tipoCambioService;
	 
	
	   
	    @GetMapping(value ="/obtenerMonedasDisponibles",produces = MediaType.APPLICATION_JSON_VALUE)
	    public Single<ResponseEntity<List<Tipocambio>>> obtenerMonedasDisponibles() {
	        return tipoCambioService.obtenerTipoCambiosDisponibles()
	                .subscribeOn(Schedulers.io())
	                .map(responses -> ResponseEntity.ok(responses));
	    }
	    
	    @PostMapping(value ="/ejecutar",produces = MediaType.APPLICATION_JSON_VALUE)
	    public Single<ResponseEntity<ResponseTipoCambio>> ejecutarTipoCambio(@RequestBody RequestTipoCambio request) {
	        return tipoCambioService.ejecutarTipoCambio(request)
	                .subscribeOn(Schedulers.io())
	                .map(responses -> ResponseEntity.ok(responses));
	    }
	    

	    @PutMapping(
	            value = "/{tcId}",
	            consumes = MediaType.APPLICATION_JSON_VALUE,
	            produces = MediaType.APPLICATION_JSON_VALUE
	    )
	    public Single<ResponseEntity<BaseWebResponse>> updateTc(@PathVariable(value = "tcId") int tcId,
	                                                              @RequestBody RequestUpdateTC requestUpdateTC) {
	        return tipoCambioService.updateTc(requestUpdateTC,tcId )
	                .subscribeOn(Schedulers.io())
	                .toSingle(() -> ResponseEntity.ok(BaseWebResponse.successNoData()));
	    }
	    

}
