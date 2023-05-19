package org.oga.gestioncollaborator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class GestionCollaboratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionCollaboratorApplication.class, args);
    }

}
