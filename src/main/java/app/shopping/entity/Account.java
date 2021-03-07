package app.shopping.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Slf4j
@Entity
@Getter
@Setter
@Table(name = "sp_accounts")
public class Account extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 4811972497832526274L;

	@Column(name = "first_name")
	private String firstname;

	@Column(name = "last_name")
	private String lastname;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;
}
