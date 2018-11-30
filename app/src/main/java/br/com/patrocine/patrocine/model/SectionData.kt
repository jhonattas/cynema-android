package br.com.patrocine.patrocine.model

import java.util.ArrayList

class SectionData {

    var headerTitle: String? = null
    var allItemsInSection: ArrayList<Movie>? = null

    fun SectionData(headerTitle: String, allItemsInSection: ArrayList<Movie>) {
        this.headerTitle = headerTitle
        this.allItemsInSection = allItemsInSection
    }

}