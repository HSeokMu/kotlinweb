package sm.domain.account.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sm.domain.account.model.Account
import sm.domain.account.model.AccountDTO
import sm.domain.account.service.AccountService
import sm.utils.ResultWrapper

@RestController
@RequestMapping("/account")
class AccountController(
    private val accountService: AccountService
) {
    @PostMapping("/register")
    fun register(@RequestBody accountDTO: AccountDTO ): ResultWrapper<Unit> {
        return accountService.saveAccount(accountDTO)
    }
}