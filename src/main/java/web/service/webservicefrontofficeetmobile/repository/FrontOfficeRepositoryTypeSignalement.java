package web.service.webservicefrontofficeetmobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.service.webservicefrontofficeetmobile.model.TypeSignalement;

@Repository
public interface FrontOfficeRepositoryTypeSignalement extends JpaRepository<TypeSignalement, Long>
{
    
}