package br.com.soucriador.cynema.cynema.io

import br.com.patrocine.cynema.model.Pizza
import java.util.ArrayList
import java.util.HashMap

class PizzaContent {
    var ITEMS: MutableList<Pizza> = ArrayList<Pizza>()
    var ITEM_MAP: MutableMap<String, Pizza> = HashMap<String, Pizza>()
}