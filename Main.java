import java.util.Scanner;

/**
 * Metodo Main de la Practica05
 * @author Rodrigo Ortega 318036104
 * @version 1.0 Noviembre 2023
 * @since ED2023-1
 */
public class Main{
    public static void main(String args[]){
        BinarySearchTree<Integer, String> tree = new BinarySearchTree<>();
        tree.insert("CINCUENTA", 50);
        tree.insert("OCHENTA", 80);
        tree.insert("TREINTA", 30);
        tree.insert("SESENTA", 60);
        tree.insert("CINCUENTA Y CINCO", 55);
        tree.insert("SESENTA Y CINCO", 65);
        tree.insert("VEINTICINCO", 25);
        tree.insert("TREINTA Y SEIS", 36);

        Scanner scanner  = new Scanner(System.in);
        do {
            System.out.println("------------");
            System.out.println("Arbol actual (inorden):");
            tree.inorden();
            System.out.print("""
                    Menu:   
                    [1]Retrieve
                    [2]Insert
                    [3]Delete
                    [4]Find Min
                    [5]Find Max
                    [6]Is Empty
                    [7]Recorrer
                    [0]Salir
                    """);
            
            int input = scanner.nextInt(); 
            int actionInput;
            switch(input){
                case 1:
                    System.out.println("Da una clave K");
                    actionInput = scanner.nextInt();
                    System.out.println("El objeto con clave K es: " + tree.retrieve(actionInput));
                    break;
                case 2:
                    System.out.println("Da una clave K");
                    actionInput = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Da un elemento");
                    String stringInput = scanner.nextLine();
                    tree.insert(stringInput, actionInput);
                    System.out.println("Se ha agregado al arbol el elemento " + stringInput + " con la clave " + actionInput );
                    break;
                case 3:
                    System.out.println("Da una clave K");
                    actionInput = scanner.nextInt();
                    System.out.println("Se ha elimindado " + tree.remove(actionInput) + " del arbol");
                    break;
                case 4:
                    System.out.println(tree.findMin() + " es el elemento mas pequeno del arbol");
                    break;
                case 5:
                    System.out.println(tree.findMax() + " es el elemento mas grande del arbol");
                    break;
                case 6:
                    if(tree.isEmpty()){
                        System.out.println("El arbol esta vacio");
                    } else {
                        System.out.println("El arbol no esta vacio");
                    }
                    break;
                case 7:
                    System.out.print("""
                        Elige en que orden recorrer el arbol
                        [1]Preorden
                        [2]Inorden
                        [3]Postorden
                        """);
                    actionInput = scanner.nextInt();
                    switch(actionInput){
                        case 1:
                            System.out.println("El arbol en preorden es:");
                            tree.preorden();
                            break;
                        case 2:
                            System.out.println("El arbol en inorden es:");
                            tree.inorden();
                            break;
                        case 3:
                            System.out.println("El arbol en postorden es:");
                            tree.postorden();
                            break;
                        default:
                            System.out.println("Accion invalida");
                            break;
                    }
                    break;
                case 0:
                    scanner.close();
                    return;
                default:
                    System.out.println("Accion invalida");
                    break;
            }
        } while(true);
    }
}