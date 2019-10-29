package tech.blur.bluredu

import com.zaxxer.hikari.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.*
import javax.sql.DataSource

@Configuration
class DatabaseConfig {

    @Value("\${spring.datasource.url}")
    private val dbUrl: String? = null

    @Value("\${spring.datasource.password}")
    private val dbPassword: String? = null

    @Value("\${spring.datasource.username}")
    private val dbUsername: String? = null

    @Bean
    fun dataSource(): DataSource {
        val config = HikariConfig()
        config.apply {
            jdbcUrl = dbUrl
            password = dbPassword
            username = dbUsername
        }
        return HikariDataSource(config)
    }
}