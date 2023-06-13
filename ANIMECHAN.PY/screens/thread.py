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
