package com.example.demo.service;

import com.example.demo.dto.AddChartDto;
import com.example.demo.dto.CartDto;
import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.model.Customer;
import com.example.demo.model.Product;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    public CartService(CustomerRepository customerRepository, CartRepository cartRepository, CustomerRepository customerRepository1, ProductRepository productRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository1;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public boolean createCart(Customer customer){
        try {
            Cart c = Cart.builder().customer(customer).build();
            c.setTotalPrice(BigDecimal.valueOf(0));
            cartRepository.save(c);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<CartDto> getCart(Customer customer){
        try {
            return CartDto.convert(cartRepository.findCartByCustomer(customer).getCartItems());

        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public boolean addCart(Long customerId, AddChartDto addChartDto) {
        try {

            Cart c = cartRepository.findCartByCustomer(customerRepository.findCustomerByCustomerId(customerId));
            if (c == null) {
                throw new Exception("Cart not found for customer id: " + customerId);
            }
            Product p = productRepository.findProductByProductId(addChartDto.productId());
            if (p == null) {
                throw new RuntimeException("Product not found for product id: " + addChartDto.productId());
            }

            CartItem cartItem = CartItem.builder()
                    .product(p)
                    .cart(c)
                    .quantity(addChartDto.quantity())
                    .build();

            cartItemRepository.save(cartItem);
            c.getCartItems().add(cartItem);
            c.setTotalPrice(c.calculateTotalPrice());

            cartRepository.save(c);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }



    public boolean removeProductsFromCart(Long customerId, AddChartDto addChartDto) {
        try {
            Cart c = cartRepository.findCartByCustomer(customerRepository.findCustomerByCustomerId(customerId));
            if (c == null) {
                throw new Exception("Cart not found for customer id: " + customerId);
            }

            List<CartItem> cartItems = c.getCartItems();

            for (CartItem cartItem : cartItems) {
                if (cartItem.getProduct().getProductId().equals(addChartDto.productId())) {
                    int newQuantity = cartItem.getQuantity() - addChartDto.quantity();
                    if (newQuantity > 0) {
                        cartItem.setQuantity(newQuantity);
                    } else {
                        cartItems.remove(cartItem);
                    }
                    break;
                }
            }
            c.setCartItems(cartItems);
            c.setTotalPrice(c.calculateTotalPrice());
            cartRepository.save(c);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }



    public boolean emptyCart(Customer customer) {
        try {
            Cart c = cartRepository.findCartByCustomer(customer);
            if (c == null) {
                throw new Exception("Cart not found for customer");
            }

            List<CartItem> cartItems = c.getCartItems();
            cartItems.forEach(cartItem -> cartItem.setCart(null));
            cartItems.clear();
            c.setCartItems(cartItems);

            c.setTotalPrice(BigDecimal.ZERO);

            cartRepository.save(c);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }





//    public Order getOrderForCode(Long productId, CartDto cartDto){
//        Cart cart = cartRepository.findCartByCustomer(customerRepository.findCustomerByCustomerId(cartDto.getCustomerId()));
//        List<Product> productList = cart.getProduct();
//
//        Product product = productList.stream()
//                .filter(p -> p.getProductId().equals(productId))
//                .findFirst()
//                .orElse(null);
//
//        return product;
//    }
//    gereksiz işlem
//    public boolean updateCart(UpdateCartDto updateCartDto){
//        Cart c = cartRepository.findCartByCartId(updateCartDto.getCartId());
//        System.out.println(c);
//        List<Product> productList= c.getProduct();
//        System.out.println(productList);
//        System.out.println("updateCart"+updateCartDto.getProducts());
//        productList=updateCartDto.getProducts();
//        System.out.println("3"+productList);
//        c.setProduct(productList);
//        cartRepository.save(c);
//        return true;
//    }

}
