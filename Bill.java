import java.util.ArrayList;

public class Bill {

    private ArrayList<BillItem> items;

    public Bill() {
        items = new ArrayList<>();
    }

    public void addItem(Product product, int quantity) {
        items.add(new BillItem(product, quantity));
    }

    public ArrayList<BillItem> getItems() {
        return items;
    }

    public double calculateSubtotal() {

        double subtotal = 0;

        for (BillItem item : items) {
            subtotal += item.getTotal();
        }

        return subtotal;
    }

    public double calculateDiscount() {

        double subtotal = calculateSubtotal();

        if (subtotal >= 1000) {
            return subtotal * 0.10;
        }

        else if (subtotal >= 500) {
            return subtotal * 0.05;
        }

        return 0;
    }

    public double calculateGST() {

        double subtotal = calculateSubtotal();

        double discount = calculateDiscount();

        double taxableAmount = subtotal - discount;

        return taxableAmount * 0.18;
    }

    public double calculateFinalAmount() {

        double subtotal = calculateSubtotal();

        double discount = calculateDiscount();

        double gst = calculateGST();

        return subtotal - discount + gst;
    }
}