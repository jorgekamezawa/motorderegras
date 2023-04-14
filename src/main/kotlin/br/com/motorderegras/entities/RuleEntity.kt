package br.com.motorderegras.entities

import br.com.motorderegras.domain.Definition
import br.com.motorderegras.domain.Rule
import br.com.motorderegras.view.toView
import io.hypersistence.utils.hibernate.type.json.JsonType
import jakarta.persistence.*
import org.hibernate.annotations.Type

@Entity
@Table(name = "tb_regras")
class RuleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(name = "name")
    val name: String,
    @Column(name = "origin")
    val origin: String,
    @Column(name = "priority_order")
    val priorityOrder: Int,
    @Type(JsonType::class)
    @Column(columnDefinition = "jsonb")
    val definitionList: List<Definition>
)

fun RuleEntity.toDomain() =
    Rule(
        id = this.id,
        name = this.name,
        origin = this.origin,
        priorityOrder = this.priorityOrder,
        definitionList = this.definitionList
    )

fun Rule.toEntity() =
    RuleEntity(
        id = this.id,
        name = this.name,
        origin = this.origin,
        priorityOrder = this.priorityOrder,
        definitionList = this.definitionList
    )