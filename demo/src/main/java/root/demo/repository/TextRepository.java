package root.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import root.demo.model.Text;

public interface TextRepository extends JpaRepository<Text, Long>{
	Optional<Text> findByNaslov(String naslov);

}
