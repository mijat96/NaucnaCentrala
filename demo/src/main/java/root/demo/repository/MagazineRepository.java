package root.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import root.demo.model.Magazine;

public interface MagazineRepository extends JpaRepository<Magazine, Long>{
	
	Optional<Magazine> findByNaziv(String naziv);

}
