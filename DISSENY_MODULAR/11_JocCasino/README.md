# Casino

El código del casino es una implementación básica de un juego de ruleta en Python. A continuación se explica cómo funciona el código:

## Clase `Casino`

La clase `Casino` representa el casino en sí y contiene los siguientes métodos:

- `__init__(self)`: El constructor de la clase inicializa los atributos necesarios, como la lista de jugadores y el saldo del casino.

- `agregar_jugador(self, jugador)`: Este método permite agregar un jugador a la lista de jugadores del casino.

- `girar_ruleta(self)`: Este método simula el giro de la ruleta y devuelve un número aleatorio entre 0 y 36.

- `pago_apuesta(self, jugador, cantidad)`: Este método realiza el pago de una apuesta ganadora a un jugador.

- `cobro_apuesta(self, jugador, cantidad)`: Este método cobra una apuesta perdida a un jugador.

## Clase `Jugador`

La clase `Jugador` representa a un jugador y contiene los siguientes métodos:

- `__init__(self, nombre, saldo_inicial)`: El constructor de la clase inicializa los atributos necesarios, como el nombre del jugador y su saldo inicial.

- `realizar_apuesta(self, cantidad)`: Este método permite al jugador realizar una apuesta.

- `ganar_apuesta(self, cantidad)`: Este método se llama cuando el jugador gana una apuesta y aumenta su saldo.

- `perder_apuesta(self, cantidad)`: Este método se llama cuando el jugador pierde una apuesta y disminuye su saldo.

## Uso del código

1. Se crea una instancia de la clase `Casino`.
2. Se agregan jugadores al casino usando el método `agregar_jugador`.
3. Se inicia el juego llamando al método `girar_ruleta` para obtener un número ganador.
4. Se realizan apuestas por parte de los jugadores usando el método `realizar_apuesta`.
5. El casino realiza los pagos y cobros correspondientes llamando a los métodos `pago_apuesta` y `cobro_apuesta`.

¡Esto es básicamente cómo funciona el código del casino en Python! Ten en cuenta que esta es solo una explicación general y puede haber más detalles en el código real, pero esto debería darte una idea de su estructura y funcionamiento.
