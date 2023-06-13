import itertools
from bs4 import BeautifulSoup
from api import get_thread_by_id
from textual.screen import Screen
from textual.widgets import DataTable, Label, Button
from textual.events import MouseEvent
from screens.thread import ThreadScreen
from api import get_board_threads

def clean_html(html):
    """
    Limpia el texto HTML eliminando las etiquetas y devuelve el texto sin formato.

    Args:
    - html: El texto HTML a limpiar.

    Returns:
    - El texto sin formato limpio.

    Utiliza el módulo 'BeautifulSoup' para parsear y manipular el HTML.
    """
    soup = BeautifulSoup(html, 'html.parser')
    text = soup.get_text()
    return text

class BoardScreen(Screen):
    CSS_PATH = "main.css"

    def __init__(self, board):
        super().__init__()
        self.board = board
        self._app = None

    def set_app(self, app):
        self._app = app

    def compose(self):
        yield Label(f"Welcome to {self.board} board!", id="welcome")
        yield DataTable()
        yield Button("Back", id="goBack", classes="danger")

    async def on_mount(self):
        table = self.query_one(DataTable)
        table.overflow_x = 'hidden'
        table.focus()
        table.add_columns("id", "Post id", "Replies", "Poster", "Content")
        self.threads = await get_board_threads(self.board)
        row_key = 0
        for thread in itertools.islice(self.threads, 0, 100):
            row_key = row_key + 1
            table.add_row(thread["no"], thread.get("replies", "0"), f"{thread['name']}", clean_html(thread.get("com", ""))[:120]+"...")
        table.fixed_rows = 1
        table.fixed_columns = 1
        table.cursor_type = "row"
        table.zebra_stripes = True

    def on_data_table_row_selected(self, event: MouseEvent):
        row_index = event.cursor_row
        selected_post_no = self.threads[row_index]["no"]
        self.app.push_screen(ThreadScreen(selected_post_no))

    def on_button_pressed(self, event):
        self.log(f"event.button.id: {event.button.id}")
        if event.button.id == "goBack":
            self.dismiss()

class ThreadScreen(Screen):
    CSS_PATH = "main.css"

    def __init__(self, thread):
        super().__init__()
        self.thread = thread
        self._app = None

    def set_app(self, app):
        self._app = app

    def compose(self):
        yield Label(f"Watching thread {self.thread}", id="welcome")
        yield Button("Salir", classes="danger", id="close")

    async def on_mount(self):
        self.messages = await get_thread_by_id(self.thread)
        self.log(self.messages)

    def on_button_pressed(self, event):
        self.log(f"event.button.id: {event.button.id}")
        if event.button.id == "close":
            self.dismiss()
