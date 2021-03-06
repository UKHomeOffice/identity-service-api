package io.digital.patterns.identity.api.controller;

import io.digital.patterns.identity.api.service.ScanRepositoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ConditionalOnProperty(prefix = "admin.controller", name = "enabled", matchIfMissing = true)
@RequestMapping(path = "/admin")
@PreAuthorize(value = "@authorizationChecker.hasReadRoles(authentication) " +
        " && @authorizationChecker.hasAdminRoles(authentication)")
@Tag(name = "Admin", description = "Advanced administrator functions")
public class AdminController {

    private final ScanRepositoryService scanRepositoryService;

    public AdminController(ScanRepositoryService scanRepositoryService) {
        this.scanRepositoryService = scanRepositoryService;
    }

    @DeleteMapping(path = "/mrz/{correlationId}")
    @PreAuthorize(value = "@authorizationChecker.hasUpdateRoles(authentication)")
    @Operation(summary = "Remove MRZ scans for a given correlation id")
    public void delete(@Parameter(description = "ID that can link a list of scans", required = true)
                       @PathVariable String correlationId) {
        scanRepositoryService.delete(correlationId);
    }

}
