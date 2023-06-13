# Documentación del Código

En este documento se proporciona una explicación detallada del código y sus componentes principales.

## Método `main()`

El método `main()` es el punto de entrada del programa. Realiza las siguientes acciones:

1. Obtiene los boards de 4chan de forma asíncrona mediante la función `get_4chan_boards_async()`.
2. Crea una instancia de la clase `MainScreen` pasando los boards obtenidos como argumento.
3. Ejecuta la aplicación llamando al método `run()` de la instancia de `MainScreen`.

Archivo: [4CHAN.PY/main.py](https://github.com/lilpandemio/enti_cyber/blob/main/4CHAN.PY/main.py)

## Clase `App`

La clase `App` es una clase base proporcionada por la biblioteca `textual` que representa una aplicación de interfaz de usuario. Sus características principales son:

- `CSS_PATH`: Constante que indica la ruta al archivo CSS de estilo para la aplicación.

El método `compose()` es responsable de componer la interfaz de usuario de la aplicación y se debe implementar en las subclases. Otros métodos como `on_button_pressed()` se utilizan para manejar eventos específicos de la aplicación.

Archivo: [4CHAN.PY/app.py](https://github.com/lilpandemio/enti_cyber/blob/main/4CHAN.PY/app.py)

## Clase `BoardScreen`

La clase `BoardScreen` es una subclase de `Screen` y representa la pantalla de un board en la aplicación. Sus características principales son:

- `CSS_PATH`: Constante que indica la ruta al archivo CSS de estilo para la pantalla del board.
- `board`: Atributo que almacena el nombre del board actual.
- `threads`: Atributo que almacena los hilos del board obtenidos mediante la función `get_board_threads()`.

El método `compose()` se encarga de componer la interfaz de usuario de la pantalla del board. El método `on_mount()` se ejecuta al montar la pantalla y se utiliza para obtener los hilos del board. Otros métodos como `on_data_table_row_selected()` y `on_button_pressed()` se utilizan para manejar eventos específicos de la pantalla del board.

Archivo: [4CHAN.PY/boards.py](https://github.com/lilpandemio/enti_cyber/blob/main/4CHAN.PY/boards.py)

## Clase `ThreadScreen`

La clase `ThreadScreen` es una subclase de `Screen` y representa la pantalla de un hilo en la aplicación. Sus características principales son:

- `CSS_PATH`: Constante que indica la ruta al archivo CSS de estilo para la pantalla del hilo.
- `thread`: Atributo que almacena el identificador del hilo actual.
- `messages`: Atributo que almacena los mensajes del hilo obtenidos mediante la función `get_thread_by_id()`.

El método `compose()` se encarga de componer la interfaz de usuario de la pantalla del hilo. El método `on_mount()` se ejecuta al montar la pantalla y se utiliza para obtener los mensajes del hilo. El método `on_button_pressed()` se utiliza para manejar eventos específicos de la pantalla del hilo.

Archivo: [4CHAN.PY/thread.py](https://github.com/lilpandemio/enti_cyber/blob/main/4CHAN.PY/thread.py)

## Archivo `API`

El archivo `api.py` contiene funciones relacionadas con la API de 4chan. Sus características principales son:

- `get_4chan_boards_async()`: Función asincrónica que obtiene los boards de 4chan de forma asíncrona mediante la función `get_4chan_boards()`.

- `get_thread_by_id(thread_id)`: Función asincrónica que obtiene un hilo por su identificador utilizando la función `get_thread_by_id()`.

Archivo: [4CHAN.PY/api.py](https://github.com/lilpandemio/enti_cyber/blob/main/4CHAN.PY/api.py)
