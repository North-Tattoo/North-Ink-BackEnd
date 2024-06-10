package school.sptech.northink.projetonorthink.api.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import school.sptech.northink.projetonorthink.domain.entity.Tatuagem;
import school.sptech.northink.projetonorthink.domain.entity.Usuario;

public class Matriz {

    private List<Tatuagem> tatuagens = new ArrayList<>();
    private Queue<Usuario> usuariosAgendar = new LinkedList<>();
    private Stack<Double> pilhaDesfazer = new Stack<>();
    private Stack<Double> pilhaRefazer = new Stack<>();

    private double[][] matrizRelatorio = {{8.0, 7.5, 9.0}, {6.5, 7.0, 8.0}};
    private double[] mediaColuna = new double[matrizRelatorio[0].length];
    private double[] mediaLinha = new double[matrizRelatorio.length];

    public void exibirTatuagens(){
        for (Tatuagem t: tatuagens) {
            System.out.println("""
                    Estilo: %s
                    Preço: %.2f
                    Usuario: %s %s
                    """.formatted(t.getEstilo(), t.getPreco(), t.getFkUsuario().getNome(), t.getFkUsuario().getSobrenome()));
        }
    }

    public void adicionarTatuagem(Tatuagem tatuagem){
        tatuagens.add(tatuagem);
        System.out.println("Tatuagem adicionada");
    }

    public void agendarUsuario(Usuario usuario){
        usuariosAgendar.add(usuario);
        System.out.println("Agendamento realizado");
    }

    public void executarAgendamento(int qtd){
        if(usuariosAgendar.size() >= qtd){
            for(int i = 0; i < qtd; i++){
                Usuario usuario = usuariosAgendar.poll();
                // Adicione a lógica para tratar a execução do agendamento, por exemplo, associar tatuagem ao usuário.
                System.out.println("Agendamento executado para o usuário: " + usuario.getNome());
            }
        } else {
            System.out.println("Não há usuários suficientes para a operação");
        }
    }

    public void ajustarPrecoTatuagem(double ajuste, boolean isIncremento) {
        for (Tatuagem t : tatuagens) {
            double novoPreco = isIncremento ? t.getPreco() + ajuste : t.getPreco() - ajuste;
            t.setPreco(Math.max(novoPreco, 0.0));
            System.out.println("Preço ajustado");
        }
        pilhaDesfazer.push(ajuste);
    }

    public void desfazerAjustePreco(){
        if(!pilhaDesfazer.isEmpty()){
            ajustarPrecoTatuagem(pilhaDesfazer.peek(), false);
            pilhaRefazer.push(pilhaDesfazer.pop());
            System.out.println("Desfazer ajuste de preço");
        }
    }

    public void refazerAjustePreco(){
        if(!pilhaRefazer.isEmpty()){
            ajustarPrecoTatuagem(pilhaRefazer.peek(), true);
            pilhaDesfazer.push(pilhaRefazer.pop());
            System.out.println("Refazer ajuste de preço");
        }
    }

    public int calculaCapacidadeDaMatriz(){
        return matrizRelatorio.length * matrizRelatorio[0].length;
    }

    public Usuario[] vetorDeItensDaFila(int qtd){
        Usuario[] vetor = new Usuario[qtd];
        if (!usuariosAgendar.isEmpty() && usuariosAgendar.size() >= qtd) {
            for (int i = 0; i < qtd; i++) {
                vetor[i] = usuariosAgendar.poll();
            }
        } else {
            System.out.println("Não há usuários suficientes para a operação");
        }
        return vetor;
    }

    public double[] obterMediaLinha() {
        for (int i = 0; i < matrizRelatorio.length; i++) {
            mediaLinha[i] = (matrizRelatorio[i][0] + matrizRelatorio[i][1] + matrizRelatorio[i][2]) / 3;
        }
        return mediaLinha;
    }

    public double[] obterMediaColuna() {
        for (int j = 0; j < matrizRelatorio[0].length; j++) {
            double soma = 0;
            for (int i = 0; i < matrizRelatorio.length; i++) {
                soma += matrizRelatorio[i][j];
            }
            mediaColuna[j] = soma / matrizRelatorio.length;
        }
        return mediaColuna;
    }

    public void exibirRelatorio() {
        mediaLinha = obterMediaLinha();
        mediaColuna = obterMediaColuna();

        System.out.printf("%-12s %-10s %-10s %-10s %-10s\n", "USUARIO", "JANEIRO", "FEVEREIRO", "MARÇO", "MEDIA");
        for (int i = 0; i < matrizRelatorio.length; i++) {
            System.out.printf("%-12s %-10.2f %-10.2f %-10.2f %-10.2f\n", "Usuario" + (i + 1), matrizRelatorio[i][0], matrizRelatorio[i][1], matrizRelatorio[i][2], mediaLinha[i]);
        }
        System.out.printf("%-12s %-10.2f %-10.2f %-10.2f\n", "MEDIA", mediaColuna[0], mediaColuna[1], mediaColuna[2]);
    }

    public void exibirAgendamentos() {
        usuariosAgendar.forEach(usuario -> System.out.println(usuario.getNome()));
    }
}
