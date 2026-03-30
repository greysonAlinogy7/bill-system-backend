package com.billing.billing.system.model;

import com.billing.billing.system.domain.StoreStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private  String brand;
    @OneToOne
    private User storeAdmin;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private  String description;
    private String storeType;

    @Enumerated(EnumType.STRING)
    private StoreStatus status = StoreStatus.PENDING;

    @Embedded
    private StoreContact contact = new StoreContact();

}
