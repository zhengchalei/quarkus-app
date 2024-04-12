package io.github.zhengchalei.config

import io.quarkus.hibernate.orm.PersistenceUnitExtension
import io.quarkus.hibernate.orm.runtime.tenant.TenantResolver
import io.vertx.ext.web.RoutingContext
import jakarta.enterprise.context.RequestScoped

@PersistenceUnitExtension
@RequestScoped
class CustomTenantResolver(
    private val context: RoutingContext
) : TenantResolver {

    override fun getDefaultTenantId(): String {
        return "public"
    }

    override fun resolveTenantId(): String {
        val cookie = context.request().getCookie("Tenant")
        return if (cookie == null || cookie.value == null) {
            defaultTenantId
        } else cookie.value
    }
}
