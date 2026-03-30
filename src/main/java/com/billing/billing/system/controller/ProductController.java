package com.billing.billing.system.controller;


import com.billing.billing.system.model.Product;
import com.billing.billing.system.model.User;
import com.billing.billing.system.payload.dto.ProductDTO;
import com.billing.billing.system.payload.response.ApiResponse;
import com.billing.billing.system.service.ProductService;
import com.billing.billing.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private  final ProductService productService;

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.getUserFromJwtToken(jwt);
        ProductDTO product = productService.createProduct(productDTO, user);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<ProductDTO>> getProductByStoreId(@PathVariable Long storeId, @RequestHeader("Authorization") String jwt) throws Exception {
        return ResponseEntity.ok(productService.getProductsByStoreId(storeId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(
            @PathVariable Long id,
            @RequestBody ProductDTO productDTO,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(productService.updateProduct(id, productDTO, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.getUserFromJwtToken(jwt);
        productService.deleteProduct(id, user);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Product deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/store/{storeId}/search")
    public ResponseEntity<List<ProductDTO>> searchByKeyword(@PathVariable Long storeId, @RequestParam String keyword, @RequestHeader("Authorization") String jwt) throws Exception {
        return ResponseEntity.ok(productService.searchByKeyword(storeId, keyword));
    }
}
