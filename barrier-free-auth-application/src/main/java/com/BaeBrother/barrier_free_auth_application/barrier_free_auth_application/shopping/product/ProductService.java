package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/*
반드시 명심할 것
- 어떠한 경우라도 Service 객체로 반환하지 않기
 */
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    //createProduct -> 새로운 주문을 추가하는 method
    public Long createProduct(ProductDTO productDTO) {
        String name = productDTO.getName();
        String description = productDTO.getDescription();
        Long price = productDTO.getPrice();

        if (name != null && description != null && price >= 0) {
            // 객체 생성하고, setter method를 통해 매개변수 값대로 값을 넣는다.
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);

            Long productId = productRepository.save(product).getId();

            return productId;
        } else {
            return null;
        }
    }


    // updateProduct -> 기준 주문을 update하는 method
    public Long updateProduct(ProductDTO productDTO, Long productId) {
        String name = productDTO.getName();
        String description = productDTO.getDescription();
        Long price = productDTO.getPrice();
        // DB에서 주어진 Id를 가진 Product 객체를 가져온다.
        Product product = productRepository.findById(productId).orElse(null);

        if (product != null) {
            // 그 객체의 정보를 수정한다.
            if (name != null) {
                product.setName(name);
            }
            if (description != null) {
                product.setDescription(description);
            }
            if (price != null) {
                if (price >= 0) {
                    product.setPrice(price);
                }
            }

            return productRepository.save(product).getId();
        }

        return null;
    }


    // deleteProduct -> 기존 주문을 delete하는 method
    public boolean deleteProduct(Long productId) {
        if (productId != null) {
            productRepository.deleteById(productId);
            return true;
        } else
            return false;
    }


    //getProduct -> order의 id로 DB의 정보를 끄집어와주는 method
    public ProductDTO getProduct(Long productId) {
        try {
            Product product = productRepository.findById(productId).get();
            ProductDTO productDTO = new ProductDTO(product);
            return productDTO;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public Long getProductIdByName(String name) {
        return productRepository.findByName(name).getId();
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(product -> new ProductDTO(product)).toList();
    }
}

