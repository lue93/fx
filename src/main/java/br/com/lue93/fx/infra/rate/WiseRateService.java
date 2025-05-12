package br.com.lue93.fx.infra.rate;

import br.com.lue93.fx.domain.rate.*;
import br.com.ungaratto93.fx.domain.rate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class WiseRateService implements RateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WiseRateService.class);
    
    @Autowired
    private FeignWiseClient feignWiseClient;

    @Override
    public RateOutPut getRates(RateInput rateInput) throws WiseRateServiceException {

        List<Rate> rates = feignWiseClient.getRates(
                rateInput.source().name(),
                rateInput.target().name(),
                1,
                "daily",
                "day");
        LOGGER.info("WISE - As taxas retornadas foram: {}", rates);

        if (rates.isEmpty()) {
            LOGGER.warn("WISE - Nao foi possivel retornar a taxa");
            throw new WiseRateServiceException(String.format("Ocorreu retorno de objeto invalido na consulta da taxa %s, na WISE", rateInput));
        }

        Rate rate = rates.get(0);
        LOGGER.info("WISE - Recuperando a seguinte taxa: {}", rate);
        RateOutPut out = new RateOutPut(
                rate.getSource(),
                rate.getTarget(),
                rate.getValue(),
                rate.getTime()
        );

        LOGGER.info("WISE - Retornando a seguinte taxa: {}", out);
        return out;
    }
}
