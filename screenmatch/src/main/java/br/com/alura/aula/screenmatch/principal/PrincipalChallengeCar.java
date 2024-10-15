package br.com.alura.aula.screenmatch.principal;

import br.com.alura.aula.screenmatch.models.Veiculo.*;
import br.com.alura.aula.screenmatch.service.ConverteDados;
import br.com.alura.aula.screenmatch.service.RequestApi;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class PrincipalChallengeCar {
    Scanner scan = new Scanner(System.in);
    Veiculo veiculo = new Veiculo();
    RequestApi request = new RequestApi();
    ConverteDados conversor = new ConverteDados();


    public void escolheVeiculo () throws JsonProcessingException {
        System.out.println("Digite o tipo de veiculo que deseja procurar: (motos, carros, caminhões)");
        veiculo.setTipoVeiculo(scan.nextLine().toLowerCase());
        String urlVeiculo = request.constroiUrlVeiculo(veiculo);
        String jsonMarcas = request.obterDados(urlVeiculo);
        List<Dados> listaMarcas = conversor.obterLista(jsonMarcas, Dados.class);
        listaMarcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("Digite o código ou nome da marca que deseja procurar:");
        String leitor = scan.nextLine();
        try {
            veiculo.setCodMarca(Integer.parseInt(leitor));
        } catch (NumberFormatException e) {
            var marcasFiltradas = listaMarcas.stream()
                    .filter(dados -> dados.nome().toLowerCase().contains(leitor.toLowerCase()))
                    .toList();

            if (marcasFiltradas.isEmpty()) {
                System.out.println("lista vazia programa finalizado");
                return;
            } else if (marcasFiltradas.size() == 1) {
                veiculo.setCodMarca(marcasFiltradas.getFirst().codigo());
            } else {
                marcasFiltradas.forEach(System.out::println);
                System.out.println("Selecione o código da marca");
                veiculo.setCodMarca(scan.nextInt());
                scan.nextLine();
            }
        }
        String urlMarca = request.constroiUrlMarca(veiculo);
        String jsonModelos = request.obterDados(urlMarca);
        System.out.println(jsonModelos);
        Modelo listaModelos = conversor.obterDados(jsonModelos, Modelo.class);
        listaModelos.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("Digite um trecho do nome do veiculo que deseja procurar");
        String leitorVeiculo = scan.nextLine();
        var veiculosFiltrado = listaModelos.modelos()
                .stream()
                .filter(dados -> dados.nome().toLowerCase().contains(leitorVeiculo.toLowerCase()))
                .toList();

        if (veiculosFiltrado.isEmpty()) {
            System.out.println("lista vazia programa finalizado");
            return;
        } else if (veiculosFiltrado.size() == 1) {
            veiculo.setCodModelo(veiculosFiltrado.getFirst().codigo());
        } else {
            veiculosFiltrado.forEach(System.out::println);
            System.out.println("Selecione o veiculo com o código do veiculo");
            veiculo.setCodModelo(scan.nextInt());
            scan.nextLine();
        }

        String urlAnos = request.constroiUrlModelo(veiculo);
        String jsonAnos = request.obterDados(urlAnos);
        List<DadosAnos> listaAnos = conversor.obterLista(jsonAnos, DadosAnos.class);
        List<VeiculoSelecionado> veiculos = new ArrayList<>();

        for (int i = 0; i < listaAnos.size(); i++) {
            String urlAno = request.constroiUrlAnos(veiculo, listaAnos.get(i).codigo());
            System.out.println(urlAno);
            String jsonAno = request.obterDados(urlAno);
            System.out.println(jsonAno);
            veiculos.add(conversor.obterDados(jsonAno, VeiculoSelecionado.class));
        }

        veiculos.forEach(System.out::println);
    }
}
