package br.com.lue93.fx.domain.rate.services;

import br.com.lue93.fx.domain.rate.entities.Rate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "wise", url = "https://wise.com/")
public interface FeignWiseClient {

    @GetMapping(
            value="/rates/history+live",
            produces = "application/json"
    )
    List<Rate> getRates(
            @RequestParam("source") String sourceValue,
            @RequestParam("target") String targetValue,
            @RequestParam("length") Integer lengthValue,
            @RequestParam("resolution") String resolutionValue,
            @RequestParam("unit") String unitValue
    );


}
