package sawyern.cookiebot.models

import javax.persistence.*

@Entity
@Table(name = "COOKIES")
data class Cookie(
    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    var account: Account,

    @Column
    var season: String,

    @Column
    var source: String
): DbItem()