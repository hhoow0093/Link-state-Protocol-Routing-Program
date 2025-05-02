import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
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
        GraphNodesRepresentation.add(String.format("node %d ke node %d memiliki jarak %d ", src, dest, weight));
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

        printSolution(distances);
        printGraphRepresentation();
    }

    private void printSolution(int[] distances) {
        System.out.println("Shortest distances from the source node:");
        for (int i = 0; i < vertices; i++) {
            if (distances[i] == Integer.MAX_VALUE) {
                System.out.println("Node " + i + " is unreachable.");
            } else {
                System.out.println("Node " + i + " distance: " + distances[i]);
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

    public static void menuNambahnodes() {
        System.out.println("1. nambah koneksi");
        System.out.println("2. lihat semua jaringan koneksi");
        System.out.println("3. Lihat solusi routing protocol link state");
    }


    public static void showMenu() {
        System.out.println("Program Link state routing menggunakan algoritma Djikstra.");
        System.out.println("1. Mulai");
        System.out.println("2. Keluar");
        System.out.println("Dibuat Oleh Howard 99772");
    }

    public static void mulaiProgram(Scanner s) {
        clearScreen();
        int jumlahJaringan;
        System.out.print("masukkan berapa nodes / jaringan dalam network: ");
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
                    
                    break;
                case 2:

                    break;

                case 3:

                    break;
            
                default:
                
                    break;
            }
            
        }

        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 2);

        graph.addEdge(1, 0, 2);
        
        boolean exist = graph.checkEdgeExist(1, 0);
        if (exist == true) {
            System.out.println("edge sudah ada!");
            waitUserInput();
        }

        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 6);
        graph.addEdge(1, 4, 2);

        graph.addEdge(2, 0, 2);
        graph.addEdge(2, 1, 3);
        graph.addEdge(2, 3, 5);
        graph.addEdge(2, 5, 3);

        graph.addEdge(3, 5, 1);
        graph.addEdge(3, 2, 5);
        graph.addEdge(3, 1, 6);
        graph.addEdge(3, 4, 4);

        graph.addEdge(4, 1, 6);
        graph.addEdge(4, 3, 4);

        graph.addEdge(5, 3, 1);
        graph.addEdge(5, 2, 3);

        graph.dijkstra(0);

        System.out.println("ketik 'enter' untuk melanjutkan program");
        waitUserInput();

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
