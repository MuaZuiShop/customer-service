package com.us.quy.customerservice.controller;

import com.quy.common.core.security.ERole;
import com.quy.common.servlet.services.UserContext;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health-check")
@Tag(name = "Health Check", description = "API dùng để kiểm tra trạng thái hoạt động của hệ thống")
@Slf4j
public class HeathCheckController {

    @GetMapping
    @Operation(
            summary = "Kiểm tra trạng thái (Health check)",
            description = "Trả về thông điệp 'Hello World!, I'm CUSTOMER SERVICE' "
                    + "để xác nhận rằng service đang hoạt động bình thường."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Service hoạt động bình thường",
            content = @Content(mediaType = "text/plain")
    )
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Hello World!, I'm CUSTOMER SERVICE");
    }


    @GetMapping("/health-check1")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> healthCheckWithToken() {
        String userId = UserContext.getUserId();
        List<ERole> roles = UserContext.getUserRoles();
        return ResponseEntity.ok("userId: " + userId + ", roles: " + roles);
    }

}