package pl.barksville.barksville.spring.model.dal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.barksville.barksville.spring.model.entities.data.RemovedItem;

public interface RemovedItemsRepository extends JpaRepository<RemovedItem,Long> {
}
