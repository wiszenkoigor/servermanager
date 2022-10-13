package w.igor.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import w.igor.server.model.Server;
import w.igor.server.model.enumeration.Status;
import w.igor.server.repo.ServerRepo;

import static w.igor.server.model.enumeration.Status.*;

@SpringBootApplication
public class ServermanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServermanagerApplication.class, args);
    }

    @Bean
    CommandLineRunner run(ServerRepo serverRepo) {
        return args -> {
            serverRepo.save(new Server(
                                        null,
                                        "192.168.1.160",
                                        "Windows 95",
                                        "16 GB",
                                        "Personal Computer",
                                        "http://localhost:8080/server/image/server1.png",
                                        SERVER_UP));

            serverRepo.save(new Server(
                                        null,
                                        "192.168.1.197",
                                        "Ububtu Windows",
                                        "32 GB",
                                        "Web Server",
                                        "http://localhost:8080/server/image/server2.png",
                                        SERVER_DOWN));

            serverRepo.save(new Server(
                                        null,
                                        "192.168.1.27",
                                        "MS 2008",
                                        "64 GB",
                                        "Mail Server",
                                        "http://localhost:8080/server/image/server3.png",
                                        SERVER_UP));

            serverRepo.save(new Server(
                                        null,
                                        "192.168.1.36",
                                        "Windows 98",
                                        "8 GB",
                                        "Laptop",
                                        "http://localhost:8080/server/image/server4.png",
                                        SERVER_DOWN));
        };
    }


}
