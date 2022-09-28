package br.com.soucriador.cynema.cynema.io;

import br.com.patrocine.cynema.model.Snack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SnackContent {

    public static List<Snack> ITEMS = new ArrayList<>();

    public static Map<String, Snack> ITEM_MAP = new HashMap<>();

    static {
    }

    private static void addItem(Snack item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }

}
