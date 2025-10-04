package utils;

import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.Parameter;
import io.qameta.allure.model.StepResult;

import java.util.List;
import java.util.stream.Collectors;

public class Listener implements StepLifecycleListener {

    @Override
    public void beforeStepStart(StepResult result) {
        List<Parameter> maskedParams = result.getParameters().stream()
                .map(param -> {
                    String paramName = param.getName().toLowerCase();
                    if (paramName.contains("password") || paramName.contains("login")) {
                        return new Parameter().setName(param.getName()).setValue("***");
                    }
                    return param;
                })
                .collect(Collectors.toList());

        result.setParameters(maskedParams);
    }
}
