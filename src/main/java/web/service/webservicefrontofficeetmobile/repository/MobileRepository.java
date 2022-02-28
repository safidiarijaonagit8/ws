package web.service.webservicefrontofficeetmobile.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.service.webservicefrontofficeetmobile.model.Utilisateur;

@Repository
public interface MobileRepository extends JpaRepository<Utilisateur, Long>
{

}