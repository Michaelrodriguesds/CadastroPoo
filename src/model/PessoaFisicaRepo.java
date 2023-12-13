import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PessoaFisicaRepo implements Repositorio<PessoaFisica> {
    private List<PessoaFisica> pessoasFisicas = new ArrayList<>();

    @Override
    public void inserir(PessoaFisica pessoa) {
        pessoasFisicas.add(pessoa);
    }

    @Override
    public void alterar(PessoaFisica pessoa) {
        for (int i = 0; i < pessoasFisicas.size(); i++) {
            PessoaFisica p = pessoasFisicas.get(i);
            if (p.getId() == pessoa.getId()) {
                pessoasFisicas.set(i, pessoa);
                break;
            }
        }
    }

    @Override
    public void excluir(int id) {
        pessoasFisicas.removeIf(p -> p.getId() == id);
    }

    @Override
    public PessoaFisica obter(int id) {
        for (PessoaFisica pessoa : pessoasFisicas) {
            if (pessoa.getId() == id) {
                return pessoa;
            }
        }
        return null;
    }

    @Override
    public List<PessoaFisica> obterTodos() {
        return pessoasFisicas != null ? new ArrayList<>(pessoasFisicas) : Collections.emptyList();
    }

    @Override
    public void persistir(String arquivo) throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(pessoasFisicas);
        }
    }

    @Override
    public void recuperar(String arquivo) throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                pessoasFisicas = (List<PessoaFisica>) obj;
            } else {
                throw new IOException("Conteúdo do arquivo não é uma lista de PessoaFisica");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            throw new IOException("Erro ao recuperar dados: " + e.getMessage());
        }
    }
}
