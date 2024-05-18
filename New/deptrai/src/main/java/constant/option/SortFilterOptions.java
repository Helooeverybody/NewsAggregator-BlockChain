package constant.option;

public class SortFilterOptions {
    public enum Sort{
        BY_RELEVANCE,
        BY_NEWEST,
        BY_OLDEST
    }
    public enum Filter{
        ;
        public enum FilterType{
            BLOG,
            NEWS
        }
        public enum FilterYear{
            YEAR_2018,
            YEAR_2019,
            YEAR_2020,
            YEAR_2021,
            YEAR_2022,
            YEAR_2023,
            YEAR_2024
        }
    }
}
