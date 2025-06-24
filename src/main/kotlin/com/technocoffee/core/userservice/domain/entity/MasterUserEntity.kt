package com.technocoffee.core.userservice.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.sql.Timestamp

@Entity
@Table(name = "mst_users")
data class MasterUserEntity(
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "mst_users_id_seq"
    )
    @SequenceGenerator(
        name = "mst_users_id_seq",
        sequenceName = "mst_users_id_seq",
        allocationSize = 1
    )
    @Column(name="id", insertable = false, updatable = false)
    var id: Int = 0,

    @Column(name="email")
    var email: String,

    @Column(name = "password")
    var password: String,

    @Column(name = "name")
    var username: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_membership")
    var membership: MasterMembershipEntity? = null,

    @CreationTimestamp
    @Column(name="created_at", insertable = false, updatable = false)
    var createdAt: Timestamp? = null,

    @Column(name="is_active")
    var isActive: Boolean = false
)
