1. Descarrega els fitxers**MyLinkedList.java** i **Node.java**i explica per a què serveix cada **variable** i cada **funció** que té la classe. Informa’t com funcionen els tipus genèrics.

---

- `ramMemory`: Es un ArrayList que almacena nodos de tipo `Node<T>`. Se utiliza para almacenar los elementos de la lista en memoria.

Funciones comunes:

- `size()`: Devuelve el tamaño de la colección, es decir, el número de elementos que contiene.
- `get(Integer index)`: Devuelve el valor en el índice especificado de la colección. Lanza una excepción `IndexOutOfBoundsException` si el índice está fuera de rango.
- `set(Integer index, T element)`: Establece el elemento en el índice especificado de la colección. Lanza una excepción `IndexOutOfBoundsException` si el índice está fuera de rango.
- `remove(Integer index)`: Elimina el elemento en el índice especificado de la colección. Lanza una excepción `IndexOutOfBoundsException` si el índice está fuera de rango.

Pilas y colas:

- `addFirst(T element)`: Agrega el elemento en la primera posición de la colección.
- `addLast(T element)`: Agrega el elemento en la última posición de la colección.
- `popFirst()`: Obtiene y elimina el elemento en la primera posición de la colección.
- `popLast()`: Obtiene y elimina el elemento en la última posición de la colección.
  En ambos archivos, la implementación de las funciones se lanza una excepción `RuntimeException`. Esto probablemente se hizo para indicar que estas funciones deben ser implementadas en una clase derivada o subclase.

---

1. Crea un nou fitxer **Main.java** i instancia-hi una MyLinkedList i 3 Node. Després, fes_print_de totes les seves variables i funcions.

2. Dissenya en un paper quines **variables i funcions auxiliars** necessitaràs per tal d’implementar aquesta classe.

3. **Implementa totes les funcions** de la classe MyLinkedList.

   1. Implementa les funcions addFirst(), addLast(), get(), set().
   2. Implementa les funcions remove(), popFirst(), popLast().

4. Dissenya els** tests unitaris **en un fitxer anomenat**UnitTest**. Aquesta classe ha de tenir una funció que provi diverses vegades cada una de les funcions públiques que acabes d’implementar.

   1. Prova cada funció almenys amb 100 valors diferents

   2. Prova cada funció en tots els casos extrems

      1. Quan la col·lecció està buida.
      2. Fent servir índexs fora de rang (per davant i per darrere).
      3. Sol·licitant elements que no existeixen.
