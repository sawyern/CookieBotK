package sawyern.cookiebot.models

import javax.persistence.Column
import javax.persistence.Entity

@Entity
data class Account(
        @Column
        var discordId: String,

        @Column
        var username: String
): DbItem()