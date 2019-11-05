package springlocation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springlocation.beans.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long>{}
