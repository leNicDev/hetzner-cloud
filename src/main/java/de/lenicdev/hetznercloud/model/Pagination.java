package de.lenicdev.hetznercloud.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pagination {

    private Integer page;
    @JsonProperty("per_page")
    private Integer perPage;
    @JsonProperty("previous_page")
    private Integer previousPage;
    @JsonProperty("next_page")
    private Integer nextPage;
    @JsonProperty("last_page")
    private Integer lastPage;
    @JsonProperty("total_entries")
    private Integer totalEntries;


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Integer getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(Integer previousPage) {
        this.previousPage = previousPage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Integer getLastPage() {
        return lastPage;
    }

    public void setLastPage(Integer lastPage) {
        this.lastPage = lastPage;
    }

    public Integer getTotalEntries() {
        return totalEntries;
    }

    public void setTotalEntries(Integer totalEntries) {
        this.totalEntries = totalEntries;
    }

}
