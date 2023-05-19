package com.example.Erp;

import com.example.Erp.Commands.Aggregates.ModuleAggregate;
import com.example.Erp.commonApi.commands.ModuleCommand.CreateModuleCommand;
import com.example.Erp.commonApi.enums.moduleStatus;
import com.example.Erp.commonApi.event.ModuleEvent.ModuleCreatedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Test;

import java.util.UUID;

public class ModuleAggregateTest {
    private static final String MODULEID = UUID.randomUUID().toString();
    private static final String MODULETITLE = "test";
    private static final String MODULEDESCRIPTION = "TEST description";

    private  static final String USERID = "1234";
    private  static final String PROJECT = "1234";





    private final FixtureConfiguration<ModuleAggregate> testFixture = new AggregateTestFixture<>(ModuleAggregate.class);

    @Test
    public void testCreteModule() {
        testFixture.givenNoPriorActivity()
                .when(new CreateModuleCommand(MODULEID, MODULETITLE, MODULEDESCRIPTION,USERID,PROJECT))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new ModuleCreatedEvent(MODULEID, MODULETITLE, MODULEDESCRIPTION,USERID,PROJECT,moduleStatus.CREATED));
    }

}
