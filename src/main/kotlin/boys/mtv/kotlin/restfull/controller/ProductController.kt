package boys.mtv.kotlin.restfull.controller

import boys.mtv.kotlin.restfull.model.*
import boys.mtv.kotlin.restfull.service.ProductServices
import org.springframework.web.bind.annotation.*

@RestController
class ProductController(val services: ProductServices) {

    @PostMapping(
        value = ["/api/product"],
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    fun createProduct(@RequestBody body: ProductRequest) : WebResponse<ProductResponse> {
        return WebResponse(
            code = 200,
            status = "OK",
            services.create(body)
        )
    }

    @GetMapping(
        value = ["/api/product/{idProduct}"],
        produces = ["application/json"]
    )
    fun getProduct(@PathVariable("idProduct") id: String) : WebResponse<ProductResponse>{
        return WebResponse(
            code = 200,
            status = "OK",
            services.get(id)
        )
    }

    @PutMapping(
        value = ["/api/product/{idProduct}"],
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    fun updateProduct(
        @PathVariable("idProduct") id: String,
        @RequestBody productRequest: UpdateProductRequest) : WebResponse<ProductResponse> {
        return WebResponse(
            code = 200,
            status = "OK",
            services.update(id, productRequest)
        )
    }

    @DeleteMapping(
        value = ["/api/product/{idProduct}"],
        produces = ["application/json"]
    )
    fun deleteProduct(@PathVariable("idProduct") id: String) : WebResponse<String> {
        services.detele(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = id
        )
    }

    @GetMapping(
        value = ["/api/product"],
        produces = ["application/json"]
    )
    fun listProduct(
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "page", defaultValue = "0") page: Int
    ) : WebResponse<List<ProductResponse>>{
        val request = ListProductRequest(page, size)
        val response = services.list(request)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }
}