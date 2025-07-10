package shop;

public record OrderItem(Product product, int quantity, Money reducedPrice) {
}
