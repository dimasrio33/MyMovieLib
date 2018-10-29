package com.example.user.mymovielib.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MvResponse {

    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<Mv> results;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Mv> getResults() {
        return results;
    }

    public void setResults(List<Mv> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }


}
