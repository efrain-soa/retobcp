package pe.reto.basic.bcp.service.tipocambio;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import pe.reto.basic.bcp.entity.Tipocambio;
import pe.reto.basic.bcp.servicedto.request.RequestTipoCambio;
import pe.reto.basic.bcp.servicedto.request.RequestUpdateTC;
import pe.reto.basic.bcp.servicedto.response.ResponseTipoCambio;

public interface TipoCambioService {

	  Single<List<ResponseTipoCambio>> getAllTipoCambio();
	  Single<List<Tipocambio>> obtenerTipoCambiosDisponibles();
	  Single<ResponseTipoCambio> ejecutarTipoCambio(RequestTipoCambio request);
	  public Completable updateTc(RequestUpdateTC updateTcRequest,int id);

}
