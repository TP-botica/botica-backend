package com.pe.botica.configuration;

import com.pe.botica.configuration.filter.JwtAuthenticationFilter;
import com.pe.botica.util.RolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfiguration {
    @Autowired
    private AuthenticationProvider dapAuthProvider;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement( sessionMagConfig ->  sessionMagConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(dapAuthProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(HttpSecurityConfiguration::buildRequestMatchers)
                .build();
    }

    private static void buildRequestMatchers(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authReqConfig) {
        //Category Controller Endpoints
        authReqConfig.requestMatchers(HttpMethod.GET, "/category/all")
                .hasAnyAuthority(RolePermission.READ_ALL_CATEGORIES.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/category/searchById/{id}")
                .hasAnyAuthority(RolePermission.READ_CATEGORY_BY_ID.name());
        authReqConfig.requestMatchers(HttpMethod.POST, "/category/register")
                .hasAnyAuthority(RolePermission.REGISTER_CATEGORY.name());
        authReqConfig.requestMatchers(HttpMethod.DELETE, "/category/deleteById/{id}")
                .hasAnyAuthority(RolePermission.DELETE_CATEGORY.name());

        //DrugstoreProduct Controller Endpoints
        authReqConfig.requestMatchers(HttpMethod.GET, "/drugstoreProduct/all")
                .hasAnyAuthority(RolePermission.READ_ALL_DRUGSTORE_PRODUCTS.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/drugstoreProduct/searchById/{drugstoreId}/{productId}")
                .hasAnyAuthority(RolePermission.READ_DRUGSTORE_PRODUCT_BY_ID.name());
        authReqConfig.requestMatchers(HttpMethod.POST, "/drugstoreProduct/register")
                .hasAnyAuthority(RolePermission.REGISTER_DRUGSTORE_PRODUCT.name());
        authReqConfig.requestMatchers(HttpMethod.DELETE, "/drugstoreProduct/deleteById/{drugstoreId}/{productId}")
                .hasAnyAuthority(RolePermission.DELETE_DRUGSTORE_PRODUCT.name());
        authReqConfig.requestMatchers(HttpMethod.PUT, "/drugstoreProduct/edit/{drugstoreId}/{productId}")
                .hasAnyAuthority(RolePermission.UPDATE_DRUGSTORE_PRODUCT.name());

        //DrugstoreServiceController Endpoints
        authReqConfig.requestMatchers(HttpMethod.GET, "/drugstoreService/all")
                .hasAnyAuthority(RolePermission.READ_ALL_DRUGSTORE_SERVICES.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/drugstoreService/searchById/{drugstoreId}/{serviceId}")
                .hasAnyAuthority(RolePermission.READ_DRUGSTORE_SERVICE_BY_ID.name());
        authReqConfig.requestMatchers(HttpMethod.POST, "/drugstoreService/register")
                .hasAnyAuthority(RolePermission.REGISTER_DRUGSTORE_SERVICE.name());
        authReqConfig.requestMatchers(HttpMethod.DELETE, "/drugstoreService/deleteById/{drugstoreId}/{serviceId}")
                .hasAnyAuthority(RolePermission.DELETE_DRUGSTORE_SERVICE.name());
        authReqConfig.requestMatchers(HttpMethod.PUT, "/drugstoreService/edit/{drugstoreId}/{serviceId}")
                .hasAnyAuthority(RolePermission.UPDATE_DRUGSTORE_SERVICE.name());

        //Product Controller Endpoints
        authReqConfig.requestMatchers(HttpMethod.GET, "/product/all")
                        .hasAnyAuthority(RolePermission.READ_ALL_PRODUCTS.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/product/allWithDetails")
                .hasAnyAuthority(RolePermission.READ_ALL_PRODUCTS.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/product/searchById/{id}")
                .hasAnyAuthority(RolePermission.READ_PRODUCT_BY_ID.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/product/allMyProducts/{drugstoreId}")
                .hasAnyAuthority(RolePermission.READ_ALL_MY_PRODUCTS.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/product/all/options")
                .hasAnyAuthority(RolePermission.READ_ALL_PRODUCT_OPTIONS.name());
        authReqConfig.requestMatchers(HttpMethod.POST, "/product/register")
                .hasAnyAuthority(RolePermission.REGISTER_PRODUCT.name());
        authReqConfig.requestMatchers(HttpMethod.DELETE, "/product/deleteById/{id}")
                .hasAnyAuthority(RolePermission.DELETE_PRODUCT.name());


        //Purchase Controller Endpoints
        authReqConfig.requestMatchers(HttpMethod.GET, "/purchase/all")
                .hasAnyAuthority(RolePermission.READ_ALL_PURCHASES.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/purchase/searchById/{id}")
                .hasAnyAuthority(RolePermission.READ_PURCHASE_BY_ID.name());
        authReqConfig.requestMatchers(HttpMethod.POST, "/purchase/register")
                .hasAnyAuthority(RolePermission.REGISTER_PURCHASE.name());
        authReqConfig.requestMatchers(HttpMethod.DELETE, "/purchase/deleteById/{id}")
                .hasAnyAuthority(RolePermission.DELETE_PURCHASE.name());

        //PurchaseDetail Controller Endpoints
        authReqConfig.requestMatchers(HttpMethod.GET, "/purchaseDetail/all")
                .hasAnyAuthority(RolePermission.READ_ALL_PURCHASE_DETAILS.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/purchaseDetail/searchById/{id}")
                .hasAnyAuthority(RolePermission.READ_PURCHASE_DETAIL_BY_ID.name());
        authReqConfig.requestMatchers(HttpMethod.POST, "/purchaseDetail/register")
                .hasAnyAuthority(RolePermission.REGISTER_PURCHASE_DETAIL.name());
        authReqConfig.requestMatchers(HttpMethod.DELETE, "/purchaseDetail/deleteById/{id}")
                .hasAnyAuthority(RolePermission.DELETE_PURCHASE_DETAIL.name());

        //Role Controller Endpoints
        authReqConfig.requestMatchers(HttpMethod.GET, "/role/searchById/{id}")
                .hasAnyAuthority(RolePermission.READ_ROLE_BY_ID.name());
        authReqConfig.requestMatchers(HttpMethod.DELETE, "/role/deleteById/{id}")
                .hasAnyAuthority(RolePermission.DELETE_ROLE.name());

        //Service Controller Endpoints
        authReqConfig.requestMatchers(HttpMethod.GET, "/service/all")
                .hasAnyAuthority(RolePermission.READ_ALL_SERVICES.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/service/allWithDetails")
                .hasAnyAuthority(RolePermission.READ_ALL_SERVICES_WITH_DETAILS.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/service/searchById/{id}")
                .hasAnyAuthority(RolePermission.READ_SERVICE_BY_ID.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/service/all/options")
                .hasAnyAuthority(RolePermission.READ_ALL_SERVICE_OPTIONS.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/service/allMyServices/{drugstoreId}")
                .hasAnyAuthority(RolePermission.READ_ALL_MY_SERVICES.name());
        authReqConfig.requestMatchers(HttpMethod.POST, "/service/register")
                .hasAnyAuthority(RolePermission.REGISTER_SERVICE.name());
        authReqConfig.requestMatchers(HttpMethod.DELETE, "/service/deleteById/{id}")
                .hasAnyAuthority(RolePermission.DELETE_SERVICE.name());

        //User Controller Endpoints
        authReqConfig.requestMatchers(HttpMethod.GET, "/user/all")
                .hasAnyAuthority(RolePermission.READ_ALL_USERS.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/user/searchById/{id}")
                .hasAnyAuthority(RolePermission.READ_USER_BY_ID.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/user/profile")
                .hasAnyAuthority(RolePermission.READ_MY_PROFILE.name());
        authReqConfig.requestMatchers(HttpMethod.DELETE, "/user/deleteById/{id}")
                .hasAnyAuthority(RolePermission.DELETE_USER.name());

        authReqConfig.requestMatchers(HttpMethod.POST,"/user/register").permitAll();
        authReqConfig.requestMatchers(HttpMethod.POST,"/user/auth").permitAll();
        authReqConfig.requestMatchers(HttpMethod.GET,"/user/validate").permitAll();
        authReqConfig.requestMatchers(HttpMethod.GET,"/role/all").permitAll();
        authReqConfig.requestMatchers(HttpMethod.GET,"/role/getList").permitAll();
        authReqConfig.requestMatchers(HttpMethod.POST,"/role/register").permitAll();

        authReqConfig.anyRequest().authenticated();
    }
}
