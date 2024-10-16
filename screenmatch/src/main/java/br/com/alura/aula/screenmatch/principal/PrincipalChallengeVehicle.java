package br.com.alura.aula.screenmatch.principal;

import br.com.alura.aula.screenmatch.models.Veiculo.*;
import br.com.alura.aula.screenmatch.service.ConverteDados;
import br.com.alura.aula.screenmatch.service.RequestApi;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class PrincipalChallengeVehicle {
    Scanner scan = new Scanner(System.in);
    Veiculo veiculo = new Veiculo();
    RequestApi request = new RequestApi();
    ConverteDados conversor = new ConverteDados();


    public void escolheVeiculo () throws JsonProcessingException {
        System.out.println("Digite o tipo de veiculo que deseja procurar: (motos, carros, caminhoes)");
        veiculo.setTipoVeiculo(scan.nextLine().toLowerCase());
        String jsonMarcas = request.constroiUrlVeiculo(veiculo);
        List<Dados> listaMarcas = conversor.obterLista(jsonMarcas, Dados.class);
        listaMarcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("Digite o código ou techo do nome da marca que deseja procurar:");
        veiculo.setCodMarca(
                retornaCodigo(scan.nextLine(),
                        listaMarcas));
        String jsonModelos = request.constroiUrlMarca(veiculo);
        Modelo listaModelos = conversor.obterDados(jsonModelos, Modelo.class);
        listaModelos.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("Digite o código ou techo do nome do do veiculo que deseja procurar");
        veiculo.setCodModelo(
                retornaCodigo(scan.nextLine(),
                        listaModelos.modelos()));
        String jsonAnos = request.constroiUrlModelo(veiculo);
        List<DadosAnos> listaAnos = conversor.obterLista(jsonAnos, DadosAnos.class);
        List<VeiculoSelecionado> veiculos = new ArrayList<>();

        for (DadosAnos listaAno : listaAnos) {
            String jsonAno = request.constroiUrlAnos(veiculo, listaAno.codigo());
            veiculos.add(conversor.obterDados(jsonAno, VeiculoSelecionado.class));
        }

        veiculos.forEach(System.out::println);
    }

    private int retornaCodigo(String entradaUsuario, List<Dados> listDados) {
        try {
            return Integer.parseInt(entradaUsuario);
        } catch (NumberFormatException e) {
            var dadosFiltrados = listDados.stream()
                    .filter(dados -> dados.nome().toLowerCase().contains(entradaUsuario.toLowerCase()))
                    .toList();

            if (dadosFiltrados.isEmpty()) {
                System.out.println("lista vazia, tente novamente");
                String codigo = scan.nextLine();
                return retornaCodigo(codigo, listDados);
            } else if (dadosFiltrados.size() == 1) {
                return dadosFiltrados.getFirst().codigo();
            } else {
                dadosFiltrados.forEach(System.out::println);
                System.out.println("Selecione o código ou trecho de nome");
                String codigo = scan.nextLine();
                return retornaCodigo(codigo, dadosFiltrados);
            }
        }
    }
}
