package com.zeuscontrol.service;

import com.zeuscontrol.model.Product;
import com.zeuscontrol.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    // Constructor para inyección de dependencias
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Obtener todos los productos
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Crear un nuevo producto
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Eliminar un producto por ID
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // Actualizar un producto existente
    public Product updateProduct(Long id, Product product) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setPrice(product.getPrice());
                    existingProduct.setStock(product.getStock());
                    return productRepository.save(existingProduct);
                }).orElseThrow(() -> new RuntimeException("Product not found"));
    }
}

