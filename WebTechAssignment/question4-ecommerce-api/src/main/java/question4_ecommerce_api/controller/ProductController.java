package question4_ecommerce_api.controller;

import question4_ecommerce_api.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    // In-memory storage for products
    private List<Product> products = new ArrayList<>();
    private Long nextId = 1L;

    // Initialize with 10 sample products
    public ProductController() {
        // Electronics
        products.add(new Product(nextId++, "iPhone 15", "Latest Apple smartphone", 999.99, "Electronics", 50, "Apple"));
        products.add(new Product(nextId++, "Samsung Galaxy S24", "Android flagship phone", 899.99, "Electronics", 30, "Samsung"));
        products.add(new Product(nextId++, "Sony Headphones", "Noise-cancelling wireless headphones", 299.99, "Electronics", 100, "Sony"));
        
        // Clothing
        products.add(new Product(nextId++, "Nike Air Max", "Running shoes", 129.99, "Clothing", 200, "Nike"));
        products.add(new Product(nextId++, "Levi's Jeans", "Classic blue jeans", 79.99, "Clothing", 150, "Levi's"));
        products.add(new Product(nextId++, "Adidas T-Shirt", "Cotton sports t-shirt", 29.99, "Clothing", 0, "Adidas")); // Out of stock
        
        // Books
        products.add(new Product(nextId++, "Clean Code", "Software development book", 39.99, "Books", 75, "Prentice Hall"));
        products.add(new Product(nextId++, "The Alchemist", "Novel by Paulo Coelho", 14.99, "Books", 120, "HarperOne"));
        
        // Home & Kitchen
        products.add(new Product(nextId++, "Dyson Vacuum", "Cordless vacuum cleaner", 499.99, "Home & Kitchen", 25, "Dyson"));
        products.add(new Product(nextId++, "KitchenAid Mixer", "Stand mixer for baking", 329.99, "Home & Kitchen", 40, "KitchenAid"));
    }

    // GET /api/products - Get all products (with optional pagination)
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        
        // Simple pagination logic
        int start = (page - 1) * limit;
        int end = Math.min(start + limit, products.size());
        
        if (start >= products.size()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
        
        List<Product> paginatedProducts = products.subList(start, end);
        return new ResponseEntity<>(paginatedProducts, HttpStatus.OK);
    }

    // GET /api/products/{productId} - Get product details
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                return new ResponseEntity<>(product, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // GET /api/products/category/{category} - Get products by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                result.add(product);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // GET /api/products/brand/{brand} - Get products by brand
    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Product>> getProductsByBrand(@PathVariable String brand) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getBrand().equalsIgnoreCase(brand)) {
                result.add(product);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // GET /api/products/search?keyword={keyword} - Search by keyword in name or description
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                product.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(product);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // GET /api/products/price-range?min={min}&max={max} - Get products within price range
    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getProductsByPriceRange(
            @RequestParam Double min,
            @RequestParam Double max) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getPrice() >= min && product.getPrice() <= max) {
                result.add(product);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // GET /api/products/in-stock - Get products with stockQuantity > 0
    @GetMapping("/in-stock")
    public ResponseEntity<List<Product>> getInStockProducts() {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getStockQuantity() > 0) {
                result.add(product);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // POST /api/products - Add new product
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        product.setProductId(nextId++);
        products.add(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    // PUT /api/products/{productId} - Update product details
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getProductId().equals(productId)) {
                updatedProduct.setProductId(productId);
                products.set(i, updatedProduct);
                return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // PATCH /api/products/{productId}/stock?quantity={quantity} - Update stock quantity
    @PatchMapping("/{productId}/stock")
    public ResponseEntity<Product> updateStockQuantity(
            @PathVariable Long productId,
            @RequestParam int quantity) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                product.setStockQuantity(quantity);
                return new ResponseEntity<>(product, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE /api/products/{productId} - Delete product
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                products.remove(product);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}