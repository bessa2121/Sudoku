package br.com.dio;

import br.com.dio.model.Board;
import br.com.dio.model.Space;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import static br.com.dio.util.BoardTemplate.BOARD_TEMPLATE;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toMap;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static Board board;

    private static final int BOARD_LIMIT = 9;

    public static void main(String[] args) {

        final var positions = Stream.of(args)
                .collect(toMap(
                        k -> k.split(";")[0],
                        v -> v.split(";")[1]
                ));

        while (true) {
            System.out.println("\nSelecione uma das opções:");
            System.out.println("1 - Iniciar um novo Jogo");
            System.out.println("2 - Colocar um novo número");
            System.out.println("3 - Remover um número");
            System.out.println("4 - Visualizar jogo atual");
            System.out.println("5 - Verificar status do jogo");
            System.out.println("6 - Limpar jogo");
            System.out.println("7 - Finalizar jogo");
            System.out.println("8 - Sair");

            int option = scanner.nextInt();

            switch (option) {
                case 1 -> startGame(positions);
                case 2 -> inputNumber();
                case 3 -> removeNumber();
                case 4 -> showCurrentGame();
                case 5 -> showGameStatus();
                case 6 -> clearGame();
                case 7 -> finishGame();
                case 8 -> System.exit(0);
                default -> System.out.println("Opção inválida");
            }
        }
    }

    private static void startGame(final Map<String, String> positions) {
        if (nonNull(board)) {
            System.out.println("O jogo já foi iniciado");
            return;
        }

        List<List<Space>> spaces = new ArrayList<>();

        for (int i = 0; i < BOARD_LIMIT; i++) {
            spaces.add(new ArrayList<>());
            for (int j = 0; j < BOARD_LIMIT; j++) {
                var positionConfig = positions.get("%s,%s".formatted(i, j));

                var expected = Integer.parseInt(positionConfig.split(",")[0]);
                var fixed = Boolean.parseBoolean(positionConfig.split(",")[1]);

                spaces.get(i).add(new Space(expected, fixed));
            }
        }

        board = new Board(spaces);
        System.out.println("O jogo está pronto para começar");
    }

    private static void inputNumber() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        System.out.println("Informe a coluna (0 a 8):");
        int col = runUntilGetValidNumber(0, 8);

        System.out.println("Informe a linha (0 a 8):");
        int row = runUntilGetValidNumber(0, 8);

        System.out.printf("Informe o número para [%d,%d]:\n", col, row);
        int value = runUntilGetValidNumber(1, 9);

        if (!board.changeValue(col, row, value)) {
            System.out.println("Essa posição é fixa");
        }
    }

    private static void removeNumber() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        System.out.println("Informe a coluna (0 a 8):");
        int col = runUntilGetValidNumber(0, 8);

        System.out.println("Informe a linha (0 a 8):");
        int row = runUntilGetValidNumber(0, 8);

        if (!board.clearValue(col, row)) {
            System.out.println("Essa posição é fixa");
        }
    }

    private static void showCurrentGame() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        var args = new Object[81];
        int pos = 0;

        for (int i = 0; i < BOARD_LIMIT; i++) {
            for (var col : board.getSpaces()) {
                args[pos++] =
                        " " + (isNull(col.get(i).getActual()) ? " " : col.get(i).getActual());
            }
        }

        System.out.println("\nEstado atual do jogo:");
        System.out.printf(BOARD_TEMPLATE + "\n", args);
        System.out.println("Jogadas: " + board.getMoveCount());
        System.out.println("Tempo: " + board.getFormattedTime());
    }

    private static void showGameStatus() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        System.out.println("Status: " + board.getStatus().getLabel());
        System.out.println("Jogadas: " + board.getMoveCount());
        System.out.println("Tempo: " + board.getFormattedTime());

        if (board.hasErrors()) {
            System.out.println("O jogo contém erros");
        } else {
            System.out.println("O jogo não contém erros");
        }
    }

    private static void clearGame() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        System.out.println("Deseja realmente limpar o jogo? (sim/não)");
        String confirm = scanner.next();

        if (confirm.equalsIgnoreCase("sim")) {
            board.reset();
            System.out.println("Jogo reiniciado");
        }
    }

    private static void finishGame() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        if (board.gameIsFinished()) {
            System.out.println("\n🎉 Parabéns! Você concluiu o jogo!");
            System.out.println("Jogadas: " + board.getMoveCount());
            System.out.println("Tempo total: " + board.getFormattedTime());
            showCurrentGame();
            board = null;
        } else if (board.hasErrors()) {
            System.out.println("O jogo contém erros");
        } else {
            System.out.println("Ainda há espaços vazios");
        }
    }

    private static int runUntilGetValidNumber(int min, int max) {
        int value = scanner.nextInt();
        while (value < min || value > max) {
            System.out.printf("Informe um número entre %d e %d:\n", min, max);
            value = scanner.nextInt();
        }
        return value;
    }
}
