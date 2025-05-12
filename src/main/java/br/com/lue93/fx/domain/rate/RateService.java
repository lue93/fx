package br.com.lue93.fx.domain.rate;

public interface RateService {

    RateOutPut getRates(RateInput rate) throws WiseRateServiceException;

}
