package io.github.zhengchalei.common.jpa;

import io.quarkus.hibernate.orm.PersistenceUnitExtension;
import io.quarkus.hibernate.orm.runtime.tenant.TenantResolver;
import io.vertx.core.http.Cookie;
import io.vertx.ext.web.RoutingContext;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@PersistenceUnitExtension
@RequestScoped
public class CustomTenantResolver implements TenantResolver {

    @Inject
    RoutingContext context;

    @Override
    public String getDefaultTenantId() {
        return "public";
    }

    @Override
    public String resolveTenantId() {
        Cookie cookie = context.request().getCookie("Tenant");
        if (cookie == null || cookie.getValue() == null) {
            return getDefaultTenantId();
        }
        return cookie.getValue();
    }

}
