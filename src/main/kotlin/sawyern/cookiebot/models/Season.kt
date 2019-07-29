package sawyern.cookiebot.models

import javax.persistence.Column
import javax.persistence.Entity

@Entity
data class Season(
        @Column
        var name: String,

        @Column
        var active: Boolean
): DbItem()