package com.example.simpleecommerce.repository;

import com.example.simpleecommerce.entity.Product;
import com.example.simpleecommerce.entity.QProduct;
import com.example.simpleecommerce.entity.QTag;
import com.example.simpleecommerce.repository.support.QuerydslRepositorySupport;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl extends QuerydslRepositorySupport implements ProductCustomRepository {
    public ProductRepositoryImpl() {
        super(Product.class);
    }
    @Override
    public Page<Product> searchProducts(String productName, String tagName, Pageable pageable) {
        QProduct product = QProduct.product;
        QTag tag = QTag.tag;

        BooleanBuilder builder = new BooleanBuilder();

        if (productName != null && !productName.isEmpty()) {
            builder.and(product.name.containsIgnoreCase(productName));
        }
        if (tagName != null && !tagName.isEmpty()) {
            builder.and(tag.name.containsIgnoreCase(tagName));
        }

        return applyPagination(pageable, query -> query
                .selectFrom(product)
                .join(product.tags, tag)
                .where(builder)
        );
    }
}
