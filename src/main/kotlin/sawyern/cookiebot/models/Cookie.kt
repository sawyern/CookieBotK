package sawyern.cookiebot.models

import javax.persistence.*

@Entity
data class Cookie(
    @ManyToOne
    @JoinColumn
    var account: Account,

    @Column
    var season: String,

    @Column
    var source: String
): DbItem()