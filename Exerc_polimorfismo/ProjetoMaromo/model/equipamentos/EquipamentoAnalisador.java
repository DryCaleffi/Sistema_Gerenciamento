package model.equipamentos;
/** Interface que define o contrato de todos os equipamento**/

public interface EquipamentoAnalisador {

    void ligar();
    /**
     * Para Ligar o Equipamento
     */
    void desligar();
    /**Para desligar o equipamento*/


    boolean calibrar();
    /**
     * Calibra o equipamento
     * @return true se a calibração foi bem-sucedida, false caso contrário
     */

    String executarAnalise(String amostraId);
    /**
     * Executa a análise em uma amostra identificada
     * @param amostraId identificação da amostra
     * @return resultado ou status da análise
     */

    String getNomeEquipamento();
/**
 * Obtém o nome específico do equipamento
 * @return nome do equipamento
 */

    String getStatus();
}
/**
 * Obtém o status atual do equipamento
 * @return status atual (Ligado, Em Análise, Desligado, Requer Calibração, etc.)
 */