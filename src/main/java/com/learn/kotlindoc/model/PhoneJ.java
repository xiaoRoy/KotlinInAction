package com.learn.kotlindoc.model;

import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;
import java.util.Set;

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

    public boolean hasNfc() {
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

    public void check() {

    }

    @Nonnull(when = When.ALWAYS)
    public String getBrandModel() {
        return String.format("%1$s,%2$s", this.brand, this.model);
    }
}
