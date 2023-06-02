# Ruleta del Casino

El código implementa una versión básica de un juego de ruleta en Kotlin. A continuación, se explica cómo funciona el código:

## Clase `Player`

La clase `Player` representa a un jugador y tiene dos atributos:

- `nombre`: El nombre del jugador.
- `credito`: El saldo actual del jugador.

## Clase `Ruleta`

La clase `Ruleta` representa la ruleta del casino y contiene los siguientes métodos:

- `spin(bets: Array<Pair<Int, Double>>): Double`: Este método realiza el giro de la ruleta y calcula las recompensas para las apuestas realizadas. Toma un arreglo de apuestas (`bets`) como argumento y devuelve la cantidad total ganada por el jugador.

- `calcularRecompensa(numero: Int, bets: Array<Pair<Int, Double>>): Double`: Este método calcula la recompensa para cada apuesta según el número ganador y las reglas de la ruleta.

- `lastnums`: Una lista que guarda los últimos números premiados en la ruleta.

## Funciones auxiliares

El código también incluye algunas funciones auxiliares:

- `main()`: La función principal del programa. Crea una instancia de la clase `Ruleta`, inicializa un jugador y solicita las apuestas mientras el jugador tenga crédito disponible.

- `obtenerApuestas(jugador: Player): Array<Pair<Int, Double>>`: Esta función muestra un menú de apuestas y permite al jugador realizar apuestas ingresando las opciones y cantidades correspondientes.

- `obtenerCantidadApostada(jugador: Player): Double`: Esta función solicita al jugador ingresar la cantidad que desea apostar y verifica que sea válida.

- `clear()`: Esta función se utiliza para limpiar la pantalla en el entorno de la línea de comandos.

## Uso del código

1. Se crea una instancia de la clase `Ruleta`.
2. Se inicializa un jugador con un nombre y un saldo inicial.
3. Mientras el jugador tenga crédito disponible, se solicitan las apuestas utilizando la función `obtenerApuestas`.
4. Las apuestas se pasan al método `spin` de la ruleta para realizar el giro y calcular las recompensas.
5. Las ganancias se suman al saldo del jugador y se muestra el crédito actual.
6. El proceso se repite hasta que el jugador se quede sin crédito.

## Notas adicionales

- El código utiliza la clase `Random` de Kotlin para generar números aleatorios y la función `Thread.sleep` para simular una animación en el giro de la ruleta.
- El código está diseñado para ejecutarse en un entorno de línea de comandos y utiliza la función `clear()` para limpiar la pantalla. Puede requerir ajustes para ejecutarse en otros entornos.
- Las reglas de las apuestas y las recompensas se implementan dentro del método `calcularRecompensa`. Cada número o rango de números tiene una lógica específica para determinar si una apuesta es ganadora.

Esta es una explicación general del código proporcionado. Puedes ajustarlo y expandirlo según tus necesidades. ¡Diviértete jugando a la ruleta!
