package app.shopping.config.impl;

import app.shopping.config.ApplicationContextProvider;
import app.shopping.entity.Account;
import app.shopping.repository.AccountRepository;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        AccountRepository accountRepository = ApplicationContextProvider.getApplicationContext().getBean(AccountRepository.class);
        Optional<Account> currentUserOptional = accountRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return currentUserOptional.map(account -> Optional.of(account.getUsername())).orElseGet(() -> Optional.of("anonymous"));
    }
}
