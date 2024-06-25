package sm.domain.account.model

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import java.time.OffsetDateTime
import java.time.ZonedDateTime
import java.util.stream.Collectors

@Entity
@Table(name = "account")
class Account (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    var pId: Int = 0,

    @Column(nullable = false)
    var id: String,

    @Column(nullable = false, length = 512)
    var password: String,

    @Column(nullable = false, length = 64)
    var email: String,

    /*
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    var roles: Set<AccountRole>,
    */

    @ElementCollection
    @CollectionTable(name="account_role", joinColumns = [JoinColumn(name="id")])
    /**
     * @Enumerated
     * enum class test { A, B, C } 가 있다면 A = 1, B = 2, C = 3
     * EnumType.ORIGINAL : Enum의 순서(숫자)로 저장 (1, 2, 3)
     * EnumType.STRING   : Enum의 이름으로 저장 (A, B, C)
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 10)
    val roles: MutableSet<AccountRole> = mutableSetOf(),
    // var roles: List<String> = listOf(),

    @Column(nullable = false)
    var ip: String = "",

    @CreationTimestamp
    var regDt: ZonedDateTime = ZonedDateTime.now(),

    @UpdateTimestamp
    var uptDt: ZonedDateTime = ZonedDateTime.now()

    /*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    var id: String,

    @Column(nullable = false, length = 512)
    var pwd: String,

    @Column(nullable = false, length = 64)
    var email: String,

    /*
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    var roles: Set<AccountRole>,
    */

    @ElementCollection
    @CollectionTable(name="AccountRole", joinColumns = [JoinColumn(name="an")])
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var roles: List<String> = listOf(),

    @Column(nullable = false)
    var ip: String,

    @CreationTimestamp
    var createdDt: ZonedDateTime = ZonedDateTime.now(),

    @UpdateTimestamp
    var updatedDt: ZonedDateTime = ZonedDateTime.now()
    */
) {
    /*
    @ColumnDefault("now()")
    @Column(name = "reg_dt", nullable = false)
    open var regDt: OffsetDateTime? = null

    @Column(name = "upt_dt")
    open var uptDt: OffsetDateTime? = null
    */

    fun getAuthorities(): User {
        return User(
            this.email, this.password,
            this.roles.stream().map { role -> SimpleGrantedAuthority("ROLE_$role") }.collect(Collectors.toSet())
        )
    }
}