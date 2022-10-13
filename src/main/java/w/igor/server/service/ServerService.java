package w.igor.server.service;

import w.igor.server.model.Server;

import java.util.Collection;

public interface ServerService {
    Server create(Server server);
    Server ping(String server);
    Collection<Server> list(int limit);
    Server get(Long id);
    Server update(Server server);
    Boolean delete(Long id);

}
