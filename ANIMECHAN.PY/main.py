# Importar la clase App y ComposeResult de textual.app
from textual.app import App, ComposeResult
# Importar las clases Button y Label de textual.widgets
from textual.widgets import Button, Label
# Importar el módulo asyncio para trabajar con tareas asíncronas
import asyncio
# Importar la clase BoardScreen del módulo screens.boards (Pantalla boards)
from screens.boards import BoardScreen
# Importar la función get_4chan_boards_async del módulo api para obtener boards
from api import get_4chan_boards_async

class MainScreen(App):
    CSS_PATH = "main.css"

    def __init__(self, boards):
        super().__init__()
        self.boards = boards
        self.buttons = []

        # Crear botones para cada board
        for board in self.boards:
            self.buttons.append(Button(board, id=f"board_{board}"))

    def compose(self) -> ComposeResult:
        # Componer la interfaz de usuario
        yield Label("Bienvenido a 4chan, selecciona un board", id="welcome")

        for button in self.buttons:
            yield button

    def on_button_pressed(self, event):
        # Manejar eventos de botón presionado
        if event.button.id.startswith("board_"):
            board = event.button.id.split("_")[1]
            print(f"Botón {board} presionado")
            display_screen = BoardScreen(board)
            display_screen.set_app(self)
            self.push_screen(display_screen)

def main():
    # Obtener los boards de 4chan de forma asíncrona
    loop = asyncio.get_event_loop()
    boards = loop.run_until_complete(get_4chan_boards_async())

    # Crear la instancia de la aplicación y ejecutarla
    app = MainScreen(boards=boards)
    app.run()

if __name__ == "__main__":
    main()
