package com.example.monolithtomicroservices.infrastructure.http.product;

import com.example.monolithtomicroservices.application.product.FakeGetAllProductsUseCase;
import com.example.monolithtomicroservices.application.product.FakeGetProductByIdUseCase;
import com.example.monolithtomicroservices.data.ProductDataFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@Import({
        FakeGetAllProductsUseCase.class,
        FakeGetProductByIdUseCase.class
})
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private FakeGetAllProductsUseCase fakeGetAllProductsUseCase;
    @Autowired
    private FakeGetProductByIdUseCase fakeGetProductByIdUseCase;

    @Test
    void shouldGetAllProducts() throws Exception {
        final var products = ProductDataFactory.createProducts();
        fakeGetAllProductsUseCase.setProducts(products);

        final var expected = products.getFirst();
        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//                .andExpect(content().json("""
//                        {
//                          "products": [
//                            {
//                              "id": %d,
//                              "title": "%s",
//                              "description": "%s",
//                              "price": %f,
//                              "discountPercentage": %f,
//                              "rating": %f,
//                              "brand": "%s",
//                              "category": "%s",
//                              "thumbnail": "%s"
//                            }
//                          ],
//                          "page": 0,
//                          "size": %d,
//                          "totalProducts": %d
//                        }
//                        """.formatted(
//                                expected.id().id(),
//                                expected.title(),
//                                expected.description(),
//                                expected.price(),
//                                expected.discountPercentage(),
//                                expected.rating(),
//                                expected.brand().name(),
//                                expected.category().name(),
//                                expected.thumbnail(),
//                                products.size(),
//                                products.size())
//                        ));
    }

    @Test
    void shouldGetProductById() throws Exception {
        final var products = ProductDataFactory.createProducts();
        fakeGetProductByIdUseCase.setProduct(products.getFirst());

        final var expected = products.getFirst();
        mockMvc.perform(get("/products/%d".formatted(expected.id().id())))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                        {
                          "id": %d,
                          "title": "%s",
                          "description": "%s",
                          "price": %f,
                          "discountPercentage": %f,
                          "rating": %f,
                          "stock": %d,
                          "brand": "%s",
                          "category": "%s",
                          "thumbnail": "%s",
                          "image": "%s"
                        }
                        """.formatted(
                                expected.id().id(),
                                expected.title(),
                                expected.description(),
                                expected.price(),
                                expected.discountPercentage(),
                                expected.rating(),
                                expected.stock(),
                                expected.brand().name(),
                                expected.category().name(),
                                expected.thumbnail(),
                                expected.image())
                        ));
    }

    @Test
    void shouldReturnNotFoundWhenProductNotFound() throws Exception {
        final var products = ProductDataFactory.createProducts();
        fakeGetProductByIdUseCase.setProduct(products.getFirst());

        mockMvc.perform(get("/products/%d".formatted(products.size() + 1)))
                .andExpect(status().isNotFound());
    }
}