package web.service.webservicefrontofficeetmobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.service.webservicefrontofficeetmobile.model.Signalement;

@Repository
public interface FrontOfficeRepository extends JpaRepository<Signalement, Long>
{
   
}
