import Extras.Stack;
import Extras.TDABinarySearchTree;

/**
 * Clase que implementa un arbol binario
 * @author Rodrigo Ortega
 * @version 1.0 Noviembre 2023
 * @since ED2023-1 
 */
public class BinarySearchTree<K extends Comparable<K>, T> implements TDABinarySearchTree<K,T>{

    /**
     * Clase auxiliar de BinarySearchTree que modela un nodo
     */
    private class Node{
        K key;
        T data;
        Node right;
        Node left;

        public Node(K key, T data){
            this.key = key;
            this.data = data;
        }
    }

    Node root;

    @Override
    public T retrieve(K k) {
        Node current = root;

        while(current.key != k){
            if(k.compareTo(current.key) == -1){
                current = current.left;
            } else {
                current = current.right;
            } 
            if (current == null){
                return null;
            }
        }
        return current.data;
    }

    @Override
    public void insert(T t, K k) {
        Node newNode = new Node(k, t);
        if (root == null){
            root = newNode;
        } else {
            Node current = root;
            Node parent;
            while(true){
                parent = current;
                if(k.compareTo(parent.key) == -1){
                    current = current.left;
                    if (current == null){
                        parent.left = newNode;
                        return;
                    }
                } else {
                    current = current.right;
                    if(current == null){
                        parent.right = newNode;
                        return;
                    }
                }
            }
        }
    }

    @Override
    public T remove(K k) {
        Node current = root;
        Node parent = root;

        boolean isLeftChild = true;

        if (root == null){
            return null;
        }

        while(current.key != k){ //Nos indica si buscar a la izquierda o derecha 
            parent = current;
            if(k.compareTo(current.key) == -1){
                isLeftChild = true;
                current = current.left;

            } else {
                isLeftChild = false;
                current = current.right;
            }
            if(current == null){
                return null;
            }
        }

        if(current.left == null && current.right == null){ //Si el nodo no tiene hijos
            if(current == root){
                root = null;
            } else if (isLeftChild){
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if(current.right == null){ //Si el nodo no tiene hijo derecho
            if(current == root){
                root = current.left;
            } else if(isLeftChild){
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else if(current.left == null){ //Si el nodo no tiene hijo izquierdo
            if(current == root){
                root = current.right;
            } else if (isLeftChild){
                parent.left = current.right;
            } else {
                parent.right = current.left;
            }
        } else { //Si tiene 2 hijos
            Node replacement = getReplacementNode(current);
            if(current == root){
                root = replacement;
            } else if(isLeftChild){
                parent.left = replacement;
            } else {
                parent.right = replacement;
            }
            replacement.left = current.left;
        }
        return current.data;
    }

    /**
     * Método auxiliar de remove que reemplaza a un nodo con sus hijos
     * @param node nodo al que reemplazaremos
     * @return el nodo de reemplazo
     */
    public Node getReplacementNode(Node node){
        Node replacementParent = node;
        Node replacement = node;
        Node current = node.right;
        while(current != null){
            replacementParent = replacement;
            replacement = current;
            current = current.left;
        }
        if(replacement != node.right){
            replacementParent.left = replacement.right;
            replacement.right = node.right; 
        }
        return replacement;
    }


    @Override
    public T findMin() {
        Node current = root;
        while(current.left != null){
            current = current.left;
        }
        return current.data;
    }

    @Override
    public T findMax() {
        Node current = root;
        while(current.right != null){
            current = current.right;
        }
        return current.data;
    }

    /**
     * Muestra si el arbol esta vacio
     * @return true si esta vacio, false si no
     */
    public boolean isEmpty(){
        return root.data == null;
    }

    @Override
    public void preorden() {
        Stack<Node> stack = new Stack<>();
        Node current = root;
        stack.push(root);

        while (current != null && !stack.isEmpty()) {
            current = stack.pop();
            show(current.data);

            if (current.right != null)
                stack.push(current.right);

            if (current.left != null)
                stack.push(current.left);
        }        
    }

    @Override
    public void inorden() {
        Stack<Node> stack = new Stack<>();
        Node current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            Node top = stack.pop();
            show(top.data);
            current = top.right;
        }
    }

    @Override
    public void postorden() {
        Stack<Node> stack = new Stack<>();
        Node prev = root;
        Node current = root;
        stack.push(root);

        while (current != null && !stack.isEmpty()) {
            current = stack.top();
            boolean hasChild = (current.left != null || current.right != null);
            boolean isPrevLastChild = (prev == current.right || (prev == current.left && current.right == null));

            if (!hasChild || isPrevLastChild) {
                current = stack.pop();
                show(current.data);
                prev = current;
            } else {
                if (current.right != null) {
                    stack.push(current.right);
                }
                if (current.left != null) {
                    stack.push(current.left);
                }
            }
        }   

    }

    
    /**
     * Muestra el valor de un nodo
     * @param value el valor de un nodo
     */
    private void show(T value) {
        System.out.print(value + ",\n");        
    }
}
