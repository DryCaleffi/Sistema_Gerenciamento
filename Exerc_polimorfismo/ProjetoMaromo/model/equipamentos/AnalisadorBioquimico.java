package model.equipamentos;

import java.util.Random;

/**
 * Implementação de um Analisador Bioquímico
 */
public class AnalisadorBioquimico implements EquipamentoAnalisador {
    private String nomeEquipamento;
    private String status;
    private boolean ligado;
    private Random random;
    private int nivelReagente;

    /**
     * Construtor que define o nome do equipamento
     */
    public AnalisadorBioquimico() {
        this.nomeEquipamento = "Analisador Bioquímico AutoLab 3000";
        this.status = "Desligado";
        this.ligado = false;
        this.random = new Random();
        this.nivelReagente = 100; // Nível inicial de reagente em porcentagem
    }

    @Override
    public void ligar() {
        if (!ligado) {
            this.ligado = true;

            if (nivelReagente < 20) {
                this.status = "Ligado - Reagente Baixo";
                System.out.println(this.nomeEquipamento + " ligado. ATENÇÃO: Nível de reagente baixo (" + nivelReagente + "%).");
            } else {
                this.status = "Ligado";
                System.out.println(this.nomeEquipamento + " ligado. Nível de reagente: " + nivelReagente + "%.");
            }
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

        // 5% de chance de falha na calibração
        boolean calibracaoBemSucedida = random.nextInt(100) >= 5;
        if (calibracaoBemSucedida) {
            System.out.println("Calibração do " + this.nomeEquipamento + " realizada com sucesso.");
            this.status = nivelReagente < 20 ? "Calibrado - Reagente Baixo" : "Calibrado";
        } else {
            System.out.println("Falha na calibração do " + this.nomeEquipamento + ". Verifique sensores.");
            this.status = "Requer Calibração";
        }
        return calibracaoBemSucedida;
    }

    @Override
    public String executarAnalise(String amostraId) {
        if (!ligado) {
            return "Erro: " + this.nomeEquipamento + " precisa estar ligado para realizar análise.";
        }

        if (this.status.equals("Requer Calibração")) {
            return "Erro: " + this.nomeEquipamento + " precisa ser calibrado antes de realizar análise.";
        }

        if (nivelReagente <= 0) {
            return "Erro: " + this.nomeEquipamento + " sem reagente disponível. Recarregue o reagente.";
        }

        System.out.println("Iniciando análise da amostra " + amostraId + " no " + this.nomeEquipamento);
        this.status = "Em Análise";

        // Simula processamento
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Consome reagente a cada análise
        nivelReagente -= random.nextInt(5) + 5;
        if (nivelReagente < 0) nivelReagente = 0;

        String resultado = "Resultado da análise da amostra " + amostraId + " no " + this.nomeEquipamento + ": ";
        resultado += "Níveis de glicose: " + (random.nextInt(100) + 70) + " mg/dL, ";
        resultado += "Colesterol total: " + (random.nextInt(100) + 150) + " mg/dL, ";
        resultado += "Triglicerídeos: " + (random.nextInt(100) + 50) + " mg/dL.";

        if (nivelReagente < 20) {
            this.status = "Pronto - Reagente Baixo";
            System.out.println("ATENÇÃO: Nível de reagente baixo (" + nivelReagente + "%).");
        } else {
            this.status = "Pronto";
        }

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

    /**
     * Método específico do AnalisadorBioquimico para recarregar reagente
     */
    public void recarregarReagente() {
        this.nivelReagente = 100;
        System.out.println("Reagente do " + this.nomeEquipamento + " recarregado para 100%.");
        if (ligado) {
            this.status = "Ligado";
        }
    }
}