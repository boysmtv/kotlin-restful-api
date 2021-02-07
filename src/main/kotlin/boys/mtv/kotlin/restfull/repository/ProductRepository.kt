package boys.mtv.kotlin.restfull.repository

import boys.mtv.kotlin.restfull.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, String> {

}