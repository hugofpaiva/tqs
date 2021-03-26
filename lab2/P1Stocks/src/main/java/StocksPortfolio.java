import java.util.ArrayList;
import java.util.List;

public class StocksPortfolio {
    private IStockMarket stockMarket;
    private List<Stock> stocks;

    public StocksPortfolio(IStockMarket stockMarket){
        this.stockMarket = stockMarket;
        stocks = new ArrayList<>();
    }

    public void addStock(Stock stock){ stocks.add(stock); }

    public double getTotalValue(){
        double value = 0;
        for( Stock stock: stocks ){
            value += stock.getQuantity() * stockMarket.getPrice( stock.getName() );
        }
        return value;
    }
}
