package com.example.Erp;
import com.example.Erp.Commands.Aggregates.ProjectAggregate;

import com.example.Erp.commonApi.commands.ProjectCommand.CreateProjectCommand;
import com.example.Erp.commonApi.enums.projectStatus;
import com.example.Erp.commonApi.event.ProjectEvent.ProjectCreatedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Test;
import java.time.LocalDate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
public class ProjectAggregateTest {
    private static final String PROJECTID = UUID.randomUUID().toString();
    private static final String PROJECTTITLE = "test";
    private static final String PROJECTDESCRIPTION = "TEST description";

    private static final List<String> COLLABORATORS = Arrays.asList("Collaborator 1", "Collaborator 2", "Collaborator 3");
    private static final LocalDate STARTDATE = LocalDate.parse("2023-04-05");
    private static final LocalDate ENDDATE = LocalDate.parse("2023-04-05");

    private final FixtureConfiguration<ProjectAggregate> testFixture = new AggregateTestFixture<>(ProjectAggregate.class);

    @Test
    public void testCreteProject() {
        testFixture.givenNoPriorActivity()
                .when(new CreateProjectCommand(PROJECTID, PROJECTTITLE,PROJECTDESCRIPTION,COLLABORATORS,STARTDATE,ENDDATE))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new ProjectCreatedEvent(PROJECTID, PROJECTTITLE,PROJECTDESCRIPTION,COLLABORATORS,STARTDATE,ENDDATE,projectStatus.CREATED));
    }

}
