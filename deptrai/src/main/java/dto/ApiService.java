package dto;

import java.io.IOException;
import java.util.List;
import my_enum.sort_filter.SortFilterOptions.Sort;
import my_enum.sort_filter.SortFilterOptions.Filter.*;

public interface ApiService {
    List<SearchResult> search(String keyword) throws IOException, InterruptedException;
    List<SearchResult> function(Sort sort, FilterType filterType, FilterYear filterYear);

    String trend(List<String> trendWord) throws IOException, InterruptedException;
}
