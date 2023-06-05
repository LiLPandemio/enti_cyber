"""
Proyecto de API: https://api.breakingbadquotes.xyz/v1/quotes
    El proyect consistira en un minijuego de preguntas sobre animes.
        Adivina el personaje
        Adivina el anime
        Adivina el personaje del anime X

Funcionamiento:
El menu principal tendra 4 botones centrados que dan las opciones:
    Adivina el personaje
        El menu mostrara una frase obtenida de la api https://api.breakingbadquotes.xyz/v1/quotes
        Habra debajo un input text que preguntara que personaje dice esa frase.
    Adivina el anime
        Te saldra una frase con el nombre del personaje en el centro de la pantalla
        Debajo un campo de texto pedirá el nombre del anime
    Adivina el personaje del anime X
        Aqui lo primero te preguntará de que anime quieres ver preguntas.
        Si el anime existe pasara a la siguiente pantalla
            La siguiente pantalla sacara frases de un anime y te preguntara igual que en las pantallas de las otras opciones el nombre del personaje.

"""

from textual.app import App, ComposeResult
from textual.widgets import Button, Label, Input
from textual.screen import Screen
import asyncio
import httpx

async def obtener_frase_aleatoria():
    url = "https://api.breakingbadquotes.xyz/v1/quotes"
    async with httpx.AsyncClient() as client:
        try:
            response = await client.get(url)
            if response.status_code == 200:
                frase = response.json()[0]["quote"]
                return frase
            else:
                return "Error obteniendo la frase"
        except:
            return "Error comunicándose con el servidor"

class AdivinaPersonajeScreen(Screen):
    CSS_PATH = "main.css"

    def compose(self) -> ComposeResult:
        self.backbutton = Button("Volver al menú", classes="danger", id="goBack")
        self.submitButton = Button("Responder", classes="success", id="submit")

        yield Label("Adivina el puto personaje :)", id="monodeuganda")
        yield Input(placeholder="Respuesta")
        yield self.submitButton
        yield self.backbutton

    def on_button_pressed(self, event: Button.Pressed) -> None:
        # Lógica del botón de salir
        if event.button.id == "goBack":
            self.dismiss()  # Pop screen


class AdivinaAnimeScreen(Screen):
    CSS_PATH = "main.css"

    def compose(self) -> ComposeResult:
        self.backbutton = Button("Volver al menú", classes="danger", id="goBack")
        self.submitButton = Button("Responder", classes="success", id="submit")

        yield Label("Adivina el puto anime :)", id="monodeuganda")
        yield Input(placeholder="Respuesta")
        yield self.submitButton
        yield self.backbutton

    def on_button_pressed(self, event: Button.Pressed) -> None:
        # Lógica del botón de salir
        if event.button.id == "goBack":
            self.dismiss()  # Pop screen


class AdivinaPersonajeAnimeScreen(Screen):
    CSS_PATH = "main.css"

    def compose(self) -> ComposeResult:
        self.backbutton = Button("Volver al menú", classes="danger", id="goBack")
        self.submitButton = Button("Responder", classes="success", id="submit")

        yield Label("Adivina el puto personaje del anime :)", id="monodeuganda")
        yield Input(placeholder="Respuesta")
        yield self.submitButton
        yield self.backbutton

    def on_button_pressed(self, event: Button.Pressed) -> None:
        # Lógica del botón de salir
        if event.button.id == "goBack":
            self.dismiss()  # Pop screen


class MyFuckingScreen(App):
    CSS_PATH = "main.css"

    def compose(self) -> ComposeResult:
        # Definición de los botones de lanzamiento
        self.Launch_AdivinaPersonaje = Button("Adivina el personaje", id="Launch_AdivinaPersonaje")
        self.Launch_AdivinaAnime = Button("Adivina el anime", id="Launch_AdivinaAnime")
        self.Launch_AdivinaPersonajeAnime = Button("Adivina el personaje de un anime", id="Launch_AdivinaPersonajeAnime")

        # Definición del botón de cerrar juego
        self.close_button = Button("Salir", classes="danger", id="close")

        #Creando una isntancia de label almacenada en una propiedad de la clase para modificar mas tarde su texto.
        self.frase_aleatoria = Label("Loading...", id="frase_aleatoria")

        # Creación de los objetos
        yield Label("Bienvenido a AnimeGuesser", id="hello")
        yield self.frase_aleatoria
        yield self.Launch_AdivinaPersonaje
        yield self.Launch_AdivinaAnime
        yield self.Launch_AdivinaPersonajeAnime
        yield self.close_button

    async def obtener_y_actualizar_frase_aleatoria(self):
        frase_aleatoria = await obtener_frase_aleatoria()
        if frase_aleatoria is not None:
            self.frase_aleatoria_text = frase_aleatoria
            self.update_frase_aleatoria_text()
        else:
            self.frase_aleatoria_text = "ERROR"
            self.update_frase_aleatoria_text()

    def update_frase_aleatoria_text(self):
        self.frase_aleatoria.text = self.frase_aleatoria_text
        self.refresh()


    async def on_mount(self):
        await self.obtener_y_actualizar_frase_aleatoria()

    def on_button_pressed(self, event: Button.Pressed) -> None:
        # Lógica del botón de salir
        if event.button.id == "close":
            self.exit()

        # Lógica del botón de Adivinar el personaje
        if event.button.id == "Launch_AdivinaPersonaje":
            adivinaPersonajeScreen = AdivinaPersonajeScreen()
            self.push_screen(adivinaPersonajeScreen)

        # Lógica del botón de Adivina el anime
        if event.button.id == "Launch_AdivinaAnime":
            adivinaAnimeScreen = AdivinaAnimeScreen()
            self.push_screen(adivinaAnimeScreen)

        # Lógica del botón de Adivina el personaje del anime X
        if event.button.id == "Launch_AdivinaPersonajeAnime":
            adivinaPersonajeAnimeScreen = AdivinaPersonajeAnimeScreen()
            self.push_screen(adivinaPersonajeAnimeScreen)


if __name__ == "__main__":
    app = MyFuckingScreen()
    app.run()
