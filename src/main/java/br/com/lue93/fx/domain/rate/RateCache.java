package br.com.lue93.fx.domain.rate;

import br.com.lue93.fx.domain.fiat.Symbol;
import br.com.lue93.fx.infra.fiat.FiatExchangeServiceWiseRates;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
@ToString
public class RateCache {

    private Map<String, Rate> rates = new HashMap<>();

    @Value("${cache.rate.limit.time.valid:900}")
    private long limitTimeForRateInCache;

    private static final Logger LOGGER = LoggerFactory.getLogger(FiatExchangeServiceWiseRates.class);

    public RateCache() {

    }

    public void putRateOnCache(Symbol source, Symbol target, double value, String time) {
        String keyName = generateKeyName(source, target);
        rates.put(
                keyName,
                Rate.builder()
                       .source(source)
                       .target(target)
                       .value(value)
                       .time(time)
                       .build()
       );
        LOGGER.info("CACHE - A {} foi inserida no cache com time de {}", keyName, time);
    }

    private String generateKeyName(Symbol source, Symbol target) {
        return source.name().concat("_").concat(target.name());
    }

    public Rate getByKeyFromCache(String keyName) throws UnsupportedOperationException {
        if(!rates.isEmpty()) {
            LOGGER.info("CACHE - Recuperando taxas do cache local");
            return rates.get(keyName);
        } else
            throw new UnsupportedOperationException(String.format(" CACHE - A {} nao foi encontrada no cache", keyName));

    }

    public void removeByKeyFromCache(String keyName) throws UnsupportedOperationException {
        LOGGER.debug("CACHE - O tamanho do cache é {}", rates.size());
        if(!rates.isEmpty()) {
            rates.remove(keyName);
            LOGGER.info("CACHE - A {} foi removida do cache", keyName);
        } else
            throw new UnsupportedOperationException(String.format(" CACHE - A {} nao foi encontrada no cache", keyName));
    }

    public boolean isEmpty() {
        return rates.isEmpty();
    }

    public boolean isRateOld(Rate rate) {
        long rateTime = Long.parseLong(rate.getTime());
        long timeOfRateInCache = System.currentTimeMillis() - rateTime;

        LOGGER.info("CACHE - O tempo desta rate no cache é {}", timeOfRateInCache);

        return timeOfRateInCache > limitTimeForRateInCache ? Boolean.TRUE : Boolean.FALSE;
    }

    public String getKeyName(Symbol source, Symbol target) {
        return generateKeyName(source, target);
    }

}
