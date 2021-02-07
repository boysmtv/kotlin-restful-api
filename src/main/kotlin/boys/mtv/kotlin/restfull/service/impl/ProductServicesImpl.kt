package boys.mtv.kotlin.restfull.service.impl

import boys.mtv.kotlin.restfull.entity.Product
import boys.mtv.kotlin.restfull.error.NotFoundException
import boys.mtv.kotlin.restfull.model.ListProductRequest
import boys.mtv.kotlin.restfull.model.ProductRequest
import boys.mtv.kotlin.restfull.model.ProductResponse
import boys.mtv.kotlin.restfull.model.UpdateProductRequest
import boys.mtv.kotlin.restfull.repository.ProductRepository
import boys.mtv.kotlin.restfull.service.ProductServices
import boys.mtv.kotlin.restfull.validation.ValidationUtil
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class ProductServicesImpl(
    val productRepository: ProductRepository,
    val validationUtil: ValidationUtil
    ) : ProductServices {

    override fun create(productRequest: ProductRequest): ProductResponse {

        validationUtil.validate(productRequest)

        val product = Product(
            id = productRequest.id!!,
            name = productRequest.name!!,
            price = productRequest.price!!,
            quantity = productRequest.quantity!!,
            created_at = Date(),
            updated_at = null
        )

        productRepository.save(product)

        return convertResponse(product)
    }

    override fun get(id: String): ProductResponse {
        return convertResponse(findProductByIdOrThrowNotFound(id))
    }

    override fun update(id: String, updateProductRequest: UpdateProductRequest): ProductResponse {
        validationUtil.validate(updateProductRequest)

        val product = findProductByIdOrThrowNotFound(id)
        product.apply {
            name = updateProductRequest.name!!
            price = updateProductRequest.price!!
            quantity = updateProductRequest.quantity!!
            updated_at = Date()
        }
        productRepository.save(product)
        return convertResponse(product)
    }

    override fun detele(id: String) {
        productRepository.delete(findProductByIdOrThrowNotFound(id))
    }

    override fun list(listProductRequest: ListProductRequest): List<ProductResponse> {
        val page = productRepository.findAll(PageRequest.of(listProductRequest.page, listProductRequest.size))
        val product: List<Product> = page.get().collect(Collectors.toList())
        return product.map { convertResponse(it) }
    }

    private fun findProductByIdOrThrowNotFound(id: String) : Product{
        val product = productRepository.findByIdOrNull(id)
        if (product == null) {
            throw NotFoundException()
        }else{
            return product
        }
    }

    private fun convertResponse(product: Product) : ProductResponse{
        return ProductResponse(
            id = product.id,
            name = product.name,
            price = product.price,
            quantity = product.quantity,
            created_at = product.created_at,
            updated_at = product.updated_at
        )
    }

}