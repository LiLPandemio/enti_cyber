from textual.app import App, ComposeResult
from textual.widgets import Button, Label
import asyncio
from screens.boards import BoardScreen
from api import get_4chan_boards_async

class MainScreen(App):
    CSS_PATH = "main.css"

    def __init__(self, boards):
        super().__init__()
        self.log("HELLO")
        self.boards = boards
        self.buttons = []

        for board in self.boards:
            self.buttons.append(Button(board, id=f"board_{board}"))

    def compose(self) -> ComposeResult:
        yield Label("Bienvenido a 4chan, selecciona un board", id="welcome")

        for button in self.buttons:
            yield button

    def on_button_pressed(self, event):
        if event.button.id.startswith("board_"):
            board = event.button.id.split("_")[1]
            print(f"Botón {board} presionado")
            display_screen = BoardScreen(board)
            display_screen.set_app(self)
            self.push_screen(display_screen)

def main():
    loop = asyncio.get_event_loop()
    boards = loop.run_until_complete(get_4chan_boards_async())

    app = MainScreen(boards=boards)
    app.run()

if __name__ == "__main__":
    main()
