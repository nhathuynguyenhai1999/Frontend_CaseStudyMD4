package com.example.casestudylibrary.domain;

import com.example.casestudylibrary.domain.enumration.EStatusDelete;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity implements ApplicationContextAware {
    protected static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false, updatable = true)
    private LocalDateTime updatedAt;

    @Enumerated(value = EnumType.STRING)
    private EStatusDelete status = EStatusDelete.NON_DELETE;

    private String deletedBy;
    private LocalDateTime deletedAt;
    private String createdBy;
    private String updatedBy;

    @PrePersist
    protected void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        createdBy = getCurrentUsername();
        updatedBy = getCurrentUsername();
    }
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
        updatedBy = getCurrentUsername();
    }
    @PreRemove
    protected void onRemove() {
        deletedAt = LocalDateTime.now();
        deletedBy = getCurrentUsername();
    }

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return authentication.getName();
        }
        return null;
    }
}
