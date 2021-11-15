package com.coffemachine.model;

import javax.validation.constraints.NotNull;

public class ItemFeedback {

    @NotNull
    Long itemId;
    @NotNull
    String stationUsername;
    @NotNull
    String sample;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getStationUsername() {
        return stationUsername;
    }

    public void setStationUsername(String stationUsername) {
        this.stationUsername = stationUsername;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }
}
