package pe.reto.basic.bcp.service.tipocambio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.reactivex.Completable;
import io.reactivex.Single;
import pe.reto.basic.bcp.entity.Tipocambio;
import pe.reto.basic.bcp.repository.TipoCambioRepository;
import pe.reto.basic.bcp.servicedto.request.RequestTipoCambio;
import pe.reto.basic.bcp.servicedto.request.RequestUpdateTC;
import pe.reto.basic.bcp.servicedto.response.ResponseTipoCambio;

@Service
public class TipoCambioServiceImpl implements TipoCambioService {

	@Autowired
	private TipoCambioRepository tipoCambioRepository;

	@Override
	public Single<List<ResponseTipoCambio>> getAllTipoCambio() {
		return findAllTipoCambio().map(this::responseListTipoCambio);
	}

	private Single<List<Tipocambio>> findAllTipoCambio() {
		return Single.create(singleSubscriber -> {
			List<Tipocambio> response = tipoCambioRepository.findAll();
			singleSubscriber.onSuccess(response);
		});
	}

	private List<ResponseTipoCambio> responseListTipoCambio(List<Tipocambio> tipoCambioResponseList) {
		return tipoCambioResponseList.stream().map(this::toTipoCambioResponse).collect(Collectors.toList());
	}

	private ResponseTipoCambio toTipoCambioResponse(Tipocambio tipoCambio) {
		ResponseTipoCambio responseTipoCambio = new ResponseTipoCambio();
		BeanUtils.copyProperties(tipoCambio, responseTipoCambio);
		return responseTipoCambio;
	}

	@Override
	public Single<List<Tipocambio>> obtenerTipoCambiosDisponibles() {
		return Single.create(singleSubscriber -> {
			List<Tipocambio> response = tipoCambioRepository.findAll();
			singleSubscriber.onSuccess(response);
		});
	}

	@Override
	public Single<ResponseTipoCambio> ejecutarTipoCambio(RequestTipoCambio request) {
		Optional<Tipocambio> tipoCambio = validarTipoCambio(tipoCambioRepository.findAll(), request);

		return Single.create(singleSubscriber -> {

			if (!tipoCambio.isPresent())
				singleSubscriber.onError(new EntityNotFoundException());
			else {
				ResponseTipoCambio tcResponse = toTipoCambioResponse(tipoCambio.get(), request);
				singleSubscriber.onSuccess(tcResponse);
			}
		});

	}

	private Optional<Tipocambio> validarTipoCambio(List<Tipocambio> listaTipoCambio, RequestTipoCambio request) {

		return listaTipoCambio.stream().filter(
				e -> e.getConvertCode().equalsIgnoreCase(request.getMonedaOrigen() + "/" + request.getMonedaDestino()))
				.findFirst();
	}

	private ResponseTipoCambio toTipoCambioResponse(final Tipocambio tc, final RequestTipoCambio request) {
		ResponseTipoCambio response = new ResponseTipoCambio();
		response.setMonedaDestino(request.getMonedaDestino());
		response.setMonedaOrigen(request.getMonedaOrigen());
		response.setMonto(request.getMonto() * tc.getValue());
		response.setTipoCambio(tc.getValue());

		return response;
	}
	
	 @Override
	    public Completable updateTc(RequestUpdateTC updateTcRequest, int id) {
	        return updateTcToRepository(updateTcRequest,id);
	    }

	    private Completable updateTcToRepository(RequestUpdateTC updateTcRequest,int id) {
	        return Completable.create(completableSubscriber -> {
	            Optional<Tipocambio> optionalTc = tipoCambioRepository.findById(id);
	            if (!optionalTc.isPresent())
	                completableSubscriber.onError(new EntityNotFoundException());
	            else {
	            	Tipocambio tc = optionalTc.get();
	            	tc.setValue(updateTcRequest.getNewTc());
	            	tipoCambioRepository.save(tc);
	                completableSubscriber.onComplete();
	            }
	        });
	    }

}
