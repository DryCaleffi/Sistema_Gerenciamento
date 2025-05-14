package model.equipamentos;

import java.util.Random;

/**
 * Implementação de um Microscópio Eletrônico
 */
public class MicroscopioEletronico implements EquipamentoAnalisador {
    private String nomeEquipamento;
    private String status;
    private boolean ligado;
    private Random random;

    /**
     * Construtor que define o nome do equipamento
     */
    public MicroscopioEletronico() {
        this.nomeEquipamento = "Microscópio Eletrônico de Alta Resolução";
        this.status = "Desligado";
        this.ligado = false;
        this.random = new Random();
    }

    @Override
    public void ligar() {
        if (!ligado) {
            this.ligado = true;
            this.status = "Ligado";
            System.out.println(this.nomeEquipamento + " ligado.");
        } else {
            System.out.println(this.nomeEquipamento + " já está ligado.");
        }
    }

    @Override
    public void desligar() {
        if (ligado) {
            this.ligado = false;
            this.status = "Desligado";
            System.out.println(this.nomeEquipamento + " desligado.");
        } else {
            System.out.println(this.nomeEquipamento + " já está desligado.");
        }
    }

    @Override
    public boolean calibrar() {
        if (!ligado) {
            System.out.println("Erro: " + this.nomeEquipamento + " precisa estar ligado para calibrar.");
            return false;
        }

        // 10% de chance de falha na calibração
        boolean calibracaoBemSucedida = random.nextInt(10) != 0;
        if (calibracaoBemSucedida) {
            System.out.println("Calibração do " + this.nomeEquipamento + " realizada com sucesso.");
            this.status = "Calibrado";
        } else {
            System.out.println("Falha na calibração do " + this.nomeEquipamento + ".");
            this.status = "Requer Calibração";
        }
        return calibracaoBemSucedida;
    }

    @Override
    public String executarAnalise(String amostraId) {
        if (!ligado) {
            return "Erro: " + this.nomeEquipamento + " precisa estar ligado para realizar análise.";
        }

        System.out.println("Iniciando análise da amostra " + amostraId + " no " + this.nomeEquipamento);
        this.status = "Em Análise";

        // Simula processamento
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String resultado = "Resultado da análise da amostra " + amostraId + " no " + this.nomeEquipamento + ": ";
        resultado += "Estrutura molecular identificada com resolução de 0.1nm. Detectados " +
                (random.nextInt(10) + 1) + " componentes celulares anômalos.";

        this.status = "Pronto";
        return resultado;
    }

    @Override
    public String getNomeEquipamento() {
        return this.nomeEquipamento;
    }

    @Override
    public String getStatus() {
        return this.status;
    }
}
