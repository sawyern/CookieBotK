package sawyern.cookiebot.models

import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
abstract class DbItem (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: String? = null,

    @Column
    private var createDate: LocalDateTime = LocalDateTime.now()
)