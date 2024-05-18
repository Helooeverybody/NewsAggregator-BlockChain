package api;

import java.io.IOException;
import java.util.List;


public interface ApiServiceInterface<T> {
    List<T> function (List<String> keyword) throws IOException, InterruptedException;
}