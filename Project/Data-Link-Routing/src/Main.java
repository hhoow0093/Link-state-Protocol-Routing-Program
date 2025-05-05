import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static class Color {
        public static final String RESET = "\u001B[0m";
        public static final String RED = "\u001B[31m";
        public static final String GREEN = "\u001B[32m";
        public static final String YELLOW = "\u001B[33m";
        public static final String BLUE = "\u001B[34m";
        public static final String CYAN = "\u001B[36m";
        public static final String PURPLE = "\u001B[35m";
        public static final String WHITE_BOLD = "\033[1;37m";
    }
    public static void printHeader(String title) {
        System.out.println(Color.CYAN + "\n╔═════════════════════════════════════╗");
        System.out.printf("║ %-35s ║%n", title);
        System.out.println("╚═════════════════════════════════════╝" + Color.RESET);
    }


    public static class Graph {
        private final int vertices;
        private final List<List<Edge>> adjacencyList;
        private List<String> GraphNodesRepresentation = new ArrayList<>();

        public Graph(int vertices) {
            this.vertices = vertices;
            adjacencyList = new ArrayList<>(vertices);
            for (int i = 0; i < vertices; i++) {
                adjacencyList.add(new ArrayList<>());
            }
        }

        public void printGraphRepresentation() {
            for (int i = 0; i < GraphNodesRepresentation.size(); i++) {
                System.out.println(GraphNodesRepresentation.get(i));
            }

        }

        public void addEdge(int src, int dest, int weight) {
            adjacencyList.get(src).add(new Edge(dest, weight));
            adjacencyList.get(dest).add(new Edge(src, weight));
        
            String edgeInfo = String.format(" Node %d <-> Node %d | Jarak: %d", src, dest, weight);
            GraphNodesRepresentation.add(edgeInfo);
        
        }
        

        public boolean checkEdgeExist(int src, int dest) {
            for (Edge edge : adjacencyList.get(src)) {
                if (edge.dest == dest) {
                    return true;
                }
            }
            for (Edge edge : adjacencyList.get(dest)) {
                if (edge.dest == src) {
                    return true;
                }
            }
            return false;
        }

        public void dijkstra(int source) {
            PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.distance));
            int[] distances = new int[vertices];
            Arrays.fill(distances, Integer.MAX_VALUE);
            distances[source] = 0;

            pq.add(new Node(source, 0));

            while (!pq.isEmpty()) {
                Node current = pq.poll();
                int u = current.node;

                for (Edge edge : adjacencyList.get(u)) {
                    int v = edge.dest;
                    int weight = edge.weight;

                    if (distances[u] + weight < distances[v]) {
                        distances[v] = distances[u] + weight;
                        pq.add(new Node(v, distances[v]));
                    }
                }
            }

            printSolution(distances, source);
        }

        private void printSolution(int[] distances, int source) {
            printHeader("Hasil Dijkstra - Jarak Terpendek");
        
            for (int i = 0; i < vertices; i++) {
                if (distances[i] == Integer.MAX_VALUE) {
                    System.out.println(Color.RED + " Node jaringan " + i + " tidak dapat dicapai dari node " + source + Color.RESET);
                } else {
                    System.out.println(Color.CYAN + String.format(" Node %d -> Jarak terpendek dari Node %d adalah %d", i, source, distances[i]) + Color.RESET);
                }
            }
        }
        

    }

    private static class Edge {
        int dest;
        int weight;

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    private static class Node {
        int node;
        int distance;

        public Node(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static void waitUserInput() {
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void showMenu() {
        System.out.println(Color.CYAN + "╔══════════════════════════════════════════╗");
        System.out.println("║        LINK STATE ROUTING PROGRAM        ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║ 1. Mulai                                 ║");
        System.out.println("║ 2. Keluar                                ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println(Color.RESET + "Dibuat Oleh Howard 99772");
        System.out.print(Color.YELLOW + "Pilihan Anda ->  " + Color.RESET);
    }

    public static void menuNambahnodes() {
        System.out.println(Color.CYAN + "╔══════════════════════════════════════════╗");
        System.out.println("║        LINK STATE ROUTING PROGRAM        ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║ 1. nambah koneksi                        ║");
        System.out.println("║ 2. cek solusi                            ║");
        System.out.println("║ 3. keluar                                ║");
        System.out.println("╚══════════════════════════════════════════╝" + Color.RESET);
    }

    public static void MaunambahNodes(Scanner s, Graph graph, int jumlahJaringan) {
        printHeader("Tambah Koneksi Baru");

        System.out.println("Node yang tersedia : ");
        for (int i = 0; i < jumlahJaringan; i++) {
            System.out.println("   Node " + i);
        }
        System.out.println();
    
        System.out.print(Color.YELLOW + "->  Masukkan Source Node       : " + Color.RESET);
        int source = s.nextInt();
        s.nextLine();
    
        System.out.print(Color.YELLOW + "->  Masukkan Destination Node  : " + Color.RESET);
        int dest = s.nextInt();
        s.nextLine();
    
        System.out.print(Color.YELLOW + "->  Masukkan Jarak (Weight)    : " + Color.RESET);
        int weight = s.nextInt();
        s.nextLine();
    
        boolean exist = graph.checkEdgeExist(source, dest);
        if (exist) {
            System.out.println(Color.RED + "\n Koneksi sudah ada!" + Color.RESET);
        } else {
            graph.addEdge(source, dest, weight);
            System.out.println(Color.GREEN + "\n Koneksi berhasil ditambahkan!" + Color.RESET);
        }
    
        System.out.println(Color.BLUE + "Tekan 'Enter' untuk melanjutkan..." + Color.RESET);
        waitUserInput();
    }
    

    public static void proses_selesai(Scanner s, Graph graph, int jumlahJaringan) {
        printHeader("Proses Perhitungan");
    
        System.out.println(Color.PURPLE + "Daftar Nodes yang Tersedia:" + Color.RESET);
        for (int i = 0; i < jumlahJaringan; i++) {
            System.out.println("   Node " + i);
        }
    
        System.out.print(Color.YELLOW + "\n-> Masukkan Node Awal: " + Color.RESET);
        int source = s.nextInt();
    
        if (source < 0 || source >= jumlahJaringan) {
            System.out.println(Color.RED + "\n  Node tidak valid! Silakan coba lagi." + Color.RESET);
            System.out.println(Color.BLUE + "Tekan 'Enter' untuk melanjutkan..." + Color.RESET);
            waitUserInput();
            clearScreen();
            return;
        }
    
        graph.dijkstra(source);
        System.out.println(Color.GREEN + "\n Proses selesai. Jalur terpendek berhasil dihitung." + Color.RESET);
        System.out.println(Color.BLUE + "Tekan 'Enter' untuk melanjutkan..." + Color.RESET);
        waitUserInput();
        clearScreen();
    }
    

    public static void mulaiProgram(Scanner s) {
        clearScreen();
        int jumlahJaringan;
        System.out.print(Color.RED + "masukkan berapa nodes / jaringan dalam network: " + Color.RESET);
        jumlahJaringan = s.nextInt();
        s.nextLine();
        clearScreen();

        Graph graph = new Graph(jumlahJaringan);

        boolean nambahnodes = true;

        while (nambahnodes) {
            int pilihanNambahNodes;
            menuNambahnodes();
            pilihanNambahNodes = s.nextInt();
            s.nextLine();
            switch (pilihanNambahNodes) {
                case 1:
                    clearScreen();
                    graph.printGraphRepresentation();
                    System.out.println();
                    MaunambahNodes(s, graph, jumlahJaringan);
                    clearScreen();
                    break;
                case 2:
                    clearScreen();
                    proses_selesai(s, graph, jumlahJaringan);
                    break;

                default:
                    nambahnodes = false;
                    clearScreen();
                    break;
            }

        }

    }

    public static void salahinputMulaiAtauKeluar() {
        clearScreen();
        System.out.println("anda salah input!, coba ulang lagi");
        System.out.println("ketik 'enter' untuk melanjutkan program");
        waitUserInput();

    }

    public static void keluar() {
        clearScreen();
        System.out.println("anda telah keluar dari program....");
        System.out.println("ketik 'enter' untuk mengakhiri program");
        waitUserInput();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean programJalan = true;

        while (programJalan) {
            int pilihan;
            showMenu();
            pilihan = scanner.nextInt();
            scanner.nextLine();
            switch (pilihan) {
                case 1:
                    mulaiProgram(scanner);
                    clearScreen();
                    break;

                case 2:
                    keluar();
                    programJalan = false;
                    break;

                default:
                    salahinputMulaiAtauKeluar();
                    clearScreen();
                    break;
            }
        }

        scanner.close();
    }
}
