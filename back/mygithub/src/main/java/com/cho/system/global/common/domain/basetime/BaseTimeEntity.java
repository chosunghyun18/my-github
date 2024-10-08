package com.cho.system.global.common.domain.basetime;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;



@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity implements Serializable {

    @CreatedDate
    @Column(name="created_time",updatable = false)
    private LocalDateTime createdDateTime;

    @LastModifiedDate
    @Column(name="updated_time")
    private LocalDateTime  updatedDateTime;

    protected BaseTimeEntity(){}

    @PrePersist
    public void prePersist(){
        LocalDateTime now = LocalDateTime.now();
        createdDateTime = now;
        updatedDateTime = now;
    }

    @PreUpdate
    public void preUpdate(){
        updatedDateTime = LocalDateTime.now();
    }
    public void syncUpdateTimeToCreatedTime(){
        updatedDateTime = this.createdDateTime;
    }
}
