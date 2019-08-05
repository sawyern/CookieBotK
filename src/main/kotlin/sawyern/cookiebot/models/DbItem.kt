package sawyern.cookiebot.models

import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
abstract class DbItem (
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator", parameters = [Parameter(name = "uuid_gen_strategy_class", value = "org.hibernate.id.uuid.CustomVersionOneStrategy")])
    private var id: String? = null,

    @Column
    private var createDate: LocalDateTime = LocalDateTime.now()
)