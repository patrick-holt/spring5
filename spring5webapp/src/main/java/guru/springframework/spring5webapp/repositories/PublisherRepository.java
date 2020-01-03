package guru.springframework.spring5webapp.repositories;

import guru.springframework.spring5webapp.model.Publisher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository //Spring can still handle these with out the annotation, but for clarity they should be there
public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
