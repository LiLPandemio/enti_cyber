import linkedlist.MyLinkedList;
import linkedlist.Node;

public class Main {

    public static void main(String[] args) {
        // Creamos una instancia de MyLinkedList
        MyLinkedList<Integer> linkedList = new MyLinkedList<>();

        // Creamos tres nodos
        Node<Integer> node1 = new Node<>(1, null, null);
        Node<Integer> node2 = new Node<>(2, null, null);
        Node<Integer> node3 = new Node<>(3, null, null);

        // Imprimimos las variables y funciones de la instancia de MyLinkedList
        System.out.println("Tamaño de la lista: " + linkedList.size());
        System.out.println("Obtener elemento en la posición 0: " + linkedList.get(0));
        linkedList.set(0, 10);
        System.out.println("Elemento en la posición 0 después de establecerlo: " + linkedList.get(0));
        linkedList.remove(0);
        System.out.println("Tamaño de la lista después de eliminar un elemento: " + linkedList.size());
        linkedList.addFirst(5);
        linkedList.addLast(15);
        System.out.println("Primer elemento de la lista: " + linkedList.popFirst());
        System.out.println("Último elemento de la lista: " + linkedList.popLast());
    }
}
