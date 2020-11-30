package com.ftninformatika.zavrsniispitaad.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("search")
    @Expose
    private List<Search> search = null;
    @SerializedName ("totalResult")
    @Expose
    private String totalResult;
    @SerializedName("response")
    @Expose
    private String response;

    public List<Search> getSearch() {
        return search;
    }

    public void setSearch(List<Search> search) {
        this.search = search;
    }

    public String getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(String totalResult) {
        this.totalResult = totalResult;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
