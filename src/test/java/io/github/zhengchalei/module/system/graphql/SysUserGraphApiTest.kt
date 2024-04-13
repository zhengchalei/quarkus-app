package io.github.zhengchalei.module.system.graphql

import io.restassured.RestAssured
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.util.*

internal class SysUserGraphApiTest {
    @Test
    fun findSysUserPage() {
        val query = """
                {
                    "query":"{
                        findSysUserPage(page: { index: 1, size: 10 }, params: {}) {
                            data {
                                username
                            }
                            count
                         }
                     }",
                     "variables":{}
                }
        """.trimIndent()
        val response = RestAssured
            .given()
            .`when`()
            .contentType("application/json")
            .body(query)
            .post("/graphql")
            .then()
            .statusCode(200)
            .log().all()
        println(response)
        // 确保数据总数为大于1
        response.body("data.findSysUserPage.count", Matchers.greaterThan(0)) // 确保返回的用户列表长大于1
            .body("data.findSysUserPage.data", Matchers.hasSize<Any>(Matchers.greaterThan(0))) // 确保返回的用户列表中第一个用户名不为空
            .body("data.findSysUserPage.data[0].username", Matchers.notNullValue())
    }

    @Test
    fun findSysUserById() {
        // 根据id查询
        val query = """
                {
                    "query": "{
                        findSysUserById(id: 1) {
                            username
                        }
                     }",
                     "variables":{}
                }
        
        """.trimIndent()
        val response = RestAssured
            .given()
            .`when`()
            .contentType("application/json")
            .body(query)
            .post("/graphql")
            .then()
            .statusCode(200)
            .log().all()
        response.body("data.findSysUserById.username", Matchers.notNullValue())
    }

    @Test
    fun saveSysUser() {
        val username = UUID.randomUUID().toString()
        val email = UUID.randomUUID().toString() + "@gmail.com"
        val query = """
            {
                "query": "mutation {
                    saveSysUser(data: {
                        username: \"$username\",
                        email: \"$email\",
                        departmentId: 1,
                    }) {
                        id
                    }
                }",
                "variables": {}
            }
        """.trimIndent()
        val response = RestAssured
            .given()
            .`when`()
            .contentType("application/json")
            .body(query)
            .post("/graphql")
            .then()
            .statusCode(200)
            .log().all()
        response.body("data.saveSysUser.id", Matchers.greaterThan(0))
    }

    @Test
    fun updateSysUserById() {
    }

    @Test
    fun deleteSysUserById() {
    }

    @Test
    fun activeSysUserById() {
    }

    @Test
    fun disableSysUserById() {
    }

    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() {
            RestAssured.baseURI = "http://localhost:8080"
            RestAssured.port = 8080
            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()
        }
    }
}