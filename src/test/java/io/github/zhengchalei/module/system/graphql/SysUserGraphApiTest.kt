package io.github.zhengchalei.module.system.graphql

import io.restassured.RestAssured
import org.hamcrest.Matchers
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import java.util.*

internal class SysUserGraphApiTest {

    @Order(1)
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

    @Order(2)
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

    @Order(3)
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

    @Order(4)
    @Test
    fun updateSysUserById() {
        val username = "admin"
        val email = "admin@gmail.com"
        val query = """
            {
                "query": "mutation {
                    updateSysUserById(id: 1, data: {
                        username: \"$username\",
                        email: \"$email\",
                        departmentId: 1,
                        roles: [1]
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
        response.body("data.updateSysUserById.id", Matchers.greaterThan(0))
    }

    @Order(5)
    @Test
    fun activeSysUserById() {
        val query = """
            {
                "query": "mutation {
                    activeSysUserById(id: 1) {
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
        // 判断返回值是 true
        response.body("data.activeSysUserById", Matchers.equalTo(true))
    }

    @Order(6)
    @Test
    fun disableSysUserById() {
        val query = """
            {
                "query": "mutation {
                    disableSysUserById(id: 1) {
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
        // 判断返回值是 true
        response.body("data.disableSysUserById", Matchers.equalTo(true))
    }

    @Order(7)
    @Test
    fun deleteSysUserById() {
        val query = """
            {
                "query": "mutation {
                    deleteSysUserById(id: 1) {
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
        // 判断返回值是 true
        response.body("data.deleteSysUserById", Matchers.equalTo(true))
    }

}