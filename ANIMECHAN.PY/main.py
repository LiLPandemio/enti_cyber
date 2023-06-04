"""
Proyecto de API: https://animechan.vercel.app/
    El proyect consistira en un minijuego de preguntas sobre animes.
        Adivina el personaje
        Adivina el anime
        Adivina el personaje del anime X

Funcionamiento:
El menu principal tendra 4 botones centrados que dan las opciones:
    Adivina el personaje
        El menu mostrara una frase obtenida de la api https://animechan.vercel.app/
        Habra debajo un input text que preguntara que personaje dice esa frase.
    Adivina el anime
        Te saldra una frase con el nombre del personaje en el centro de la pantalla
        Debajo un campo de texto pedirá el nombre del anime
    Adivina el personaje del anime X
        Aqui lo primero te preguntará de que anime quieres ver preguntas.
        Si el anime existe pasara a la siguiente pantalla
            La siguiente pantalla sacara frases de un anime y te preguntara igual que en las pantallas de las otras opciones el nombre del personaje.

"""
"""

An implementation of a classic calculator, with a layout inspired by macOS calculator.


"""
from textual.app import App, ComposeResult
from textual.widgets import Button, Label
from textual.screen import Screen


class AdivinaPersonajeScreen(Screen):
    def compose(self) -> ComposeResult:
        yield Label("Hello Textual", id="hello")


class MainScreen(App):
    CSS_PATH = "main.css"

    def compose(self) -> ComposeResult:
        self.close_button = Button("Close", id="close")
        self.adivinaPersonajeLauncher = Button("Adivinar el personaje", id="adivinaPersonajeLauncher")
        self.adivinaAnimeLauncher = Button("Adivinar el anime", id="adivinaAnimeLauncher")
        self.adivinaPersonajeAnimeLauncher = Button("Adivinar el personaje (de un Anime)", id="adivinaPersonajeAnimeLauncher")

        yield Label("Hello Textual", id="hello")
        yield self.adivinaPersonajeLauncher
        yield self.adivinaAnimeLauncher
        yield self.adivinaPersonajeAnimeLauncher
        yield self.close_button

    def on_mount(self) -> None:
        pass

    def on_button_pressed(self, event: Button.Pressed) -> None:
        if event.button.id == "adivinaPersonajeLauncher":
            adivinaPersonaje = AdivinaPersonajeScreen()
            self.push_screen(adivinaPersonaje)


if __name__ == "__main__":
    app = MainScreen()
    app.run()
