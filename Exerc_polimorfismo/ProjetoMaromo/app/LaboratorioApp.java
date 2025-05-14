package app;

import model.equipamentos.*;
import utils.GerenciadorLaboratorio;

/**
 * Classe principal de demonstração do sistema de laboratório
 */
public class LaboratorioApp {
    public static void main(String[] args) {
        System.out.println("----INICIANDO SISTEMA DO LABORATÓRIO----\n");

        // Criando instâncias dos equipamentos, inciando os equipamentos
        MicroscopioEletronico microscopio = new MicroscopioEletronico();
        EspectrometroDeMassa espectrometro = new EspectrometroDeMassa();
        AnalisadorBioquimico analisador = new AnalisadorBioquimico();

        // Adicionando equipamentos ao gerenciador, para ele fazer o gerenciamento
        GerenciadorLaboratorio.adicionarEquipamento(microscopio);
        GerenciadorLaboratorio.adicionarEquipamento(espectrometro);
        GerenciadorLaboratorio.adicionarEquipamento(analisador);

        // Mostrando status inicial dos equipamentos, inciando as operações dos equipamentos
        GerenciadorLaboratorio.exibirStatusGeral();

        System.out.println("=--- INICIANDO OPERAÇÕES DOS EQUIPAMENTOS ----\n");

        // Iterando sobre a lista de equipamentos para demonstrar polimorfismo
        for (EquipamentoAnalisador equipamento : GerenciadorLaboratorio.equipamentosEmUso) {
            System.out.println("\n----- Operando " + equipamento.getNomeEquipamento() + " -----");

            // Ligando o equipamento
            equipamento.ligar();

            // Calibrando o equipamento
            boolean calibrado = equipamento.calibrar();

            // Se calibrado com sucesso, executa análise.
            if (calibrado) {
                // Determina o ID da amostra baseado no tipo de equipamento
                String amostraId;
                if (equipamento instanceof MicroscopioEletronico) {
                    amostraId = "AM-MICRO-001";
                } else if (equipamento instanceof EspectrometroDeMassa) {
                    amostraId = "AM-SPEC-002";
                } else {
                    amostraId = "AM-BIO-003";
                }

                // Executa a análise e imprime o resultado
                String resultadoAnalise = equipamento.executarAnalise(amostraId);
                System.out.println(resultadoAnalise);
            } else {
                System.out.println("Não foi possível executar análise devido a falha na calibração.");
            }

            // Mostra o status do equipamento
            System.out.println("Status atual: " + equipamento.getStatus());
        }

        // Exibindo status após operações
        GerenciadorLaboratorio.exibirStatusGeral();

        // Recarregando reagente do analisador bioquímico (exemplo de método específico)
        System.out.println("-- Operações especificas--\n");
        System.out.println("Recarregando reagente do Analisador Bioquímico:");
        analisador.recarregarReagente();

        // Desligando todos os equipamentos
        System.out.println("\n----FINALIZANDO OPERAÇÕES ----\n");
        for (EquipamentoAnalisador equipamento : GerenciadorLaboratorio.equipamentosEmUso) {
            equipamento.desligar();
        }

        // Status final
        GerenciadorLaboratorio.exibirStatusGeral();

        System.out.println("---- SISTEMA DE LABORATÓRIO ENCERRADO ----");
    }
}