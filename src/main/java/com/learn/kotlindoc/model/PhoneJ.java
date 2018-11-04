package com.learn.kotlindoc.model;

import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;
import java.util.HashSet;
import java.util.Set;

public class PhoneJ {

    private String id;

    private String brand;

    private String model;

    private boolean hasNfc;

    private boolean isFullScreen;

    // null means price is not yet undecided
    private Double price;

    private int[] size = new int[2];

    private String[] what = {};

    private Set<String> colors = new HashSet<>();

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
//        canNotInitTheArray({1, 2});
    }

    private void canNotInitTheArray(int[] array) {

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

    public Set<String> getColors() {
        return colors;
    }

    public void setColors(Set<String> colors) {
        if(colors != null){
            this.colors.clear();
            this.colors.addAll(colors);
        }
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void check() {

    }

    public int[] getSize() {
        return size;
    }

    public void setSize(int[] size) {
        this.size = size;
    }

    public String[] getWhat() {
        return what;
    }

    public void setWhat(String[] what) {
        this.what = what;
    }

    @Nonnull(when = When.ALWAYS)
    public String getBrandModel() {
        return String.format("%1$s,%2$s", this.brand, this.model);
    }
}
