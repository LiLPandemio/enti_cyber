from textual.app import App, ComposeResult
from textual.screen import Screen  # Agrega esta línea
from textual.widgets import Button, Label, DataTable
import asyncio
import httpx
import random
import re
from api import get_4chan_boards, get_board_threads
import itertools
from bs4 import BeautifulSoup

def clean_html(html):
    soup = BeautifulSoup(html, 'html.parser')
    text = soup.get_text()
    return text

class displayBoardScreen(Screen):
    CSS_PATH = "main.css"

    def __init__(self, board):
        super().__init__()
        self.board = board

    def compose(self) -> ComposeResult:
        yield Label(f"Welcome to {self.board} board!", id="welcome")  # Display the board name
        
        yield DataTable()

        
        
        yield Button("Back", id="goBack", classes="danger")  # Example button widget

    async def on_mount(self) -> None:
        table = self.query_one(DataTable)
        table.overflow_x = 'hidden'
        table.focus()
        table.add_columns("Post id", "Replies", "Poster", "Content")
        threads = await get_board_threads(self.board)
        for thread in itertools.islice(threads, 0, 100):
            table.add_row(thread["no"], thread.get("replies", "0"), f"{thread['name']}", clean_html(thread.get("com", ""))[:120]+"...")
        table.fixed_rows = 2
        table.fixed_columns = 1
        table.cursor_type = "row"
        table.zebra_stripes = True

    def on_button_pressed(self, event):
        # Lógica del botón de salir
        if event.button.id == "goBack":
            self.dismiss()  # Pop screen

class displayThreadScreen(Screen):
    CSS_PATH = "main.css"

    def compose(self) -> ComposeResult:
        self.close_button = Button("Salir", classes="danger", id="close")


class MainScreen(App):
    CSS_PATH = "main.css"

    def __init__(self, boards):
        super().__init__()
        self.boards = boards
        self.buttons = []  # Lista para almacenar los botones

        for board in self.boards:
            self.buttons.append(Button(board, id=f"board_{board}"))

    def compose(self) -> ComposeResult:
        yield Label("Bienvenido a 4chan, selecciona un board", id="welcome")

        for button in self.buttons:  # Iterar sobre la lista de botones
            yield button

    def on_button_pressed(self, event):
        board = event.button.id.split("_")[1]
        print(f"Botón {board} presionado")
        # Abrir displayBoardScreen y pasar el parámetro 'board'
        self.push_screen(displayBoardScreen(board))


async def get_4chan_boards_async():
    return await get_4chan_boards()


def main():
    loop = asyncio.get_event_loop()
    boards = loop.run_until_complete(get_4chan_boards_async())  # Obtener la lista de boards de manera sincrónica

    app = MainScreen(boards=boards)  # Pasar la lista de boards como parámetro
    app.run()


if __name__ == "__main__":
    main()
