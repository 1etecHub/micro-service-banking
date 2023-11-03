package com.kokoro.accountservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity {

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Column(updatable = false)
    private LocalDateTime createdBy;


    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @Column(insertable = false)
    private LocalDateTime updatedBy;

}
