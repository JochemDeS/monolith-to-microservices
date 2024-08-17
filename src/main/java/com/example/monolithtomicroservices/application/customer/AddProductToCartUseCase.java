//package com.example.monolithtomicroservices.application.customer;
//
//import com.example.monolithtomicroservices.application.cart.GetCartByCustomerIdPort;
//import com.example.monolithtomicroservices.application.cart.item.SaveCartItemPort;
//import com.example.monolithtomicroservices.application.common.UseCase;
//import com.example.monolithtomicroservices.application.product.GetProductByIdPort;
//import com.example.monolithtomicroservices.application.product.GetProductsByIdInPort;
//import com.example.monolithtomicroservices.domain.Cart;
//import com.example.monolithtomicroservices.domain.CartItem;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AddProductToCartUseCase implements UseCase<AddProductToCartRequest, Cart> {
//    private final GetCustomerByIdPort getCustomerByIdPort;
//    private final GetProductsByIdInPort getProductsByIdInPort;
//    private final SaveCartItemPort saveCartItemPort;
//    private final GetCartByCustomerIdPort getCartByCustomerIdPort;
//
//    public AddProductToCartUseCase(GetCustomerByIdPort getCustomerByIdPort, GetProductsByIdInPort getProductsByIdInPort,
//                                   SaveCartItemPort saveCartItemPort,
//                                   GetCartByCustomerIdPort getCartByCustomerIdPort) {
//        this.getCustomerByIdPort = getCustomerByIdPort;
//        this.getProductsByIdInPort = getProductsByIdInPort;
//        this.saveCartItemPort = saveCartItemPort;
//        this.getCartByCustomerIdPort = getCartByCustomerIdPort;
//    }
//
//    @Override
//    public Cart handle(AddProductToCartRequest request) {
//        final var customer = getCustomerByIdPort.byId(request.customer())
//                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
//
//        final var productIds = request.products().keySet();
//        final var products = getProductsByIdInPort.byIdIn(productIds.stream().toList());
//        // TODO: check if all products are found
//
//        final var cartItem = CartItem.builder()
//                .product(request.cartItem().productId())
//                .quantity(request.cartItem().quantity())
//                .build();
//
//        saveCartItemPort.save(customer.value(), request.cartItem());
//        return null;
//    }
//
//    private Cart convertToCart() {
//        return cart;
//    }
//}
