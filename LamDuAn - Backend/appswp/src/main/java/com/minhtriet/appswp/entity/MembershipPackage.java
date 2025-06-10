package com.minhtriet.appswp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.util.List;

@Entity
@Table(name = "MembershipPackages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembershipPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PackageID")
    private long packageId;

    @Nationalized
    @Column(name = "PackageName", nullable = false, unique = true, length = 255)
    private String packageName;

    @Column(name = "Price", nullable = false)
    private double price;

    @Column(name = "DurationDays", nullable = false)
    private int durationDays;

    @Nationalized
    @Column(name = "Description", columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @Column(name = "IsActive", nullable = false)
    private boolean isActive = true;

    // OneToMany: Lấy danh sách user đang dùng gói này (không bắt buộc)
    @OneToMany(mappedBy = "currentMembershipPackage")
    private List<User> users;
}
