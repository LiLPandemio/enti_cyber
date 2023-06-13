from textual.screen import Screen
from textual.widgets import Label, Button
from api import get_thread_by_id

class ThreadScreen(Screen):
    CSS_PATH = "main.css"

    def __init__(self, thread):
        super().__init__()
        self.thread = thread
        self._app = None

    def set_app(self, app):
        """
        Establece la referencia de la aplicación principal en la pantalla.

        Argumentos:
        - app: La instancia de la aplicación principal.

        Esta función permite que la pantalla se comunique con la aplicación principal
        y realice acciones como cambiar de pantalla o acceder a métodos y propiedades
        de la aplicación.
        """
        self._app = app

    def compose(self):
        """
        Componer la interfaz de usuario de la pantalla.

        Esta función se llama automáticamente para generar los elementos de la interfaz
        de usuario de la pantalla.

        Retorna:
        - Los elementos que conforman la interfaz de usuario de la pantalla.
        """
        yield Label(f"Watching thread {self.thread}", id="welcome")
        yield Button("Salir", classes="danger", id="close")

    async def on_mount(self):
        """
        Lógica que se ejecuta cuando la pantalla se monta.

        Esta función se llama automáticamente cuando la pantalla se monta en la aplicación.
        Aquí se pueden realizar tareas asíncronas como hacer solicitudes a la API.

        En este caso, se utiliza para obtener los mensajes de un hilo específico de 4chan
        utilizando la función 'get_thread_by_id' de la API.

        Nota: Se asume que la función 'get_thread_by_id' devuelve los mensajes del hilo.

        Utiliza 'await' para esperar la respuesta asincrónica de 'get_thread_by_id'.
        """
        self.messages = await get_thread_by_id(self.thread)
        self.log(self.messages)

    def on_button_pressed(self, event):
        """
        Manejar eventos de botón presionado.

        Argumentos:
        - event: El objeto de evento que contiene información sobre el evento.

        Esta función se llama automáticamente cuando se presiona un botón en la pantalla.
        Permite manejar diferentes acciones en función del botón presionado.

        En este caso, se verifica si se ha presionado el botón "close" y, si es así,
        se llama al método 'dismiss' para cerrar la pantalla.
        """
        self.log(f"event.button.id: {event.button.id}")
        if event.button.id == "close":
            self.dismiss()
