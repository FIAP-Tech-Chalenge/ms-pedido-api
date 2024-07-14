package com.fiap.mspedidoapi.bdd;

import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;

import java.util.logging.Logger;

public class CucumberTestListener implements TestExecutionListener {
    private static final Logger LOGGER = Logger.getLogger(CucumberTestListener.class.getName());

    @Override
    public void executionStarted(TestIdentifier testIdentifier) {
        LOGGER.info("Test started: " + testIdentifier.getDisplayName());
    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        LOGGER.info("Test finished: " + testIdentifier.getDisplayName() + " - " + testExecutionResult.getStatus());
        if (testExecutionResult.getThrowable().isPresent()) {
            LOGGER.severe("Test failed: " + testExecutionResult.getThrowable().get().getMessage());
        }
    }
}
