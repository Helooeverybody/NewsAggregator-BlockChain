package gui;

import dto.SearchResult;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import my_enum.sort_filter.SortFilterOptions.Sort;
import my_enum.sort_filter.SortFilterOptions.Filter.*;
public class utilityFunction {
    public List<SearchResult> sortByDateAsc(List<SearchResult> searchResults) {
        System.out.println("The function sort by oldest has been called"); // fixme
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);
        return searchResults.stream()
                .sorted(Comparator.comparing((SearchResult result) -> LocalDate.parse(result.getDate(), formatter)))
                .collect(Collectors.toList());
    }

    public List<SearchResult> sortByDateDesc(List<SearchResult> searchResults) {
        System.out.println("The function sort by newest has been called"); // fixme
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);
        return searchResults.stream()
                .sorted(Comparator.comparing((SearchResult result) -> LocalDate.parse(result.getDate(), formatter)).reversed())
                .collect(Collectors.toList());
    }

    //TODO
    public List<SearchResult> filterByYear(List<SearchResult> searchResults, int year) {
        return searchResults.stream()
                .filter(result -> {
                    String dateString = result.getDate();
                    LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH));
                    return date.getYear() == year;
                })
                .collect(Collectors.toList());
    }
    //TODO
    public List<SearchResult> filterBySource(List<SearchResult> searchResults, String source) {
        return searchResults.stream()
                .filter(result -> result.getSource().equalsIgnoreCase(source))
                .collect(Collectors.toList());
    }
    //TODO

    public List<SearchResult> filterByType(List<SearchResult> searchResults, String type) {
        System.out.println("The filter by type has been called");
        return searchResults.stream()
                .filter(result -> result.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }
    public List<SearchResult> search(List<SearchResult> searchResults, Sort sort, FilterType filterType, FilterYear filterYear){
        switch (sort){
            case Sort.BY_NEWEST -> {
                return sortByDateDesc(searchResults);
            }case Sort.BY_OLDEST -> {
                return sortByDateAsc(searchResults);
            }case Sort.BY_RELEVANCE ->
            {
                return searchResults;
            }case null, default -> {
                switch (filterType){
                    case FilterType.BLOG -> {
                        return filterByType(searchResults, "Blog");
                    }case FilterType.NEWS -> {
                        return filterByType(searchResults, "News Articles");
                    }case null, default -> {
                        switch (filterYear){
                            case YEAR_2018 -> {
                                return filterByYear(searchResults, 2018);
                            }
                            case YEAR_2019 -> {
                                return filterByYear(searchResults, 2019);
                            }
                            case YEAR_2020 -> {
                                return filterByYear(searchResults, 2020);
                            }
                            case YEAR_2021 -> {
                                return filterByYear(searchResults, 2021);
                            }
                            case YEAR_2022 -> {
                                return filterByYear(searchResults, 2022);
                            }
                            case YEAR_2023 -> {
                                return filterByYear(searchResults, 2023);
                            }
                            case YEAR_2024 -> {
                                return filterByYear(searchResults, 2024);
                            }case null, default -> {
                                return searchResults;
                            }
                        }
                    }
                }
            }

        }
    }
}
