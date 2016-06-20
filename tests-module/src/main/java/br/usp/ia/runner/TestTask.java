package br.usp.ia.runner;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

@Data
@AllArgsConstructor
class TestTask implements Runnable {

    private Path testFilePath;

    @Override
    public void run() {

        final String testName = this.testFilePath.getFileName().toString().replace(".vrp", "");
        final String[] command = {"java", "-jar", "-DtestName=" + testName, "genetic-algorithms-2.0.jar" + //
                "--spring.config.name=params"};
        final ProcessBuilder processBuilder = new ProcessBuilder(command);

        try {
            final Process process = processBuilder.start();

            //Espera pra obter o valor de saida
            try {
                final boolean exitValue = process.waitFor(120, TimeUnit.SECONDS);
                if (!exitValue) {
                    System.out.println("java -jar -DtestName=" + testName + //
                            " genetic-algorithms-2.0.jar --spring.config.name=params");
                }
            } catch (final InterruptedException e) {
                e.printStackTrace();
            } finally {
                process.destroyForcibly();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

}
