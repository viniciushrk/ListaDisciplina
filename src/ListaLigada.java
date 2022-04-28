public class ListaLigada<T> {
    private No<T> primeiro;
    private No<T> ultimo;
    private int tamanho;

    public int getTamanho() {
        return tamanho;
    }

    public No<T> getUltimo() {
        return ultimo;
    }

    public No<T> getPrimeiro() {
        return primeiro;
    }

    public void adicionaComeco(T dado) {
        // criando o nó com o dado, e definindo o proximo elemento (primeiro)
        No<T> no = new No<>(dado, primeiro);

        // o primeiro deixa ser o primeiro, e o nó recem adicionado passa a
        // ser o primeiro
        primeiro = no;

        // quando a lista está vazia (tamanho 0), o último tb é o nó recem
        // adicionado
        if (tamanho == 0) {
            ultimo = primeiro;
        }

        // aumento o tamanho da lista em 1 elemento
        tamanho++;
    }

    public void adicionaFim(T dado) {
        if (tamanho == 0) {
            adicionaComeco(dado);
        } else {
            No<T> no = new No<>(dado);
            ultimo.setProximo(no);
            ultimo = no;
            tamanho++;
        }
    }

    // função limpa lista de nós
    public void clear() {
        primeiro = null;
        ultimo = null;
        tamanho = 0;
    }

    public void adiciona(int posicao, T dado) {
        if (posicao == 0)
            adicionaComeco(dado);
        else if (posicao == tamanho)
            adicionaFim(dado);
        else if (posicao >= 0 && posicao < tamanho) {
            No<T> anterior = getNo(posicao - 1);
            No<T> novo = new No<>(dado, anterior.getProximo());
            anterior.setProximo(novo);
            tamanho++;
        }
    }

    public No<T> getNo(int posicao) {
        if (posicao >= 0 && posicao < tamanho) {
            No<T> atual = primeiro;
            for (int i = 0; i < posicao; i++) {
                atual = atual.getProximo();
            }
            return atual;
        }
        return null;
    }

    public T getDado(int posicao) {
        return getNo(posicao).getDado();
    }

    public String toString() {
        // retorna [] quando a lista está vazia
        if (tamanho == 0)
            return "[]";

        // inicia a construção da saída
        // toda lista começa com [ e termina com ]
        StringBuilder builder = new StringBuilder("[ \n");

        // inicio a percorrer a lista do primeiro elemento
        // o nó atual será usado para percorrer a lsita
        No<T> atual = primeiro;

        // percorrendo a lista
        for (int i = 0; i < tamanho - 1; i++) {
            // adicionando a lista o dado,
            // seguido de vírgula
            builder.append(atual.getDado());
            builder.append(", \n");

            // o nó atual é atualizado com o próximo (lembra que a lista é ligada)
            atual = atual.getProximo();
        }

        // sai da lista, e agora vou escrever o último elemento
        builder.append(atual.getDado());
        // e finalizar a saída com ]
        builder.append("\n]");

        // retorna o texto (string) que representa a lista
        return builder.toString();
    }

    public boolean existe(int posicao) {
        return posicao >= 0 && posicao < tamanho;
    }

    public int contem(T dado) {
        No<T> atual = this.primeiro;
        int posicao = 0;
        while (atual != null) {
            if (atual.getDado().equals(dado)) {
                // se contém, retorna a posição do elemento
                return posicao;
            }
            atual = atual.getProximo();
            posicao++;
        }
        return -1; // não contem o dado na lista dinâmica
    }

    public void excluiComeco() {
        if (tamanho > 0) {
            primeiro = primeiro.getProximo();
            tamanho--;
            if (tamanho == 0) {
                ultimo = null;
            }
        }
    }

    public void excluiFim() {
        if (tamanho > 0) {
            if (tamanho == 1) {
                excluiComeco();
            } else {
                var penultimo = getNo(tamanho - 2);
                penultimo.setProximo(null);
                ultimo = penultimo;
                tamanho--;
            }
        }
    }

    public void exclui(int posicao) {
        if (existe(posicao)) {
            if (posicao == 0) {
                excluiComeco();
            } else if (posicao == tamanho - 1) {
                excluiFim();
            } else {
                var anterior = getNo(posicao - 1);
                anterior.setProximo(anterior.getProximo().getProximo());
                tamanho--;
            }
        }
    }

    // remove item(string nome)
    public boolean removeItem(String nomeremover) {
        No<T> atual = this.primeiro;
        var tamanhoInicial = this.getTamanho();
        int posicao = 0;
        while (atual != null) {
            if (atual.getDado() instanceof Disciplina) {
                Disciplina aux = (Disciplina) atual.getDado();
                if (aux.getNome().equalsIgnoreCase(nomeremover)) {
                    exclui(posicao);   
                }
            }
            atual = atual.getProximo();
            posicao++;
        }
        if(tamanhoInicial == getTamanho()){
            return false;
        }else{
            return true;
        }
    }
}
