package io.github.zhengchalei

import org.eclipse.microprofile.health.HealthCheck
import org.eclipse.microprofile.health.HealthCheckResponse
import org.eclipse.microprofile.health.Liveness

/**
 * 健康检查
 *
 * @author 郑查磊
 */
@Liveness
class MyLivenessCheck : HealthCheck {
    override fun call(): HealthCheckResponse {
        return HealthCheckResponse.up("alive")
    }
}
