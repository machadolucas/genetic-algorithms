package br.usp.ia;

import br.usp.ia.create.TestsCreator;
import br.usp.ia.runner.TestsRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class StartupBean implements CommandLineRunner {

    @Autowired
    private TestsCreator testsCreator;
    @Autowired
    private TestsRunner testsRunner;

    public void run(final String... args) {
        if (args != null && args.length > 0) {
            final List<String> argsList = Arrays.asList(args);
            if (argsList.contains("create")) {
                //Se passar o argumento create, cria testes
                this.testsCreator.execute();

            } else if (argsList.contains("run")) {
                //Se passar o argumento run, executa testes
                this.testsRunner.execute();
            }
        } else {
            System.out.println("Informe 'create' ou 'run'.");
        }
    }

}