package com.nju.edu.erp.web.controller;


import com.nju.edu.erp.model.vo.CreateProductVO;
import com.nju.edu.erp.model.vo.ProductInfoVO;
import com.nju.edu.erp.service.ProductService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @CrossOrigin
    @PostMapping("/create")
    public Response createProduct(@RequestBody CreateProductVO createProductVO) {
        return Response.buildSuccess(productService.createProduct(createProductVO));
    }

    @CrossOrigin
    @PostMapping("/update")
    public Response updateProduct(@RequestBody ProductInfoVO productInfoVO) {
        return Response.buildSuccess(productService.updateProduct(productInfoVO));
    }

    @CrossOrigin
    @GetMapping("/queryAll")
    public Response findAllProduct() {
        return Response.buildSuccess(productService.queryAllProduct());
    }

    @CrossOrigin
    @GetMapping("/delete")
    public Response deleteProduct(@RequestParam String id) {
        productService.deleteById(id);
        return Response.buildSuccess();
    }

}
