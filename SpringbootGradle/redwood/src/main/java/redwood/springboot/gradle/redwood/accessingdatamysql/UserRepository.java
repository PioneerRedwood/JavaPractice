package redwood.springboot.gradle.redwood.accessingdatamysql;

import org.springframework.data.repository.CrudRepository;
import redwood.springboot.gradle.redwood.accessingdatamysql.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    
}