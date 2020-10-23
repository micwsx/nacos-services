package com.micwsx.project.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import io.seata.rm.datasource.DataSourceProxy;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author Michael
 * @create 10/21/2020 3:36 PM
 * 需要添加数据源配置类，能及时更新nacos配置属性。
 */
@Configuration
@RefreshScope
public class DataSourceConfig {

    // 来源于nacos中自定义数据源db
    @Value("${db.url}")
    private String url;
    // 来源于nacos中自定义数据源db
    @Value("${db.driver-class-name}")
    private String driverClassName;
    // 来源于nacos中自定义数据源db
    @Value("${db.username}")
    private String username;
    // 来源于nacos中自定义数据源db
    @Value("${db.password}")
    private String password;

    @Bean(initMethod = "init")
    @RefreshScope
    public DruidDataSource dataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        // 配置初始化大小、最小、最大
        druidDataSource.setInitialSize(10);
        druidDataSource.setMinIdle(1);
        druidDataSource.setMaxActive(20);
        druidDataSource.setMaxWait(60000);// 配置获取连接等待超时的时间
        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);// 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        druidDataSource.setMinEvictableIdleTimeMillis(300000);// 配置一个连接在池中最小生存的时间，单位是毫秒
//        druidDataSource.setDefaultAutoCommit(false);// 禁止自动提交，实现事务管理
        druidDataSource.setValidationQuery("SELECT 1;");
        druidDataSource.setValidationQueryTimeout(30000);
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setTestOnBorrow(false);
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setPoolPreparedStatements(true);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(50);
        // 拦截配置
        druidDataSource.setFilters("stat,wall");
        return druidDataSource;
    }

    @Bean
    public DataSourceProxy dataSourceProxy(DataSource dataSource) {
        return new DataSourceProxy(dataSource);
    }

    @Primary
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(DataSourceProxy dataSourceProxy) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSourceProxy);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public ServletRegistrationBean druidStateViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        //初始化参数initParams
        //添加白名单
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1,192.168.1.20");
        //添加ip黑名单
        servletRegistrationBean.addInitParameter("deny", "192.168.16.111");
        //登录查看信息的账号密码
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "admin");
        //是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }
}
