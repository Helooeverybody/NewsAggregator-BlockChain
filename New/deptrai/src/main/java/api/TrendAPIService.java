package api;

import java.io.IOException;
import java.util.List;

public class TrendAPIService implements ApiServiceInterface<String> {
    private Client client;
    public TrendAPIService(){
        client = new Client();
    }
    @Override
    public List<String> function(List<String> trendList) throws IOException, InterruptedException {
       System.out.println("The search API service is: " + trendList);
        return this.client.trend(trendList);
    }
}
