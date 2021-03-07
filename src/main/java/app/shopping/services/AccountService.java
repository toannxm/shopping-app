package app.shopping.services;

import app.shopping.entity.Account;
import app.shopping.entity.dto.AccountDTO;
import app.shopping.entity.mapper.AccountMapper;
import app.shopping.repository.AccountRepository;
import app.shopping.rest.exception.ResourceAlreadyExistsException;
import app.shopping.rest.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;

    public Page<AccountDTO> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable).map(accountMapper::toDto);
    }

    public AccountDTO findByUsername(String username) {
        Optional<Account> accountOptional = accountRepository.findByUsername(username);
        AccountDTO accountDTO = null;
        if (accountOptional.isPresent()) {
            accountDTO = accountMapper.toDto(accountOptional.get());
        }
        return accountDTO;
    }

    public AccountDTO createAccount(AccountDTO accountDTO) {
        Optional<Account> accountOptional = accountRepository.findByUsername(accountDTO.getUsername());
        if (accountOptional.isPresent()) {
            throw new ResourceAlreadyExistsException("Account already existed by: ".concat(accountDTO.getUsername()));
        } else {
            accountDTO.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
            return accountMapper.toDto(accountRepository.save(accountMapper.toEntity(accountDTO)));
        }
    }

    public void deleteByUsername(String username) {
        Optional<Account> accountOptional = accountRepository.findByUsername(username);
        if (accountOptional.isPresent()) {
            accountRepository.delete(accountOptional.get());
        } else {
            throw new ResourceNotFoundException("Account not found by: ".concat(username));
        }
    }

    public AccountDTO updateAccount(AccountDTO accountDTO) {
        Optional<Account> accountOptional = accountRepository.findByUsername(accountDTO.getUsername());
        if (accountOptional.isPresent()) {
            accountDTO.setFirstname(accountDTO.getFirstname());
            accountDTO.setLastname(accountDTO.getLastname());
            return accountMapper.toDto(accountRepository.save(accountMapper.toEntity(accountDTO)));
        } else {
            throw new ResourceNotFoundException("Account not found by: ".concat(accountDTO.getUsername()));
        }
    }
}
