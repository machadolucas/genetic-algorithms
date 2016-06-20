package br.usp.ia.runner;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

@Component
public class TestsRunner {

    public void execute() {
        final ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //Configura o pool para processar simultaneamente a mesma quantidade de tarefas que cores disponiveis
        taskExecutor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.initialize();

        final List<Path> testPaths = new LinkedList<>();

        try (Stream<Path> filePathStream = Files.walk(Paths.get("tests/"))) {
            filePathStream.forEach(filePath -> {
                if (Files.isRegularFile(filePath) && filePath.endsWith(".vrp")) {
                    testPaths.add(filePath);
                }
            });
        } catch (final IOException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("========================================");
        System.out.println();

        testPaths.forEach(path -> {
            final TestTask task = new TestTask(path);
            taskExecutor.execute(task);
        });

        taskExecutor.shutdown();
    }
}
