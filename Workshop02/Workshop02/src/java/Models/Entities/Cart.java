package Models.Entities;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<String, CartItem> items;

    public Cart() {
        items = new HashMap<>();
    }

    public Map<String, CartItem> getItems() {
        return items;
    }

    public void addItem(CartItem item) {
        if (items.containsKey(item.getItemId())) {
            CartItem existing = items.get(item.getItemId());
            existing.setQuantity(existing.getQuantity() + item.getQuantity());
        } else {
            items.put(item.getItemId(), item);
        }
    }

    public void removeItem(String id) {
        items.remove(id);
    }

    public double getTotal() {
        double total = 0;
        for (CartItem item : items.values()) {
            total += item.getSubTotal();
        }
        return total;
    }
}