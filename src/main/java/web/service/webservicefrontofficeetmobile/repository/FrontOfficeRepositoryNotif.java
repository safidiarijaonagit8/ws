package web.service.webservicefrontofficeetmobile.repository;

import web.service.webservicefrontofficeetmobile.model.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrontOfficeRepositoryNotif extends JpaRepository<Notifications, Long>
{
    
}