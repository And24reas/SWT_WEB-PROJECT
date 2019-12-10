package fotolab.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

import javax.management.relation.Role;

public interface UserRepository extends CrudRepository<User, Long> {

	@Override
	Streamable<User> findAll();

}
