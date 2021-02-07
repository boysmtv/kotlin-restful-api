package boys.mtv.kotlin.restfull.repository

import boys.mtv.kotlin.restfull.entity.ApiKey
import org.springframework.data.jpa.repository.JpaRepository

interface ApiKeyRepository : JpaRepository<ApiKey, String> {

}