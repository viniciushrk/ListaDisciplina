public class No<T> {


    private T dado;
    private No<T> proximo; 

    public No(T dado, No<T> proximo) {
        this.dado = dado;
        this.proximo = proximo;
    }
    
    public No(T dado) {
        this.dado = dado;
    }

    public T getDado() {
        return dado;
    }

    public void setDado(T value) {
        dado = value;
    }

    public No<T> getProximo() {
        return proximo;
    }

    public void setProximo(No<T> value) {
        proximo = value;
    }
}
