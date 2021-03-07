package app.shopping.entity.mapper;

import app.shopping.entity.Account;
import app.shopping.entity.dto.AccountDTO;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountDTO toDto(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setFirstname(account.getFirstname());
        accountDTO.setLastname(account.getLastname());
        accountDTO.setUsername(account.getUsername());
        accountDTO.setPassword(account.getPassword());
        return accountDTO;

    }

    public Account toEntity(AccountDTO accountDTO) {
        Account account = new Account();
        account.setId(accountDTO.getId());
        account.setFirstname(accountDTO.getFirstname());
        account.setLastname(accountDTO.getLastname());
        account.setUsername(accountDTO.getUsername());
        account.setPassword(accountDTO.getPassword());
        return account;
    }
}
