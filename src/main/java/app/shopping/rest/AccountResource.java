package app.shopping.rest;

import app.shopping.entity.dto.AccountDTO;
import app.shopping.entity.mapper.PaginationResponseMapper;
import app.shopping.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccountResource {

    private final AccountService accountService;
    private final PaginationResponseMapper paginationResponseMapper;

    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable) {
        return new ResponseEntity<>(paginationResponseMapper.toDTO(accountService.findAll(pageable)), HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable String username) {
        return new ResponseEntity<>(accountService.findByUsername(username), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
        return new ResponseEntity<>(accountService.createAccount(accountDTO), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<AccountDTO> updateAccount(@RequestBody AccountDTO accountDTO) {
        return new ResponseEntity<>(accountService.updateAccount(accountDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteAccount(@PathVariable String username) {
        accountService.deleteByUsername(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
