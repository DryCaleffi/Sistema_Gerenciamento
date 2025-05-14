package model.equipamentos;

import java.util.Random;

/**
 * Implementação de um Espectrômetro de Massa
 */
public class EspectrometroDeMassa implements EquipamentoAnalisador {
    private String nomeEquipamento;
    private String status;
    private boolean ligado;
    private Random random;

    /**
     * Construtor que define o nome do equipamento
     */
    public EspectrometroDeMassa() {
        this.nomeEquipamento = "Espectrômetro de Massa QuadruPole Pro";
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

        // 15% de chance de falha na calibração
        boolean calibracaoBemSucedida = random.nextInt(100) >= 15;
        if (calibracaoBemSucedida) {
            System.out.println("Calibração do " + this.nomeEquipamento + " realizada com sucesso.");
            this.status = "Calibrado";
        } else {
            System.out.println("Falha na calibração do " + this.nomeEquipamento + ". Necessário ajustar manualmente.");
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

        System.out.println("Iniciando análise da amostra " + amostraId + " no " + this.nomeEquipamento);
        this.status = "Em Análise";

        // Simula processamento
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String resultado = "Resultado da análise da amostra " + amostraId + " no " + this.nomeEquipamento + ": ";
        resultado += "Detectados compostos moleculares de massa entre " + (random.nextInt(500) + 100) +
                " e " + (random.nextInt(1000) + 600) + " Da. Pureza estimada em " +
                (85 + random.nextInt(15)) + "%.";

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