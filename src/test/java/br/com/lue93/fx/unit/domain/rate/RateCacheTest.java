package br.com.lue93.fx.unit.domain.rate;

import br.com.lue93.fx.domain.fiat.Symbol;
import br.com.lue93.fx.domain.rate.Rate;
import br.com.lue93.fx.domain.rate.RateCache;
import br.com.lue93.fx.unit.infra.rate.mock.RateMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
public class RateCacheTest {

    private RateCache rateCache;

    @Before
    public void setUp() {
        rateCache = new RateCache();
    }

    @After
    public void tearDown () {
        rateCache = null;
    }

    @Test
    public void deveRetornarUmObjRateBuscandoPelaKeyNoCache() {

        var rate = new Rate(
                Symbol.BRL,
                Symbol.USD,
                4.916,
                "1701993600000");

        rateCache.putRateOnCache(
                rate.getSource(),
                rate.getTarget(),
                rate.getValue(),
                rate.getTime()
        );



        Assertions.assertEquals(
                rate.getSource(),
                rateCache.getByKeyFromCache("BRL_USD").getSource()
        );
        Assertions.assertEquals(
                rate.getTarget(),
                rateCache.getByKeyFromCache("BRL_USD").getTarget()
        );
        Assertions.assertEquals(
                rate.getValue(),
                rateCache.getByKeyFromCache("BRL_USD").getValue()
        );
        Assertions.assertEquals(
                rate.getTime(),
                rateCache.getByKeyFromCache("BRL_USD").getTime()
        );
        Assertions.assertEquals(
                rate.getClass(),
                rateCache.getByKeyFromCache("BRL_USD").getClass()
        );

    }

    @Test
    public void deveRetornaUmObjRateBuscandoPelaKeyComDoisObjNoCache() {
        var rateBrl = new Rate(
                Symbol.BRL,
                Symbol.USD,
                4.916,
                "1701993600000");
        var rateUsd = new Rate(
                Symbol.USD,
                Symbol.BRL,
                0.501,
                "1701993600000");

        rateCache.putRateOnCache(
                rateBrl.getSource(),
                rateBrl.getTarget(),
                rateBrl.getValue(),
                rateBrl.getTime());
        rateCache.putRateOnCache(
                rateUsd.getSource(),
                rateUsd.getTarget(),
                rateUsd.getValue(),
                rateUsd.getTime());

        String key = "USD_BRL";
        Rate rate = rateCache.getByKeyFromCache(key);

        Assertions.assertEquals(
                rate.getSource(),
                rateCache.getByKeyFromCache(key).getSource()
        );
        Assertions.assertEquals(
                rate.getTarget(),
                rateCache.getByKeyFromCache(key).getTarget()
        );
        Assertions.assertEquals(
                rate.getValue(),
                rateCache.getByKeyFromCache(key).getValue()
        );
        Assertions.assertEquals(
                rate.getTime(),
                rateCache.getByKeyFromCache(key).getTime()
        );
        Assertions.assertEquals(
                rate.getClass(),
                rateCache.getByKeyFromCache(key).getClass()
        );
    }

    @Test
    public void deveRemoverTodosObjetosNoCache() {
        var rateBrl = new Rate(
                Symbol.BRL,
                Symbol.USD,
                4.916,
                "1701993600000");
        var rateUsd = new Rate(
                Symbol.USD,
                Symbol.BRL,
                0.501,
                "1701993600000");

        rateCache.putRateOnCache(
                rateBrl.getSource(),
                rateBrl.getTarget(),
                rateBrl.getValue(),
                rateBrl.getTime());
        rateCache.putRateOnCache(
                rateUsd.getSource(),
                rateUsd.getTarget(),
                rateUsd.getValue(),
                rateUsd.getTime());

        String keyUSD = "USD_BRL";
        String keyBRL = "BRL_USD";
        rateCache.removeByKeyFromCache(keyUSD);
        rateCache.removeByKeyFromCache(keyBRL);

        Assertions.assertTrue(rateCache.isEmpty());

    }

    @Test
    public void deveRetornarQueRateEAntiga() {

        rateCache.setLimitTimeForRateInCache(900);

        var rateBrl = new Rate(
                Symbol.BRL,
                Symbol.USD,
                4.916,
                String.valueOf(System.currentTimeMillis() - 999));

        rateCache.putRateOnCache(
                rateBrl.getSource(),
                rateBrl.getTarget(),
                rateBrl.getValue(),
                rateBrl.getTime());

        Assertions.assertEquals(900, rateCache.getLimitTimeForRateInCache());
        Assertions.assertTrue(rateCache.isRateOld(rateBrl));

    }

    @Test
    public void deveRetornarQueRateNaoEAntiga() {

        rateCache.setLimitTimeForRateInCache(900);

        var rateBrl = new Rate(
                Symbol.BRL,
                Symbol.USD,
                4.916,
                String.valueOf(System.currentTimeMillis() - 899));

        rateCache.putRateOnCache(
                rateBrl.getSource(),
                rateBrl.getTarget(),
                rateBrl.getValue(),
                rateBrl.getTime());

        Assertions.assertFalse(rateCache.isRateOld(rateBrl));

    }

    @Test
    public void deveInserirRateNoCache() {
        Rate instance = RateMock.getInstance();
        rateCache.putRateOnCache(
                instance.getSource(),
                instance.getTarget(),
                instance.getValue(),
                instance.getTime()
        );

        Assertions.assertFalse(rateCache.isEmpty());
    }

    @Test
    public void deveRetornarChaveParaConsulta() {
        String keyName = rateCache.getKeyName(Symbol.USD, Symbol.BRL);
        Assertions.assertEquals("USD_BRL", keyName);
    }

    @Test
    public void deveRemoverRateDoCache() {

        Rate instance = RateMock.getInstance();
        rateCache.putRateOnCache(
                instance.getSource(),
                instance.getTarget(),
                instance.getValue(),
                instance.getTime()
        );
        Assertions.assertFalse(rateCache.isEmpty());

        rateCache.removeByKeyFromCache(
                rateCache.getKeyName(Symbol.USD, Symbol.BRL)
        );

        Assertions.assertTrue(rateCache.isEmpty());

    }


    @Test
    public void deveLancarExcQuandoRemoveRateDoCacheJaRemovido() {

        Rate instance = RateMock.getInstance();
        rateCache.putRateOnCache(
                instance.getSource(),
                instance.getTarget(),
                instance.getValue(),
                instance.getTime()
        );
        Assertions.assertFalse(rateCache.isEmpty());

        rateCache.removeByKeyFromCache(
                rateCache.getKeyName(Symbol.USD, Symbol.BRL)
        );

        try {
            rateCache.removeByKeyFromCache(
                    rateCache.getKeyName(Symbol.USD, Symbol.BRL)
            );
        } catch (UnsupportedOperationException ex) {
            Assertions.assertEquals(UnsupportedOperationException.class, ex.getClass());
        }

    }

    @Test
    public void deveRetornarTempoLimiteNoCache() {
        rateCache.setLimitTimeForRateInCache(900);
        Assertions.assertEquals(
                900, rateCache.getLimitTimeForRateInCache()
        );
    }

    @Test
    public void deveLancarExcQuandoRemoveRateDoCacheVazio() {
        try {
            rateCache.removeByKeyFromCache(
                    rateCache.getKeyName(Symbol.USD, Symbol.BRL)
            );
        } catch (UnsupportedOperationException ex) {
            Assertions.assertEquals(UnsupportedOperationException.class, ex.getClass());
        }

    }



}
