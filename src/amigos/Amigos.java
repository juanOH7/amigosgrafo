/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amigos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.spriteManager.SpriteManager;

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
        Random generator = new Random();
        int idEdg = 0;
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
                graph.addEdge(String.valueOf(idEdg), divCom[0], divCom[1], true);
            }else if ((graph.getNode(divCom[0]) == null)) {
                graph.addNode(divCom[1]);
                graph.addEdge(String.valueOf(idEdg), divCom[0], divCom[1], true);
            }else if ((graph.getNode(divCom[1]) == null)) {
                graph.addNode(divCom[0]);
                graph.addEdge(String.valueOf(idEdg), divCom[0], divCom[1], true);
            }else{
                graph.addEdge(String.valueOf(idEdg), divCom[0], divCom[1], true);
            }
            
 
            idEdg++;
        }
       
        for (Node node : graph) {
            node.addAttribute("ui.label", node.getId());
        }
        graph.display();
    }
    
}
