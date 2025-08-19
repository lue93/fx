package br.com.lue93.fx.domain.rate.services;

import br.com.lue93.fx.domain.rate.vo.RateInput;
import br.com.lue93.fx.domain.rate.vo.RateOutPut;
import br.com.lue93.fx.application.services.rate.WiseRateServiceException;

public interface RateService {

    RateOutPut getRates(RateInput rate) throws WiseRateServiceException;

}
