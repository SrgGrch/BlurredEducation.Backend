package tech.blur.bluredu.entity

import java.io.Serializable
import javax.persistence.*

@Entity
@Table
@Deprecated("Use User")
data class Participant(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO, generator = "participant_seq_gen")
        @SequenceGenerator(name = "participant_seq_gen", sequenceName = "id_seq")
        val id: Int,

        @Column
        val name: String,

        @ManyToOne(fetch = FetchType.LAZY, optional = true)
        @JoinTable(name = "COMPANY", joinColumns = [JoinColumn(name = "ID_COMPANY")], inverseJoinColumns = [JoinColumn(name = "ID_PARTICIPANT")])
        val company: Company //TODO rework join
) : Serializable