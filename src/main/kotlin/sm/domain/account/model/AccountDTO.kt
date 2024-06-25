package sm.domain.account.model

class AccountDTO(
    var id: String = "",
    var password: String = "",
    var email: String = "",
) {
    fun toDTO(): AccountDTO {
        return AccountDTO(id, )
    }
}