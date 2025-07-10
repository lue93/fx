package br.com.lue93.fx.adapters;

import br.com.lue93.fx.domain.fiat.FiatInputData;
import br.com.lue93.fx.domain.fiat.FiatOutputData;
import br.com.lue93.fx.domain.rate.WiseRateServiceException;
import br.com.lue93.fx.application.usecase.fiat.exchange.FiatExcxhangeUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("fx/v1")
public class FiatExchangeController {

    @Autowired
    private FiatExcxhangeUseCase fiatExchangeUsecase;

    @PostMapping("/exchanges")
    @Operation(
            summary =  "Consultar o cambio de determinada moeda",
            description = "Retorna o valor obtido apos o calculo de cambio"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = FiatOutputData.class),
                                    mediaType = "application/json")
                    }),
            @ApiResponse(
                    responseCode = "503",
                    content = {
                            @Content(schema = @Schema(implementation = ResponseEntity.class),
                                    mediaType = "application/json")
                    })
    })
    public ResponseEntity<FiatOutputData> post(@RequestBody FiatInputData fiatInputData) throws WiseRateServiceException {
       return ResponseEntity.ok(fiatExchangeUsecase.exec(fiatInputData));
    }
}
