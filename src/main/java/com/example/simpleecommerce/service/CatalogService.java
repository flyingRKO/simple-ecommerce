package com.example.simpleecommerce.service;

import com.example.simpleecommerce.dto.response.ProductResponse;
import com.example.simpleecommerce.entity.Product;
import com.example.simpleecommerce.entity.Tag;
import com.example.simpleecommerce.exception.ErrorCode;
import com.example.simpleecommerce.exception.SimpleEcommerceException;
import com.example.simpleecommerce.repository.ProductRepository;
import com.example.simpleecommerce.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogService {
    private final ProductRepository productRepository;
    private final TagRepository tagRepository;

    @Transactional
    public ProductResponse registerProduct(
            Long sellerId,
            String name,
            String description,
            Long price,
            Long stockCount,
            List<String> tags
    ){
        List<Tag> tagList = tags.stream()
                .map(tagName -> tagRepository.findByName(tagName)
                        .orElseGet(() -> tagRepository.save(Tag.of(tagName))))
                .collect(Collectors.toList());

        Product savedProduct = productRepository.save(Product.of(
                sellerId,
                name,
                description,
                price,
                stockCount,
                tagList
        ));

        tagList.forEach(tag -> tag.getProducts().add(savedProduct));
        return ProductResponse.from(savedProduct);
    }

    @Transactional
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new SimpleEcommerceException(ErrorCode.NOT_FOUND_PRODUCT));

        product.getTags().forEach(tag -> tag.getProducts().remove(product));

        productRepository.delete(product);
    }

    @Transactional
    public List<Product> getProductsBySellerId(Long sellerId) {
        return productRepository.findBySellerId(sellerId);
    }

    @Transactional
    public ProductResponse getProductById(Long productId) {
        return ProductResponse.from(productRepository.findById(productId)
                .orElseThrow(() -> new SimpleEcommerceException(ErrorCode.NOT_FOUND_PRODUCT)));
    }

    @Transactional
    public ProductResponse decreaseStockCount(Long productId, Long decreaseCount) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new SimpleEcommerceException(ErrorCode.NOT_FOUND_PRODUCT));

        Long newStockCount = product.getStockCount() - decreaseCount;
        if (newStockCount < 0) {
            throw new SimpleEcommerceException(ErrorCode.NOT_ENOUGH_STOCK);
        }
        product.setStockCount(newStockCount);
        productRepository.save(product);
        return ProductResponse.from(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductResponse> searchProducts(String productName, String tagName, Pageable pageable) {
        return productRepository.searchProducts(productName, tagName, pageable)
                .map(ProductResponse::from);

    }

}
