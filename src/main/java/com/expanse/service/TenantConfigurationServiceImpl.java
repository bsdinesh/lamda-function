package com.expanse.service;

import com.expanse.entity.TenantConfiguration;
import com.expanse.repository.TenantConfigurationRepository;
import com.expanse.service.TenantConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantConfigurationServiceImpl implements TenantConfigurationService {

    @Autowired
    private TenantConfigurationRepository tenantConfigurationRepository;

    @Override
    public List<TenantConfiguration> getAllTenantConfigurations() {
        return tenantConfigurationRepository.findAll();
    }

    @Override
    public TenantConfiguration getTenantConfigurationById(String id) {
        return tenantConfigurationRepository.findById(id).orElse(null);
    }

    @Override
    public TenantConfiguration createTenantConfiguration(TenantConfiguration tenantConfiguration) {
        return tenantConfigurationRepository.save(tenantConfiguration);
    }

    @Override
    public TenantConfiguration updateTenantConfiguration(String id, TenantConfiguration tenantConfiguration) {
        if (tenantConfigurationRepository.existsById(id)) {
            tenantConfiguration.setTenantId(id);
            return tenantConfigurationRepository.save(tenantConfiguration);
        }
        return null;
    }

    @Override
    public void deleteTenantConfiguration(String id) {
        tenantConfigurationRepository.deleteById(id);
    }
}