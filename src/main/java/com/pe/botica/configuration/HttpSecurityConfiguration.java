package com.pe.botica.configuration;

import com.pe.botica.configuration.filter.JwtAuthenticationFilter;
import com.pe.botica.util.RolePermissionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfiguration {
    @Autowired
    private AuthenticationProvider dapAuthProvider;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private AuthorizationManager<RequestAuthorizationContext> authorizationManager;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement( sessionMagConfig ->  sessionMagConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(dapAuthProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authReqConfig ->{
                    authReqConfig.anyRequest().access(authorizationManager);
                })
                .build();
    }

    private static void buildRequestMatchers(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authReqConfig) {
        //Category Controller Endpoints
        authReqConfig.requestMatchers(HttpMethod.GET, "/category/all")
                .hasAnyAuthority(RolePermissionEnum.READ_ALL_CATEGORIES.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/category/searchById/{id}")
                .hasAnyAuthority(RolePermissionEnum.READ_CATEGORY_BY_ID.name());
        authReqConfig.requestMatchers(HttpMethod.POST, "/category/register")
                .hasAnyAuthority(RolePermissionEnum.REGISTER_CATEGORY.name());
        authReqConfig.requestMatchers(HttpMethod.DELETE, "/category/deleteById/{id}")
                .hasAnyAuthority(RolePermissionEnum.DELETE_CATEGORY.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/category/all/productOptions")
                .hasAnyAuthority(RolePermissionEnum.READ_ALL_CATEGORY_OPTIONS.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/category/all/serviceOptions")
                .hasAnyAuthority(RolePermissionEnum.READ_ALL_CATEGORY_OPTIONS.name());

        //DrugstoreProduct Controller Endpoints
        authReqConfig.requestMatchers(HttpMethod.GET, "/drugstoreProduct/all")
                .hasAnyAuthority(RolePermissionEnum.READ_ALL_DRUGSTORE_PRODUCTS.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/drugstoreProduct/locations/{productId}")
                .hasAnyAuthority(RolePermissionEnum.READ_ALL_DRUGSTORE_LOCATIONS.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/drugstoreProduct/searchById/{drugstoreId}/{productId}")
                .hasAnyAuthority(RolePermissionEnum.READ_DRUGSTORE_PRODUCT_BY_ID.name());
        authReqConfig.requestMatchers(HttpMethod.POST, "/drugstoreProduct/register")
                .hasAnyAuthority(RolePermissionEnum.REGISTER_DRUGSTORE_PRODUCT.name());
        authReqConfig.requestMatchers(HttpMethod.DELETE, "/drugstoreProduct/deleteById/{drugstoreId}/{productId}")
                .hasAnyAuthority(RolePermissionEnum.DELETE_DRUGSTORE_PRODUCT.name());
        authReqConfig.requestMatchers(HttpMethod.PUT, "/drugstoreProduct/edit/{drugstoreId}/{productId}")
                .hasAnyAuthority(RolePermissionEnum.UPDATE_DRUGSTORE_PRODUCT.name());

        //DrugstoreServiceController Endpoints
        authReqConfig.requestMatchers(HttpMethod.GET, "/drugstoreService/all")
                .hasAnyAuthority(RolePermissionEnum.READ_ALL_DRUGSTORE_SERVICES.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/drugstoreService/locations/{serviceId}")
                .hasAnyAuthority(RolePermissionEnum.READ_ALL_DRUGSTORE_LOCATIONS.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/drugstoreService/searchById/{drugstoreId}/{serviceId}")
                .hasAnyAuthority(RolePermissionEnum.READ_DRUGSTORE_SERVICE_BY_ID.name());
        authReqConfig.requestMatchers(HttpMethod.POST, "/drugstoreService/register")
                .hasAnyAuthority(RolePermissionEnum.REGISTER_DRUGSTORE_SERVICE.name());
        authReqConfig.requestMatchers(HttpMethod.DELETE, "/drugstoreService/deleteById/{drugstoreId}/{serviceId}")
                .hasAnyAuthority(RolePermissionEnum.DELETE_DRUGSTORE_SERVICE.name());
        authReqConfig.requestMatchers(HttpMethod.PUT, "/drugstoreService/edit/{drugstoreId}/{serviceId}")
                .hasAnyAuthority(RolePermissionEnum.UPDATE_DRUGSTORE_SERVICE.name());

        //Product Controller Endpoints
        authReqConfig.requestMatchers(HttpMethod.GET, "/product/all")
                        .hasAnyAuthority(RolePermissionEnum.READ_ALL_PRODUCTS.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/product/allByCategory/{categoryId}")
                .hasAnyAuthority(RolePermissionEnum.READ_ALL_PRODUCTS_BY_CATEGORY.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/product/allWithDetails")
                .hasAnyAuthority(RolePermissionEnum.READ_ALL_PRODUCTS.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/product/searchById/{id}")
                .hasAnyAuthority(RolePermissionEnum.READ_PRODUCT_BY_ID.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/product/allMyProducts/{drugstoreId}")
                .hasAnyAuthority(RolePermissionEnum.READ_ALL_MY_PRODUCTS.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/product/all/options")
                .hasAnyAuthority(RolePermissionEnum.READ_ALL_PRODUCT_OPTIONS.name());
        authReqConfig.requestMatchers(HttpMethod.POST, "/product/register")
                .hasAnyAuthority(RolePermissionEnum.REGISTER_PRODUCT.name());
        authReqConfig.requestMatchers(HttpMethod.DELETE, "/product/deleteById/{id}")
                .hasAnyAuthority(RolePermissionEnum.DELETE_PRODUCT.name());


        //Purchase Controller Endpoints
        authReqConfig.requestMatchers(HttpMethod.GET, "/purchase/all")
                .hasAnyAuthority(RolePermissionEnum.READ_ALL_PURCHASES.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/purchase/allMyPurchases/{customerId}")
                .hasAnyAuthority(RolePermissionEnum.READ_ALL_MY_PURCHASES.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/purchase/allMySales/{drugstoreId}")
                .hasAnyAuthority(RolePermissionEnum.READ_ALL_MY_SALES.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/purchase/searchById/{id}")
                .hasAnyAuthority(RolePermissionEnum.READ_PURCHASE_BY_ID.name());
        authReqConfig.requestMatchers(HttpMethod.POST, "/purchase/register")
                .hasAnyAuthority(RolePermissionEnum.REGISTER_PURCHASE.name());
        authReqConfig.requestMatchers(HttpMethod.DELETE, "/purchase/deleteById/{id}")
                .hasAnyAuthority(RolePermissionEnum.DELETE_PURCHASE.name());

        //PurchaseDetail Controller Endpoints
        authReqConfig.requestMatchers(HttpMethod.GET, "/purchaseDetail/all")
                .hasAnyAuthority(RolePermissionEnum.READ_ALL_PURCHASE_DETAILS.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/purchaseDetail/allByPurchase/{purchaseId}")
                .hasAnyAuthority(RolePermissionEnum.READ_ALL_PURCHASE_DETAILS_BY_PURCHASE.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/purchaseDetail/searchById/{id}")
                .hasAnyAuthority(RolePermissionEnum.READ_PURCHASE_DETAIL_BY_ID.name());
        authReqConfig.requestMatchers(HttpMethod.POST, "/purchaseDetail/register")
                .hasAnyAuthority(RolePermissionEnum.REGISTER_PURCHASE_DETAIL.name());
        authReqConfig.requestMatchers(HttpMethod.DELETE, "/purchaseDetail/deleteById/{id}")
                .hasAnyAuthority(RolePermissionEnum.DELETE_PURCHASE_DETAIL.name());

        //Role Controller Endpoints
        authReqConfig.requestMatchers(HttpMethod.GET, "/role/searchById/{id}")
                .hasAnyAuthority(RolePermissionEnum.READ_ROLE_BY_ID.name());
        authReqConfig.requestMatchers(HttpMethod.DELETE, "/role/deleteById/{id}")
                .hasAnyAuthority(RolePermissionEnum.DELETE_ROLE.name());
        authReqConfig.requestMatchers(HttpMethod.POST,"/role/register").
                hasAnyAuthority(RolePermissionEnum.REGISTER_ROLE.name());

        //Service Controller Endpoints
        authReqConfig.requestMatchers(HttpMethod.GET, "/service/all")
                .hasAnyAuthority(RolePermissionEnum.READ_ALL_SERVICES.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/service/allByCategory/{categoryId}")
                .hasAnyAuthority(RolePermissionEnum.READ_ALL_SERVICES_BY_CATEGORY.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/service/allWithDetails")
                .hasAnyAuthority(RolePermissionEnum.READ_ALL_SERVICES_WITH_DETAILS.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/service/searchById/{id}")
                .hasAnyAuthority(RolePermissionEnum.READ_SERVICE_BY_ID.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/service/all/options")
                .hasAnyAuthority(RolePermissionEnum.READ_ALL_SERVICE_OPTIONS.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/service/allMyServices/{drugstoreId}")
                .hasAnyAuthority(RolePermissionEnum.READ_ALL_MY_SERVICES.name());
        authReqConfig.requestMatchers(HttpMethod.POST, "/service/register")
                .hasAnyAuthority(RolePermissionEnum.REGISTER_SERVICE.name());
        authReqConfig.requestMatchers(HttpMethod.DELETE, "/service/deleteById/{id}")
                .hasAnyAuthority(RolePermissionEnum.DELETE_SERVICE.name());

        //User Controller Endpoints
        authReqConfig.requestMatchers(HttpMethod.GET, "/user/all")
                .hasAnyAuthority(RolePermissionEnum.READ_ALL_USERS.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/user/searchById/{id}")
                .hasAnyAuthority(RolePermissionEnum.READ_USER_BY_ID.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/user/profile")
                .hasAnyAuthority(RolePermissionEnum.READ_MY_PROFILE.name());
        authReqConfig.requestMatchers(HttpMethod.DELETE, "/user/deleteById/{id}")
                .hasAnyAuthority(RolePermissionEnum.DELETE_USER.name());

        authReqConfig.requestMatchers(HttpMethod.POST,"/user/register").permitAll();
        authReqConfig.requestMatchers(HttpMethod.POST,"/user/auth").permitAll();
        authReqConfig.requestMatchers(HttpMethod.GET,"/user/validate").permitAll();
        authReqConfig.requestMatchers(HttpMethod.GET,"/role/all").permitAll();
        authReqConfig.requestMatchers(HttpMethod.GET,"/role/getList").permitAll();

        authReqConfig.anyRequest().authenticated();
    }
}
