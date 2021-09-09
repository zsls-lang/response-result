package com.zsls.framework.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.zsls.common.base.BaseMapper;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.mapper.autoconfigure.SpringBootVFS;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;


@Configuration
@MapperScan(basePackages = "com.zsls.*.dao",markerInterface = BaseMapper.class, sqlSessionTemplateRef =
    "sqlSessionTemplateFramework")
public class DatasourceConfig {
    public static final String COMMA_SEPARATOR = ",";

    @Value("${framework.aliases.package}")
    private String aliasPacage;

    @Value("${framework.mapper}")
    private String frameworkMapper;



    @Bean(name = "dataSourceFramework")
    @ConfigurationProperties(prefix = "spring.datasource.framework")
    @Primary
    public DataSource dataSourceFramework(){
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;

    }

    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
//        registrationBean.addInitParameter("allow", "127.0.0.1"); // IP白名单 (没有配置或者为空，则允许所有访问)
        registrationBean.addInitParameter("deny", ""); // IP黑名单 (存在共同时，deny优先于allow)
        registrationBean.addInitParameter("loginUsername", "admin");
        registrationBean.addInitParameter("loginPassword", "admin");
        registrationBean.addInitParameter("resetEnable", "false");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean druidWebStatViewFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new WebStatFilter());
        registrationBean.addInitParameter("urlPatterns", "/*");
        registrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        return registrationBean;
    }


    @Bean(name = "sqlSessionFactoryFramework")
    @Primary
    public SqlSessionFactory sqlSessionFactoryFramework(@Qualifier("dataSourceFramework") DataSource dataSource)
            throws Exception{
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        VFS.addImplClass(SpringBootVFS.class);
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setTypeAliasesPackage(aliasPacage);

        // Mybatis Config
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sessionFactory.setConfiguration(configuration);

        // Mybatis Mapper XML Config
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //*.mapper.xml的地址（根据你的项目自行修改）
        String[] resouces = frameworkMapper.split(COMMA_SEPARATOR);
        Resource[] resourceArray = null;
        for(String resouce : resouces){
            Resource[] resourceArray1 = resolver.getResources(resouce);
            if(resourceArray==null){
                resourceArray = resourceArray1;
            }
            else {
                resourceArray = ArrayUtils.addAll(resourceArray, resourceArray1);
            }
        }
        sessionFactory.setMapperLocations(resourceArray);
        return sessionFactory.getObject();
    }

    @Bean(name = "dataSourceTransactionManagerFramework")
    @Primary
    public DataSourceTransactionManager dataSourceTransactionManagerFrameork(@Qualifier("dataSourceFramework") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionTemplateFramework")
    @Primary
    public SqlSessionTemplate sqlSessionTemplateFramework(@Qualifier("sqlSessionFactoryFramework")
        SqlSessionFactory sqlSessionFactory)throws Exception{
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}