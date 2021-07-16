package org.springframework.samples.petclinic.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.User;

public interface UserRepository extends  CrudRepository<User, String>{
	@Query("SELECT aut.authority FROM Authorities aut WHERE aut.user.username LIKE :username")
	public String findAuthoritiesByUsername(@Param("username") String username);

}
