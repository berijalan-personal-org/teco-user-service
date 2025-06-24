package com.technocoffee.core.userservice.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table

@Entity
@Table(name = "mst_memberships")
data class MasterMembershipEntity(
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

    @Column(name = "name")
    var name: String = "",

    @Column(name = "discount")
    var description: Double = 0.00,
)
