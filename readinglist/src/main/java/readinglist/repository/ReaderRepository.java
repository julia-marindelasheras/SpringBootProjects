package readinglist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import readinglist.entity.Reader;

public interface ReaderRepository extends JpaRepository<Reader, String>{
}
