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
            yield Label("Adivina el puto personaje :)", id="monodeuganda")
            pass


class AdivinaAnimeScreen(Screen):
        def compose(self) -> ComposeResult:
            yield Label("Adivina el puto anime :)", id="monodeuganda")
            pass


class AdivinaPersonajeAnimeScreen(Screen):
        def compose(self) -> ComposeResult:
            yield Label("Adivina el puto personaje del anime :)", id="monodeuganda")
            pass


class MyFuckingScreen(App):
    CSS_PATH = "main.css"

    def compose(self) -> ComposeResult:
        #Definicion de los botones de lanzamiento
        self.Launch_AdivinaPersonaje = Button("Adivina el personaje", id="Launch_AdivinaPersonaje")
        self.Launch_AdivinaAnime = Button("Adivina el anime", id="Launch_AdivinaAnime")
        self.Launch_AdivinaPersonajeAnime = Button("Adivina el personaje de un anime", id="Launch_AdivinaPersonajeAnime")

        #Definicion boton de cerrar juego
        self.close_button = Button("Salir", id="close")

        #Creacion de los objetos
        yield Label("Hello Textual", id="hello")
        yield self.Launch_AdivinaPersonaje
        yield self.Launch_AdivinaAnime
        yield self.Launch_AdivinaPersonajeAnime
        yield self.close_button

    def on_mount(self) -> None:
        # self.screen.styles.background = "darkblue"
        # self.close_button.styles.background = "red"
        pass

    def on_button_pressed(self, event: Button.Pressed) -> None:
        #Logica del boton de salir
        if(event.button.id == "close"):
            self.exit(event.button.id)

        #Logica del boton de Adivinar el personaje
        if (event.button.id == "Launch_AdivinaPersonaje"):
            adivinaPersonajeScreen = AdivinaPersonajeScreen()
            self.push_screen(adivinaPersonajeScreen)

        #Logica del boton de Adivina el anime
        if (event.button.id == "Launch_AdivinaAnime"):
            adivinaAnimeScreen = AdivinaAnimeScreen()
            self.push_screen(adivinaAnimeScreen)

        #Logica del boton de Adivina el personaje del anime X
        if (event.button.id == "Launch_AdivinaPersonajeAnime"):
            adivinaPersonajeAnimeScreen = AdivinaPersonajeAnimeScreen()
            self.push_screen(adivinaPersonajeAnimeScreen)

        
if __name__ == "__main__":
    app = MyFuckingScreen()
    app.run()