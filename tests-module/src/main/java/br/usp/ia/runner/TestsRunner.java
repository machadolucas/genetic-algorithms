package br.usp.ia.runner;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class TestsRunner {

    public void execute() {
        final ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //Configura o pool para processar simultaneamente a mesma quantidade de tarefas que cores disponiveis
        taskExecutor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.initialize();

        //TODO obter a lista com todos os caminhos dos arquivos de teste.
        final List<String> testPaths = new LinkedList<>();

        testPaths.forEach(path -> {
            final TestTask task = new TestTask(path);
            taskExecutor.execute(task);
        });
    }
}
