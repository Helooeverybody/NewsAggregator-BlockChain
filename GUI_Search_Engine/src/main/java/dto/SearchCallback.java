package dto;

import java.util.List;

public interface SearchCallback {
    void showLoadingAnimation();
    void hideLoadingAnimation();
    void displaySearchResults(List<SearchResult> searchResults);
}

