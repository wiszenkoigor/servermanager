package w.igor.server.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import w.igor.server.model.Server;
import w.igor.server.model.enumeration.Status;
import w.igor.server.repo.ServerRepo;
import w.igor.server.service.ServerService;

import javax.transaction.Transactional;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import static java.lang.Boolean.*;
import static java.util.stream.Collectors.toList;
import static org.springframework.data.domain.PageRequest.*;
import static w.igor.server.model.enumeration.Status.SERVER_DOWN;
import static w.igor.server.model.enumeration.Status.SERVER_UP;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImplementation implements ServerService {

    private final ServerRepo serverRepo;

    @Override
    public Server create(Server server) {
        log.info("saving new server: {}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepo.save(server);
    }

    @Override
    public Server ping(String ipAddress) {
        log.info("Pinging server IP: {}", ipAddress);
        Server server = serverRepo.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? SERVER_UP : SERVER_DOWN);
        serverRepo.save(server);
        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching all servers...");
        return serverRepo.findAll(of(0, limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching server by ID: {}", id);
        return serverRepo.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Updating  server: {}", server.getName());
        return serverRepo.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server with ID: {}", id);
        serverRepo.deleteById(id);
        return TRUE ;
    }


    private String setServerImageUrl() {
      String[] imageNames = {"server1.png", "server2.png", "server3.png", "server4.png"};
      return ServletUriComponentsBuilder.fromCurrentContextPath()
              .path("/server/image/" + imageNames[new Random().nextInt(4)]).toUriString();
    }
}
