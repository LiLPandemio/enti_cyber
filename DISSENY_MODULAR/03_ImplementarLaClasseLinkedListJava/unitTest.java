import linkedlist.MyLinkedList;

public class UnitTest {
    public static void main(String[] args) {
        testAddFirst();
        testAddLast();
        testGet();
        testSet();
        testRemove();
        testPopFirst();
        testPopLast();
    }

    public static void testAddFirst() {
        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        // Agregar 100 valores diferentes al principio
        for (int i = 0; i < 100; i++) {
            linkedList.addFirst(i);
        }
        // Comprobar el resultado esperado
        System.out.println("Lista después de agregar elementos al principio: " + linkedList.toString());
    }

    public static void testAddLast() {
        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        // Agregar 100 valores diferentes al final
        for (int i = 0; i < 100; i++) {
            linkedList.addLast(i);
        }
        // Comprobar el resultado esperado
        System.out.println("Lista después de agregar elementos al final: " + linkedList.toString());
    }

    public static void testGet() {
        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        // Agregar valores del 1 al 100
        for (int i = 1; i <= 100; i++) {
            linkedList.addLast(i);
        }
        // Obtener elementos en varios índices
        System.out.println("Elemento en la posición 0: " + linkedList.get(0));
        System.out.println("Elemento en la posición 50: " + linkedList.get(50));
        System.out.println("Elemento en la posición 99: " + linkedList.get(99));
    }

    public static void testSet() {
        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        // Agregar valores del 1 al 100
        for (int i = 1; i <= 100; i++) {
            linkedList.addLast(i);
        }
        // Establecer elementos en varios índices
        linkedList.set(0, 1000);
        linkedList.set(50, 2000);
        linkedList.set(99, 3000);
        // Comprobar el resultado esperado
        System.out.println("Lista después de establecer elementos: " + linkedList.toString());
    }

    public static void testRemove() {
        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        // Agregar valores del 1 al 100
        for (int i = 1; i <= 100; i++) {
            linkedList.addLast(i);
        }
        // Eliminar elementos en varios índices
        linkedList.remove(0);
        linkedList.remove(50);
        linkedList.remove(99);
        // Comprobar el resultado esperado
        System.out.println("Lista después de eliminar elementos: " + linkedList.toString());
    }

    public static void testPopFirst() {
        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        // Agregar valores del 1 al 100
        for (int i = 1; i <= 100; i++) {
            linkedList.addLast(i);
        }
        // Sacar el primer elemento repetidamente hasta que la lista esté vacía
        while (!linkedList.isEmpty()) {
            Integer element = linkedList.popFirst();
            System.out.println("Elemento sacado: " + element);
        }
    }

    public static void testPopLast() {
        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        // Agregar valores del 1 al 100
        for (int i = 1; i <= 100; i++) {
            linkedList.addLast(i);
        }
        // Sacar el último elemento repetidamente hasta que la lista esté vacía
        while (!linkedList.isEmpty()) {
            Integer element = linkedList.popLast();
            System.out.println("Elemento sacado: " + element);
        }
    }
}
