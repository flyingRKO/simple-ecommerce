package com.example.simpleecommerce.controller;

import com.example.simpleecommerce.dto.request.DecreaseStockCountRequest;
import com.example.simpleecommerce.dto.request.ProductRequest;
import com.example.simpleecommerce.dto.request.SearchProductRequest;
import com.example.simpleecommerce.dto.response.ProductResponse;
import com.example.simpleecommerce.dto.response.Response;
import com.example.simpleecommerce.entity.Product;
import com.example.simpleecommerce.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CatalogController {
    private final CatalogService catalogService;

    @PostMapping("/catalog/products")
    public Response<ProductResponse> registerProduct(@RequestBody ProductRequest request) {
        return Response.success(catalogService.registerProduct(
                request.sellerId(),
                request.name(),
                request.description(),
                request.price(),
                request.stockCount(),
                request.tags()
        ));
    }
    @GetMapping("/catalog/products/{productId}")
    public Response<ProductResponse> getProductById(@PathVariable Long productId) {
        return Response.success(catalogService.getProductById(productId));
    }

    @DeleteMapping("/catalog/products/{productId}")
    public Response<Void> deleteProduct(@PathVariable Long productId) {
        catalogService.deleteProduct(productId);
        return Response.success();
    }

    @GetMapping("/catalog/seller/{sellerId}/products")
    public Response<List<ProductResponse>> getProductsBySellerId(@PathVariable Long sellerId) {
        List<Product> products = catalogService.getProductsBySellerId(sellerId);
        List<ProductResponse> productResponses = products.stream()
                .map(ProductResponse::from)
                .toList();
        return Response.success(productResponses);
    }

    @PostMapping("/catalog/products/{productId}/decreaseStockCount")
    public Response<ProductResponse> decreaseStockCount(@PathVariable Long productId, @RequestBody DecreaseStockCountRequest request) {
        return Response.success(catalogService.decreaseStockCount(productId, request.decreaseCount()));
    }

    @GetMapping("/catalog/search")
    public Response<Page<ProductResponse>> searchProducts(@RequestBody SearchProductRequest request, Pageable pageable){
        return Response.success(catalogService.searchProducts(request.productName(), request.tagName(), pageable));
    }
}
