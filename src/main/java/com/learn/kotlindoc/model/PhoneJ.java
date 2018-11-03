package com.learn.kotlindoc.model;

public class PhoneJ {

    private String id;

    private String brand;

    private String model;

    private boolean hasNfc;

    private boolean isFullScreen;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isHasNfc() {
        return hasNfc;
    }

    public void setHasNfc(boolean hasNfc) {
        this.hasNfc = hasNfc;
    }

    public boolean isFullScreen() {
        return isFullScreen;
    }

    public void setFullScreen(boolean fullScreen) {
        isFullScreen = fullScreen;
    }

    public void setId(String id) {
        this.id = id;
    }
}
