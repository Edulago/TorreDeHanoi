import java.util.Scanner;
import java.util.Random;

public class App {
    private static Torre[] torres;
    private static int NUMERO_DE_DISCOS;
    private static int numeroDeMovimentos; // Adicionamos esta variável

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o número de discos: ");
        NUMERO_DE_DISCOS = scanner.nextInt();

        if (NUMERO_DE_DISCOS < 1) {
            System.out.println("O número de discos deve ser maior ou igual a 1.");
            return;
        }

        torres = new Torre[3];
        inicializarTorres();
        boolean jogando = true;

        while (jogando) {
            exibirMenu();
            int opcao = scanner.nextInt();
            switch (opcao) {
                case 0:
                    jogando = false;
                    break;
                case 1:
                    jogarTorreDeHanoi(scanner);
                    break;
                case 2:
                    numeroDeMovimentos = 0; // Reinicialize o contador
                    resolverTorreDeHanoi(NUMERO_DE_DISCOS, 0, 2, 1);
                    System.out.println("A máquina resolveu a Torre de Hanói em " + numeroDeMovimentos + " movimentos.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }

        System.out.println("Obrigado por jogar!");
        scanner.close();
    }

    public static void exibirMenu() {
        System.out.println("\nMenu:");
        System.out.println("0 - Sair do jogo");
        System.out.println("1 - Jogar (Modo jogador)");
        System.out.println("2 - Resolver (Modo máquina)");
        System.out.print("Escolha uma opção: ");
    }

    public static void inicializarTorres() {
        for (int i = 0; i < 3; i++) {
            torres[i] = new Torre();
        }

        int[] discosAleatorios = gerarDiscosAleatorios(NUMERO_DE_DISCOS);

        for (int i = NUMERO_DE_DISCOS - 1; i >= 0; i--) {
            torres[0].empilharDisco(discosAleatorios[i]);
        }
    }

    public static int[] gerarDiscosAleatorios(int numeroDeDiscos) {
        int[] discos = new int[numeroDeDiscos];
        Random random = new Random();

        for (int i = 0; i < numeroDeDiscos; i++) {
            discos[i] = random.nextInt(100);
        }

        return discos;
    }

    public static void jogarTorreDeHanoi(Scanner scanner) {
        int movimentos = 0;
        while (true) {
            imprimirEstadoDasTorres();
            System.out.print("Digite a torre de origem (0, 1, 2) ou -1 para sair: ");
            int origem = scanner.nextInt();

            if (origem == -1) {
                break;
            }

            System.out.print("Digite a torre de destino (0, 1, 2): ");
            int destino = scanner.nextInt();

            if (movimentoValido(origem, destino)) {
                moverDisco(origem, destino);
                movimentos++;
            } else {
                System.out.println("Movimento inválido. Tente novamente.");
            }
        }
        System.out.println("Você completou o jogo em " + movimentos + " movimentos!");
    }

    public static boolean movimentoValido(int origem, int destino) {
        if (origem < 0 || origem > 2 || destino < 0 || destino > 2) {
            return false;
        }

        if (torres[origem].vazia()) {
            return false;
        }

        if (!torres[destino].vazia() && torres[origem].verTopo() > torres[destino].verTopo()) {
            return false;
        }

        return true;
    }

    public static void moverDisco(int origem, int destino) {
        if (movimentoValido(origem, destino)) {
            int disco = torres[origem].desempilharDisco();
            if (disco != -1) {
                torres[destino].empilharDisco(disco);
            } else {
                System.out.println("Erro: torre de origem vazia.");
            }
        } else {
            System.out.println("Movimento inválido. Tente novamente.");
        }
    }

    public static void imprimirEstadoDasTorres() {
        for (int i = 0; i < 3; i++) {
            System.out.print("Torre " + i + ": ");
            torres[i].imprimirTorre();
        }
        System.out.println();
    }

    public static void resolverTorreDeHanoi(int n, int origem, int destino, int auxiliar) {
        if (n == 1) {
            int discoMovido = torres[origem].desempilharDisco();
            torres[destino].empilharDisco(discoMovido);
            System.out.println("Mover disco " + discoMovido + " da torre " + origem + " para a torre " + destino);
            numeroDeMovimentos++; // Incrementa o contador
        } else {
            resolverTorreDeHanoi(n - 1, origem, auxiliar, destino);
            int discoMovido = torres[origem].desempilharDisco();
            torres[destino].empilharDisco(discoMovido);
            System.out.println("Mover disco " + discoMovido + " da torre " + origem + " para a torre " + destino);
            numeroDeMovimentos++; // Incrementa o contador
            resolverTorreDeHanoi(n - 1, auxiliar, destino, origem);
        }
    }
}
