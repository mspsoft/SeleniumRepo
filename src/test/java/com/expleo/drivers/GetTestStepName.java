package com.expleo.drivers;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.EventHandler;
import io.cucumber.plugin.event.TestStepStarted;
import io.cucumber.plugin.event.PickleStepTestStep;

public class GetTestStepName implements ConcurrentEventListener {

    private static ThreadLocal<String> currentStepName = ThreadLocal.withInitial(() -> null);

    private final EventHandler<TestStepStarted> stepHandler = new EventHandler<TestStepStarted>() {
        @Override
        public void receive(TestStepStarted event) {
            handleTestStepStarted(event);
        }
    };

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepStarted.class, stepHandler);
    }

    private void handleTestStepStarted(TestStepStarted event) {
    	  if (event.getTestStep() instanceof PickleStepTestStep) {
              PickleStepTestStep testStep = (PickleStepTestStep) event.getTestStep();
              String stepText = testStep.getStep().getText();
              currentStepName.set(stepText);
              //ExtentManager.logInfo("Step Started --> "+stepText);
          }
    }

    public static String getCurrentStepName() {
        return currentStepName.get();
    }

    public static void clearCurrentStepName() {
        currentStepName.remove();
    }
}

