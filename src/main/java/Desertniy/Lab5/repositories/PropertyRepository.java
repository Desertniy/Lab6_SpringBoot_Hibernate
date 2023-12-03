package Desertniy.Lab5.repositories;

import Desertniy.Lab5.model.PropertyBody;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends CrudRepository<PropertyBody, Integer> {
    PropertyBody findByStreet(String street);
    void deleteByStreet(String street);
}
