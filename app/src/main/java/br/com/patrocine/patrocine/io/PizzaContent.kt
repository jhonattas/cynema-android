package br.com.patrocine.patrocine.io

import br.com.patrocine.patrocine.model.Pizza
import java.util.ArrayList
import java.util.HashMap

class PizzaContent {
    var ITEMS: MutableList<Pizza> = ArrayList<Pizza>()
    var ITEM_MAP: MutableMap<String, Pizza> = HashMap<String, Pizza>()
}