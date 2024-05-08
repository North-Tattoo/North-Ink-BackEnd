package school.sptech.northink.projetonorthink.api.util;

import lombok.Getter;

@Getter
public class ListaObj <T>{


    private T[] vetor;
    private int nroElem;

    public ListaObj(int tamanho) {
        vetor = (T[]) new Object[tamanho];
        nroElem = 0;
    }

    public void adiciona(T elemento) {
        if (nroElem >= vetor.length) {
            System.out.println("Lista está cheia");
        }
        else {
            vetor[nroElem++] = elemento;
        }
    }

    public void adiciona(int i, T elemento) {
        vetor[i] = elemento;
    }


    public void exibe() {
        if (nroElem == 0) {
            System.out.println("\nA lista está vazia.");
        }
        else {
            System.out.println("\nElementos da lista:");
            for (int i = 0; i < nroElem; i++) {
                System.out.println(vetor[i]);
            }
        }
    }

    public int busca(T elementoBuscado) {
        for (int i = 0; i < nroElem; i++) {
            if (vetor[i].equals(elementoBuscado)) {
                return i;
            }
        }
        return -1;
    }

    public boolean removePeloIndice (int indice) {
        if (indice < 0 || indice >= nroElem) {
            System.out.println("\nIndice invalido!");
            return false;
        }

        for (int i = indice; i < nroElem-1; i++) {
            vetor[i] = vetor[i+1];
        }

        nroElem--;
        return true;
    }

    public boolean removeElemento(T elementoARemover) {
        return removePeloIndice(busca(elementoARemover));
    }

    public int getTamanho() {
        return nroElem;
    }

    public T getElemento(int indice) {
        if (indice < 0 || indice >= nroElem) {
            return null;
        }
        else {
            return vetor[indice];
        }
    }


    public void limpa() {
        nroElem = 0;
    }

    public void ordenaPorNome() {
        for (int i = 0; i < nroElem - 1; i++) {
            int indMenor = i;

            for (int j = i + 1; j < nroElem; j++) {
                if (vetor[j].toString().compareTo(vetor[indMenor].toString()) < 0) {
                    indMenor = j;
                }
            }
            T aux = vetor[i];
            vetor[i] = vetor[indMenor];
            vetor[indMenor] = aux;
        }
    }

    public int buscaBinariaRecursiva(T elementoBuscado) {
        return buscaBinariaRecursiva(elementoBuscado, 0, nroElem - 1);
    }

    private int buscaBinariaRecursiva(T elementoBuscado, int inicio, int fim) {
        if (inicio > fim) {
            return -1; // Elemento não encontrado
        }

        int meio = (inicio + fim) / 2;

        // Se o elemento no meio for igual ao elemento buscado, retornar o índice
        if (vetor[meio].equals(elementoBuscado)) {
            return meio;
        }

        // Se o elemento no meio for maior que o elemento buscado, buscar na metade esquerda
        if (vetor[meio].toString().compareTo(elementoBuscado.toString()) > 0) {
            return buscaBinariaRecursiva(elementoBuscado, inicio, meio - 1);
        }

        // Caso contrário, buscar na metade direita
        return buscaBinariaRecursiva(elementoBuscado, meio + 1, fim);
    }

}
