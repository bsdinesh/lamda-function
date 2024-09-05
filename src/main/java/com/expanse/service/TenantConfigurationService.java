package com.expanse.service;

import com.expanse.entity.TenantConfiguration;

import java.util.List;

public interface TenantConfigurationService {
    List<TenantConfiguration> getAllTenantConfigurations();
    TenantConfiguration getTenantConfigurationById(String id);
    TenantConfiguration createTenantConfiguration(TenantConfiguration tenantConfiguration);
    TenantConfiguration updateTenantConfiguration(String id, TenantConfiguration tenantConfiguration);
    void deleteTenantConfiguration(String id);
}