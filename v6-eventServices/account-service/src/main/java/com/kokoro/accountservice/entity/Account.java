 package com.kokoro.accountservice.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Account extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    private Long accountNumber;

    private String accountType;

    private String branchAddress;

    @Column(name = "communication_sw")
    private Boolean communicationSw;

}
