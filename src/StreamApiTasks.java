import java.time.LocalDate;
import java.util.*;

public class StreamApiTasks {

    enum OrderStatus {
        NEW, PAID, SHIPPED, DELIVERED, CANCELLED
    }

    record Product(String name, String category, double price) {}

    record OrderItem(Product product, int quantity) {
        double totalPrice() {
            return product.price() * quantity;
        }
    }

    record Order(
            String id,
            String customerName,
            LocalDate orderDate,
            List<OrderItem> items,
            OrderStatus status
    ) {
        double totalValue() {
            return items.stream()
                    .mapToDouble(OrderItem::totalPrice)
                    .sum();
        }
    }

    static List<Order> sampleOrders() {
        Product laptop = new Product("Laptop", "Electronics", 4200.0);
        Product phone = new Product("Phone", "Electronics", 2600.0);
        Product headphones = new Product("Headphones", "Electronics", 350.0);
        Product novel = new Product("Novel", "Books", 45.0);
        Product textbook = new Product("Textbook", "Books", 120.0);
        Product tshirt = new Product("T-Shirt", "Fashion", 80.0);
        Product jacket = new Product("Jacket", "Fashion", 300.0);
        Product coffee = new Product("Coffee", "Grocery", 35.0);
        Product tea = new Product("Tea", "Grocery", 25.0);

        return List.of(
                new Order("ORD-001", "Anna Kowalska", LocalDate.of(2024, 2, 3),
                        List.of(new OrderItem(laptop, 1), new OrderItem(headphones, 2)),
                        OrderStatus.DELIVERED),
                new Order("ORD-002", "Jan Nowak", LocalDate.of(2024, 2, 5),
                        List.of(new OrderItem(novel, 3), new OrderItem(textbook, 1)),
                        OrderStatus.DELIVERED),
                new Order("ORD-003", "Anna Kowalska", LocalDate.of(2024, 2, 10),
                        List.of(new OrderItem(phone, 1), new OrderItem(tshirt, 2)),
                        OrderStatus.SHIPPED),
                new Order("ORD-004", "Maria Wisniewska", LocalDate.of(2024, 3, 1),
                        List.of(new OrderItem(jacket, 1), new OrderItem(coffee, 3), new OrderItem(tea, 5)),
                        OrderStatus.PAID),
                new Order("ORD-005", "Jan Nowak", LocalDate.of(2024, 3, 12),
                        List.of(new OrderItem(laptop, 2)),
                        OrderStatus.NEW),
                new Order("ORD-006", "Piotr Zielinski", LocalDate.of(2024, 3, 20),
                        List.of(new OrderItem(phone, 1), new OrderItem(headphones, 1)),
                        OrderStatus.CANCELLED),
                new Order("ORD-007", "Maria Wisniewska", LocalDate.of(2024, 4, 2),
                        List.of(new OrderItem(novel, 1), new OrderItem(coffee, 2), new OrderItem(tshirt, 1)),
                        OrderStatus.DELIVERED),
                new Order("ORD-008", "Tomasz Lewandowski", LocalDate.of(2024, 4, 8),
                        List.of(new OrderItem(laptop, 1), new OrderItem(textbook, 2)),
                        OrderStatus.SHIPPED)
        );
    }

    static List<String> activeOrderIds(List<Order> orders) {
        // TODO: zadanie 1
        return orders.stream()
                .filter(order -> order.status != OrderStatus.CANCELLED)
                .map(order -> order.id)
                .toList();
    }

    static List<Order> ordersAbove(List<Order> orders, double minValue) {
        // TODO: zadanie 2
        return orders.stream()
                .filter(order -> order.totalValue() > minValue)
                .sorted(Comparator.comparing(Order::totalValue).reversed())
                .toList();
    }

    static List<String> uniqueCustomerNames(List<Order> orders) {
        // TODO: zadanie 3
        return List.of();
    }

    static List<String> soldProductNames(List<Order> orders) {
        // TODO: zadanie 4
        return List.of();
    }

    static double totalRevenue(List<Order> orders) {
        // TODO: zadanie 5
        return 0.0;
    }

    static OptionalDouble averageDeliveredOrderValue(List<Order> orders) {
        // TODO: zadanie 6
        return OptionalDouble.empty();
    }

    static Map<OrderStatus, Long> countByStatus(List<Order> orders) {
        // TODO: zadanie 7
        return Map.of();
    }

    static Map<String, Double> revenueByCategory(List<Order> orders) {
        // TODO: zadanie 8
        return Map.of();
    }

    static Map<String, Double> topCustomers(List<Order> orders, int limit) {
        // TODO: zadanie 9
        return Map.of();
    }

    static Map<Boolean, List<Order>> partitionActiveOrdersByValue(List<Order> orders, double threshold) {
        // TODO: zadanie 10
        return Map.of();
    }

    static Optional<Order> mostExpensiveDeliveredOrder(List<Order> orders) {
        // TODO: zadanie 11
        return Optional.empty();
    }

    static DoubleSummaryStatistics activeOrderStatistics(List<Order> orders) {
        // TODO: zadanie dodatkowe
        return new DoubleSummaryStatistics();
    }

    public static void main(String[] args) {
        List<Order> orders = sampleOrders();

        System.out.println(activeOrderIds(orders));
        System.out.println(ordersAbove(orders, 3000).stream().map(Order::id).toList());
        System.out.println(uniqueCustomerNames(orders));
        System.out.println(soldProductNames(orders));
        System.out.println(totalRevenue(orders));
        System.out.println(averageDeliveredOrderValue(orders).orElse(0.0));
        System.out.println(countByStatus(orders));
        System.out.println(revenueByCategory(orders));
        System.out.println(topCustomers(orders, 3));
        System.out.println(partitionActiveOrdersByValue(orders, 3000));
        System.out.println(mostExpensiveDeliveredOrder(orders).map(Order::id).orElse("brak"));
        System.out.println(activeOrderStatistics(orders));
    }
}