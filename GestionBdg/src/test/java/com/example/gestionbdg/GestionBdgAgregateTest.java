package com.example.gestionbdg;

import com.example.gestionbdg.aggregates.GestionBdj;
import com.example.gestionbdg.commonapi.CreateCjmCommand;
import com.example.gestionbdg.enums.GbdgStatus;
import com.example.gestionbdg.events.CjmCreatedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;
@RunWith(SpringJUnit4ClassRunner.class)

@SpringBootTest
public class GestionBdgAgregateTest {


    private String BDGID = UUID.randomUUID().toString();
    private String COLLABORATOR = "User 1";
    private Double TJM = 200.0;
    private Double CJM = 700.0;
    private String TASK = "createbdg";
    private Double DAYNUMBRE = 5.0;
    private String PROJECT = "ERP";
    private Double CHARGESOCIAL = 300.0;
    private Double PRIME = 500.0;


    private final FixtureConfiguration<GestionBdj> testFixture = new AggregateTestFixture<>(GestionBdj.class);

    @Test
    public void testCreteFicheDePaie() {
        testFixture.givenNoPriorActivity()
                .when(new CreateCjmCommand(BDGID, COLLABORATOR,TJM,CJM,TASK,DAYNUMBRE,PROJECT))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new CjmCreatedEvent(BDGID, COLLABORATOR,TJM,CJM,TASK,DAYNUMBRE,PROJECT, GbdgStatus.CREATED));

    }

}