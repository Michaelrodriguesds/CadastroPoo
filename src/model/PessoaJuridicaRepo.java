import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PessoaJuridicaRepo implements Repositorio<PessoaJuridica> {
    private List<PessoaJuridica> pessoasJuridicas = new ArrayList<>();

    @Override
    public void inserir(PessoaJuridica pessoa) {
        pessoasJuridicas.add(pessoa);
    }

    @Override
    public void alterar(PessoaJuridica pessoa) {
        for (int i = 0; i < pessoasJuridicas.size(); i++) {
            PessoaJuridica p = pessoasJuridicas.get(i);
            if (p.getId() == pessoa.getId()) {
                pessoasJuridicas.set(i, pessoa);
                break;
            }
        }
    }

    @Override
    public void excluir(int id) {
        pessoasJuridicas.removeIf(p -> p.getId() == id);
    }

    @Override
    public PessoaJuridica obter(int id) {
        for (PessoaJuridica pessoa : pessoasJuridicas) {
            if (pessoa.getId() == id) {
                return pessoa;
            }
        }
        return null;
    }

    @Override
    public List<PessoaJuridica> obterTodos() {
        return pessoasJuridicas != null ? new ArrayList<>(pessoasJuridicas) : Collections.emptyList();
    }

    @Override
    public void persistir(String arquivo) throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(pessoasJuridicas);
        }
    }

    @Override
    public void recuperar(String arquivo) throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                pessoasJuridicas = (List<PessoaJuridica>) obj;
            } else {
                throw new IOException("Conteúdo do arquivo não é uma lista de PessoaJuridica");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            throw new IOException("Erro ao recuperar dados: " + e.getMessage());
        }
    }
}
