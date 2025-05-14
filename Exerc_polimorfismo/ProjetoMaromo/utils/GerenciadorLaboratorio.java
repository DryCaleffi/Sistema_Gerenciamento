package utils;

import model.equipamentos.EquipamentoAnalisador;
import java.util.ArrayList;
import java.util.List;



public class GerenciadorLaboratorio {
    public static List<EquipamentoAnalisador> equipamentosEmUso = new ArrayList<>();

    /**
     * Adiciona um equipamento à lista de equipamentos em uso
     * @param equipamento Equipamento a ser adicionado
     */
    public static void adicionarEquipamento(EquipamentoAnalisador equipamento) {
        equipamentosEmUso.add(equipamento);
        System.out.println("Equipamento " + equipamento.getNomeEquipamento() + " adicionado ao gerenciador.");
    }

    /**
     * Remove um equipamento da lista de equipamentos em uso
     * @param equipamento Equipamento a ser removido
     */
    public static void removerEquipamento(EquipamentoAnalisador equipamento) {
        if (equipamentosEmUso.contains(equipamento)) {
            equipamentosEmUso.remove(equipamento);
            System.out.println("Equipamento " + equipamento.getNomeEquipamento() + " removido do gerenciador.");
        } else {
            System.out.println("Equipamento " + equipamento.getNomeEquipamento() + " não encontrado no gerenciador.");
        }
    }

    /**
     * Exibe o status geral de todos os equipamentos em uso
     */
    public static void exibirStatusGeral() {
        System.out.println("\n===== STATUS GERAL DOS EQUIPAMENTOS =====");
        if (equipamentosEmUso.isEmpty()) {
            System.out.println("Nenhum equipamento cadastrado no gerenciador.");
        } else {
            for (EquipamentoAnalisador equipamento : equipamentosEmUso) {
                System.out.println(equipamento.getNomeEquipamento() + " - Status: " + equipamento.getStatus());
            }
        }
        System.out.println("=========================================\n");
    }
}