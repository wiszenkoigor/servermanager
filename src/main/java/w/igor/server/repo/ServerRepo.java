package w.igor.server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import w.igor.server.model.Server;

public interface ServerRepo extends JpaRepository<Server, Long> {
    Server findByIpAddress(String ipAddress);
}
