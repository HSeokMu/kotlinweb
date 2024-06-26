package sm.domain.account.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sm.domain.account.model.Account
import sm.domain.account.model.AccountDTO
import sm.domain.account.repository.AccountRepository
import sm.utils.BCryptUtil
import sm.utils.ResultWrapper

// UserDetailsService
// PasswordEncoder

@Service
class AccountService(
    @Autowired
    private val accountRepository: AccountRepository,
) : UserDetailsService {

    @Transactional
    fun saveAccount(accountDTO: AccountDTO): ResultWrapper<Unit> {
        // 계정 유효성 체크
        // 1. 기가입된 계정인지
        if(accountRepository.existsByIdOrEmail(accountDTO.id, accountDTO.email)) {
            return ResultWrapper.fail("이미 가입된 계정 또는 이메일입니다.")
        }

        // 2. 이메일 인증 중인 계정인지 (생각 중)


        // 3. 로그인 중인데 계정 생성 시도할 때 (처리 방법 생각 중)
        accountRepository.save(
            Account (
                id = accountDTO.id,
                password = BCryptUtil.encode(accountDTO.password),
                email = accountDTO.email
            )
        )

        return ResultWrapper.ok()
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        // TODO("Not yet implemented")
        return username?.let { accountRepository.findByEmail(it)?.getAuthorities() }
            ?: throw UsernameNotFoundException("$username Can Not Found")
    }
}