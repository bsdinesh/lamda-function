package com.expanse.controller;

import com.expanse.entity.TenantConfiguration;
import com.expanse.service.TenantConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenant-configurations")
public class TenantConfigurationController {

    @Autowired
    private TenantConfigurationService tenantConfigurationService;

    @GetMapping
    public List<TenantConfiguration> getAllTenantConfigurations() {
        return tenantConfigurationService.getAllTenantConfigurations();
    }

    @GetMapping("/{id}")
    public TenantConfiguration getTenantConfigurationById(@PathVariable String id) {
        return tenantConfigurationService.getTenantConfigurationById(id);
    }

    @PostMapping
    public TenantConfiguration createTenantConfiguration(@RequestBody TenantConfiguration tenantConfiguration) {
        return tenantConfigurationService.createTenantConfiguration(tenantConfiguration);
    }

    @PutMapping("/{id}")
    public TenantConfiguration updateTenantConfiguration(@PathVariable String id, @RequestBody TenantConfiguration tenantConfiguration) {
        return tenantConfigurationService.updateTenantConfiguration(id, tenantConfiguration);
    }

    @DeleteMapping("/{id}")
    public void deleteTenantConfiguration(@PathVariable String id) {
        tenantConfigurationService.deleteTenantConfiguration(id);
    }
}