package br.com.lue93.fx.application.services.rate;

import br.com.lue93.fx.domain.rate.entities.Rate;
import br.com.lue93.fx.domain.rate.entities.RateCache;
import br.com.lue93.fx.domain.rate.services.RateService;
import br.com.lue93.fx.domain.rate.vo.RateInput;
import br.com.lue93.fx.domain.rate.vo.RateOutPut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProxyRateService implements RateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WiseRateService.class);

    @Autowired
    private WiseRateService wiseRateService;

    private final RateCache rateCache = new RateCache();

    public ProxyRateService(WiseRateService wiseRateService) {
        this.wiseRateService = wiseRateService;
    }

    @Override
    public RateOutPut getRates(RateInput rateInput) throws WiseRateServiceException {
        String keyName = rateCache.getKeyName(rateInput.source(), rateInput.target());
        LOGGER.info("CACHE - Chave gerada {}", keyName);
        try {
            LOGGER.info("CACHE - Recuperando taxas do cache local");
            Rate rate = rateCache.getByKeyFromCache(keyName);
            LOGGER.info("CACHE - Taxa encontrada no cache local, retornando-a");
            if(rateCache.isRateOld(rate))
                throw new UnsupportedOperationException("Rate com prazo excedida");

            return new RateOutPut(rate.getSource(), rate.getTarget(), rate.getValue(), rate.getTime());
        } catch ( UnsupportedOperationException ex) {
            LOGGER.info("CACHE - Nao encontramos as taxas no cache local");
            LOGGER.info("CACHE - Vamos consultar o servico externo");
            RateOutPut out = wiseRateService.getRates(rateInput);

            LOGGER.info("CACHE - Atualizando-o com a taxa {}", keyName);
            rateCache.putRateOnCache(
                    out.source(),
                    out.target(),
                    out.value(),
                    out.time()
            );
            return out;
        }
    }
}
