package com.pe.botica.util;

import java.util.Arrays;
import java.util.List;

public enum RoleEnum {
    ROLE_ADMINISTRATOR(Arrays.asList(
            RolePermission.READ_ALL_CATEGORIES,
            RolePermission.READ_CATEGORY_BY_ID,
            RolePermission.REGISTER_CATEGORY,
            RolePermission.DELETE_CATEGORY,
            RolePermission.READ_ALL_PRODUCTS,
            RolePermission.READ_PRODUCT_BY_ID,
            RolePermission.READ_ALL_PRODUCTS_WITH_DETAILS,
            RolePermission.READ_ALL_SERVICES_WITH_DETAILS,
            RolePermission.REGISTER_PRODUCT,
            RolePermission.DELETE_PRODUCT,
            RolePermission.READ_ALL_SERVICES,
            RolePermission.READ_SERVICE_BY_ID,
            RolePermission.REGISTER_SERVICE,
            RolePermission.DELETE_SERVICE,
            RolePermission.READ_ALL_ROLES,
            RolePermission.READ_ROLE_BY_ID,
            RolePermission.REGISTER_ROLE,
            RolePermission.DELETE_ROLE,
            RolePermission.READ_ALL_PURCHASES,
            RolePermission.READ_PURCHASE_BY_ID,
            RolePermission.REGISTER_PURCHASE,
            RolePermission.DELETE_PURCHASE,
            RolePermission.READ_ALL_PURCHASE_DETAILS,
            RolePermission.READ_PURCHASE_DETAIL_BY_ID,
            RolePermission.REGISTER_PURCHASE_DETAIL,
            RolePermission.DELETE_PURCHASE_DETAIL,
            RolePermission.READ_ALL_USERS,
            RolePermission.READ_USER_BY_ID,
            RolePermission.REGISTER_USER,
            RolePermission.DELETE_USER,
            RolePermission.READ_ALL_DRUGSTORE_PRODUCTS,
            RolePermission.READ_DRUGSTORE_PRODUCT_BY_ID,
            RolePermission.REGISTER_DRUGSTORE_PRODUCT,
            RolePermission.DELETE_DRUGSTORE_PRODUCT,
            RolePermission.READ_ALL_DRUGSTORE_SERVICES,
            RolePermission.READ_DRUGSTORE_SERVICE_BY_ID,
            RolePermission.REGISTER_DRUGSTORE_SERVICE,
            RolePermission.DELETE_DRUGSTORE_SERVICE,
            RolePermission.READ_MY_PROFILE,
            RolePermission.READ_ALL_MY_PRODUCTS,
            RolePermission.READ_ALL_MY_SERVICES,
            RolePermission.UPDATE_DRUGSTORE_PRODUCT,
            RolePermission.READ_ALL_PRODUCT_OPTIONS,
            RolePermission.UPDATE_DRUGSTORE_SERVICE,
            RolePermission.READ_ALL_SERVICE_OPTIONS
    )),
    ROLE_CUSTOMER(Arrays.asList(
            RolePermission.READ_ALL_CATEGORIES,
            RolePermission.READ_CATEGORY_BY_ID,
            RolePermission.READ_ALL_PRODUCTS,
            RolePermission.READ_PRODUCT_BY_ID,
            RolePermission.READ_ALL_SERVICES,
            RolePermission.READ_SERVICE_BY_ID,
            RolePermission.READ_ALL_PURCHASES,
            RolePermission.READ_PURCHASE_BY_ID,
            RolePermission.REGISTER_PURCHASE,
            RolePermission.READ_ALL_PURCHASE_DETAILS,
            RolePermission.READ_PURCHASE_DETAIL_BY_ID,
            RolePermission.REGISTER_PURCHASE_DETAIL,
            RolePermission.READ_ALL_DRUGSTORE_PRODUCTS,
            RolePermission.READ_DRUGSTORE_PRODUCT_BY_ID,
            RolePermission.READ_ALL_DRUGSTORE_SERVICES,
            RolePermission.READ_DRUGSTORE_SERVICE_BY_ID,
            RolePermission.READ_ALL_PRODUCTS_WITH_DETAILS,
            RolePermission.READ_ALL_SERVICES_WITH_DETAILS,
            RolePermission.READ_MY_PROFILE
    )),
    ROLE_DRUGSTORE(Arrays.asList(
            RolePermission.READ_ALL_CATEGORIES,
            RolePermission.READ_CATEGORY_BY_ID,
            RolePermission.READ_ALL_PRODUCTS,
            RolePermission.READ_PRODUCT_BY_ID,
            RolePermission.READ_ALL_SERVICES,
            RolePermission.READ_SERVICE_BY_ID,
            RolePermission.READ_ALL_DRUGSTORE_PRODUCTS,
            RolePermission.READ_DRUGSTORE_PRODUCT_BY_ID,
            RolePermission.REGISTER_DRUGSTORE_PRODUCT,
            RolePermission.DELETE_DRUGSTORE_PRODUCT,
            RolePermission.READ_ALL_DRUGSTORE_SERVICES,
            RolePermission.READ_DRUGSTORE_SERVICE_BY_ID,
            RolePermission.REGISTER_DRUGSTORE_SERVICE,
            RolePermission.DELETE_DRUGSTORE_SERVICE,
            RolePermission.READ_MY_PROFILE,
            RolePermission.READ_ALL_MY_PRODUCTS,
            RolePermission.READ_ALL_MY_SERVICES,
            RolePermission.UPDATE_DRUGSTORE_PRODUCT,
            RolePermission.READ_ALL_PRODUCT_OPTIONS,
            RolePermission.UPDATE_DRUGSTORE_SERVICE,
            RolePermission.READ_ALL_SERVICE_OPTIONS
    ));

    private List<RolePermission> permissions;

    RoleEnum(List<RolePermission> permissions) {
        this.permissions = permissions;
    }

    public List<RolePermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<RolePermission> permissions) {
        this.permissions = permissions;
    }
}
