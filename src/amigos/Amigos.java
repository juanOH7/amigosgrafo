/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amigos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

/**
 *
 * @author Juan O'Hara
 */
public class Amigos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        boolean cont = true, cont2 = true, cont3 = true;
        String resp2;
        int idEdg = 0, opcMe, pos1, pos2;
        Graph graph = new MultiGraph("grafo");
        File arc = new File("amigos.txt");
        Scanner sc = new Scanner(arc);
        Scanner sc2 = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String comp = sc.nextLine();
            String divCom[] = comp.split(",");
            if ((graph.getNode(divCom[0]) == null) && (graph.getNode(divCom[1]) == null)) {
                graph.addNode(divCom[0]);
                graph.addNode(divCom[1]);
                graph.addEdge(String.valueOf(idEdg), divCom[0], divCom[1], true).addAttribute("length", 1);
            } else if ((graph.getNode(divCom[0]) != null) && (graph.getNode(divCom[1]) == null)) {
                graph.addNode(divCom[1]);
                graph.addEdge(String.valueOf(idEdg), divCom[0], divCom[1], true).addAttribute("length", 1);
            } else if ((graph.getNode(divCom[1]) != null) && (graph.getNode(divCom[0]) == null)) {
                graph.addNode(divCom[0]);
                graph.addEdge(String.valueOf(idEdg), divCom[0], divCom[1], true).addAttribute("length", 1);
            } else {
                graph.addEdge(String.valueOf(idEdg), divCom[0], divCom[1], true).addAttribute("length", 1);
            }
            idEdg++;
        }
        for (Node node : graph) {
            node.addAttribute("ui.label", node.getId());
        }

        do {
            System.out.println("1-Ver Grafo\n2-¿Es ___ amigo de ___?\n3-Salir");
            opcMe = sc2.nextInt();
            if (opcMe == 1) {
                graph.display();
            }
            if (opcMe == 2) {
                int pos;
                while (cont2) {
                    pos = 1;
                    for (Node node : graph) {
                        System.out.println(pos + "  " + node.getId());
                        pos++;
                    }
                    System.out.print("Primera Persona: ");
                    pos1 = sc2.nextInt() - 1;
                    System.out.print("Segunda Persona: ");
                    pos2 = sc2.nextInt() - 1;
                    if (graph.getNode(pos1).hasEdgeToward(pos2)) {
                        System.out.println("Si son amigos");
                    } else {
                        Dijkstra path = new Dijkstra(Dijkstra.Element.EDGE, null, "length");
                        path.init(graph);
                        path.setSource(graph.getNode(pos1));
                        path.compute();
                        System.out.println("No son amigos");
                        System.out.println(path.getPath(graph.getNode(pos2)));
                        System.out.println("Pero con este camino lo pueden ser");
                    }
                    System.out.println("Continuar [S/N]");
                    resp2 = sc2.next();
                    cont2 = (resp2.equals("S") || resp2.equals("s"));
                }
            }
            if (opcMe <= 0 || opcMe > 2) {
                cont = false;
            }
        } while (cont);

    }

}
