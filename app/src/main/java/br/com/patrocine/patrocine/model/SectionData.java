package br.com.patrocine.patrocine.model;

import java.util.ArrayList;

public class SectionData {

    private String headerTitle;
    private ArrayList<Movie> allItemsInSection;

    public SectionData() {
    }

    public SectionData(String headerTitle, ArrayList<Movie> allItemsInSection) {
        this.headerTitle = headerTitle;
        this.allItemsInSection = allItemsInSection;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public ArrayList<Movie> getAllItemsInSection() {
        return allItemsInSection;
    }

    public void setAllItemsInSection(ArrayList<Movie> allItemsInSection) {
        this.allItemsInSection = allItemsInSection;
    }

}
