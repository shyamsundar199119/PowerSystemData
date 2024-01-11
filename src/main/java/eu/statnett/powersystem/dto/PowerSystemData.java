package eu.statnett.powersystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PowerSystemData {

    @JsonProperty("total")
    private int total;

    @JsonProperty("sort")
    private String sort;

    @JsonProperty("limit")
    private int limit;

    @JsonProperty("dataset")
    private String dataset;

    @JsonProperty("records")
    private List<PowerRecord> records;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getDataset() {
        return dataset;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
    }

    public List<PowerRecord> getRecords() {
        return records;
    }

    public void setRecords(List<PowerRecord> records) {
        this.records = records;
    }
}

