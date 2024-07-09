package com.pe.botica.util;

import java.util.Arrays;
import java.util.List;

public enum RoleEnum {
    ROLE_ADMINISTRATOR(Arrays.asList(
            RolePermissionEnum.READ_ALL_CATEGORIES,
            RolePermissionEnum.READ_CATEGORY_BY_ID,
            RolePermissionEnum.REGISTER_CATEGORY,
            RolePermissionEnum.DELETE_CATEGORY,
            RolePermissionEnum.READ_ALL_PRODUCTS,
            RolePermissionEnum.READ_PRODUCT_BY_ID,
            RolePermissionEnum.READ_ALL_PRODUCTS_WITH_DETAILS,
            RolePermissionEnum.READ_ALL_SERVICES_WITH_DETAILS,
            RolePermissionEnum.REGISTER_PRODUCT,
            RolePermissionEnum.DELETE_PRODUCT,
            RolePermissionEnum.READ_ALL_SERVICES,
            RolePermissionEnum.READ_SERVICE_BY_ID,
            RolePermissionEnum.REGISTER_SERVICE,
            RolePermissionEnum.DELETE_SERVICE,
            RolePermissionEnum.READ_ALL_ROLES,
            RolePermissionEnum.READ_ROLE_BY_ID,
            RolePermissionEnum.REGISTER_ROLE,
            RolePermissionEnum.DELETE_ROLE,
            RolePermissionEnum.READ_ALL_PURCHASES,
            RolePermissionEnum.READ_PURCHASE_BY_ID,
            RolePermissionEnum.REGISTER_PURCHASE,
            RolePermissionEnum.DELETE_PURCHASE,
            RolePermissionEnum.READ_ALL_PURCHASE_DETAILS,
            RolePermissionEnum.READ_PURCHASE_DETAIL_BY_ID,
            RolePermissionEnum.REGISTER_PURCHASE_DETAIL,
            RolePermissionEnum.DELETE_PURCHASE_DETAIL,
            RolePermissionEnum.READ_ALL_USERS,
            RolePermissionEnum.READ_USER_BY_ID,
            RolePermissionEnum.REGISTER_USER,
            RolePermissionEnum.DELETE_USER,
            RolePermissionEnum.READ_ALL_DRUGSTORE_PRODUCTS,
            RolePermissionEnum.READ_DRUGSTORE_PRODUCT_BY_ID,
            RolePermissionEnum.REGISTER_DRUGSTORE_PRODUCT,
            RolePermissionEnum.DELETE_DRUGSTORE_PRODUCT,
            RolePermissionEnum.READ_ALL_DRUGSTORE_SERVICES,
            RolePermissionEnum.READ_DRUGSTORE_SERVICE_BY_ID,
            RolePermissionEnum.REGISTER_DRUGSTORE_SERVICE,
            RolePermissionEnum.DELETE_DRUGSTORE_SERVICE,
            RolePermissionEnum.READ_MY_PROFILE,
            RolePermissionEnum.READ_ALL_MY_PRODUCTS,
            RolePermissionEnum.READ_ALL_MY_SERVICES,
            RolePermissionEnum.UPDATE_DRUGSTORE_PRODUCT,
            RolePermissionEnum.READ_ALL_PRODUCT_OPTIONS,
            RolePermissionEnum.UPDATE_DRUGSTORE_SERVICE,
            RolePermissionEnum.READ_ALL_SERVICE_OPTIONS,
            RolePermissionEnum.READ_ALL_MY_SALES,
            RolePermissionEnum.READ_ALL_MY_PURCHASES,
            RolePermissionEnum.READ_ALL_PURCHASE_DETAILS_BY_PURCHASE,
            RolePermissionEnum.READ_ALL_SERVICES_BY_CATEGORY,
            RolePermissionEnum.READ_ALL_PRODUCTS_BY_CATEGORY,
            RolePermissionEnum.READ_ALL_CATEGORY_OPTIONS,
            RolePermissionEnum.READ_ALL_DRUGSTORE_LOCATIONS
    )),
    ROLE_CUSTOMER(Arrays.asList(
            RolePermissionEnum.READ_ALL_CATEGORIES,
            RolePermissionEnum.READ_CATEGORY_BY_ID,
            RolePermissionEnum.READ_ALL_PRODUCTS,
            RolePermissionEnum.READ_PRODUCT_BY_ID,
            RolePermissionEnum.READ_ALL_SERVICES,
            RolePermissionEnum.READ_SERVICE_BY_ID,
            RolePermissionEnum.READ_ALL_PURCHASES,
            RolePermissionEnum.READ_PURCHASE_BY_ID,
            RolePermissionEnum.REGISTER_PURCHASE,
            RolePermissionEnum.READ_ALL_PURCHASE_DETAILS,
            RolePermissionEnum.READ_PURCHASE_DETAIL_BY_ID,
            RolePermissionEnum.REGISTER_PURCHASE_DETAIL,
            RolePermissionEnum.READ_ALL_DRUGSTORE_PRODUCTS,
            RolePermissionEnum.READ_DRUGSTORE_PRODUCT_BY_ID,
            RolePermissionEnum.READ_ALL_DRUGSTORE_SERVICES,
            RolePermissionEnum.READ_DRUGSTORE_SERVICE_BY_ID,
            RolePermissionEnum.READ_ALL_PRODUCTS_WITH_DETAILS,
            RolePermissionEnum.READ_ALL_SERVICES_WITH_DETAILS,
            RolePermissionEnum.READ_MY_PROFILE,
            RolePermissionEnum.READ_ALL_MY_PURCHASES,
            RolePermissionEnum.READ_ALL_PURCHASE_DETAILS_BY_PURCHASE,
            RolePermissionEnum.READ_ALL_SERVICES_BY_CATEGORY,
            RolePermissionEnum.READ_ALL_PRODUCTS_BY_CATEGORY,
            RolePermissionEnum.READ_ALL_CATEGORY_OPTIONS,
            RolePermissionEnum.READ_ALL_DRUGSTORE_LOCATIONS
    )),
    ROLE_DRUGSTORE(Arrays.asList(
            RolePermissionEnum.READ_ALL_CATEGORIES,
            RolePermissionEnum.READ_CATEGORY_BY_ID,
            RolePermissionEnum.READ_PRODUCT_BY_ID,
            RolePermissionEnum.READ_SERVICE_BY_ID,
            RolePermissionEnum.READ_ALL_DRUGSTORE_PRODUCTS,
            RolePermissionEnum.READ_DRUGSTORE_PRODUCT_BY_ID,
            RolePermissionEnum.REGISTER_DRUGSTORE_PRODUCT,
            RolePermissionEnum.DELETE_DRUGSTORE_PRODUCT,
            RolePermissionEnum.READ_ALL_DRUGSTORE_SERVICES,
            RolePermissionEnum.READ_DRUGSTORE_SERVICE_BY_ID,
            RolePermissionEnum.REGISTER_DRUGSTORE_SERVICE,
            RolePermissionEnum.DELETE_DRUGSTORE_SERVICE,
            RolePermissionEnum.READ_MY_PROFILE,
            RolePermissionEnum.READ_ALL_MY_PRODUCTS,
            RolePermissionEnum.READ_ALL_MY_SERVICES,
            RolePermissionEnum.UPDATE_DRUGSTORE_PRODUCT,
            RolePermissionEnum.READ_ALL_PRODUCT_OPTIONS,
            RolePermissionEnum.UPDATE_DRUGSTORE_SERVICE,
            RolePermissionEnum.READ_ALL_SERVICE_OPTIONS,
            RolePermissionEnum.READ_ALL_MY_SALES,
            RolePermissionEnum.READ_ALL_PURCHASE_DETAILS_BY_PURCHASE

    ));

    private List<RolePermissionEnum> permissions;

    RoleEnum(List<RolePermissionEnum> permissions) {
        this.permissions = permissions;
    }

    public List<RolePermissionEnum> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<RolePermissionEnum> permissions) {
        this.permissions = permissions;
    }
}
