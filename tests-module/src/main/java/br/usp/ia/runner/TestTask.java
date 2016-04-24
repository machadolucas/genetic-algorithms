package br.usp.ia.runner;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;
import java.io.IOException;

@Data
@AllArgsConstructor
class TestTask implements Runnable {

    private String testFilePath;

    @Override
    public void run() {

        final String testName = this.testFilePath.replace(".yml", "");
        final String[] command = {"java", "-jar", "-DtestName=" + testName, "genetic-algorithms-1.0.jar", //
                "--spring.config.location=\"" + this.testFilePath + "\""};
        final ProcessBuilder processBuilder = new ProcessBuilder(command);

        // Ajusta o work directory
        processBuilder.directory(new File("c:\\ia-tests"));

        try {
            final Process process = processBuilder.start();

            //Espera pra obter o valor de saida
            try {
                final int exitValue = process.waitFor();
                if (exitValue != 0) {
                    System.out.println(this.testFilePath + ", resultou em :" + exitValue);
                }
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

}
