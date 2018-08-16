package br.com.patrocine.patrocine.io;

import br.com.patrocine.patrocine.model.Pizza;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PromotionContent {

    public static List<Pizza> ITEMS = new ArrayList<>();

    public static Map<String, Pizza> ITEM_MAP = new HashMap<>();

    static {
        // TODO itens
    }

    private static void addItem(Pizza item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }

}
