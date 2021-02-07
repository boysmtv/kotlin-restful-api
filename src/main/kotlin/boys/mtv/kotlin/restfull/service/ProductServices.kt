package boys.mtv.kotlin.restfull.service

import boys.mtv.kotlin.restfull.model.ListProductRequest
import boys.mtv.kotlin.restfull.model.ProductRequest
import boys.mtv.kotlin.restfull.model.ProductResponse
import boys.mtv.kotlin.restfull.model.UpdateProductRequest

interface ProductServices {

    fun create(productRequest: ProductRequest) : ProductResponse

    fun get(id: String) : ProductResponse

    fun update(id: String, updateProductRequest: UpdateProductRequest) : ProductResponse

    fun detele(id: String)

    fun list(listProductRequest: ListProductRequest) : List<ProductResponse>
}