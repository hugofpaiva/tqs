import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.is;




@ExtendWith(MockitoExtension.class)
class StocksPortfolioTest {

    @Mock
    IStockMarket market;

    @InjectMocks
    StocksPortfolio portfolio;

    @Test
    void getTotalValue() {

        when(market.getPrice("EBAY")).thenReturn(4.0);
        when(market.getPrice("MSFT")).thenReturn(1.5);

        portfolio.addStock(new Stock("EBAY", 2));
        portfolio.addStock(new Stock("MSFT", 4));
        double result = portfolio.getTotalValue();

        assertEquals(14.0, result);
        verify(market, times(2)).getPrice(anyString());

    }

    @Test
    void getTotalValueHam() {

        when(market.getPrice("EBAY")).thenReturn(4.0);
        when(market.getPrice("MSFT")).thenReturn(1.5);

        portfolio.addStock(new Stock("EBAY", 2));
        portfolio.addStock(new Stock("MSFT", 4));
        double result = portfolio.getTotalValue();

        assertThat(result, is(14.0));
        verify(market, times(2)).getPrice(anyString());

    }
}