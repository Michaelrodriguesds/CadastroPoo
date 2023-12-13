import java.util.List;

public interface Repositorio<T> {
    void inserir(T entidade);
    void alterar(T entidade);
    void excluir(int id);
    T obter(int id);
    List<T> obterTodos();
    void persistir(String arquivo) throws Exception;
    void recuperar(String arquivo) throws Exception;
}
