"""
Proyecto de API: https://api.breakingbadquotes.xyz/v1/quotes
    El proyect consistira en un minijuego de preguntas sobre BrakingBads.
        Adivina el personaje
        Adivina el personaje del BrakingBad X

Funcionamiento:
El menu principal tendra 2 botones centrados que dan las opciones:
    Adivina el personaje
        El menu mostrara una frase obtenida de la api https://api.breakingbadquotes.xyz/v1/quotes
        Habra debajo un input text que preguntara que personaje dice esa frase.


"""

from textual.app import App, ComposeResult
from textual.widgets import Button, Label, Input, Static
from textual.screen import Screen
import asyncio
import httpx

async def obtener_frase_aleatoria():
    url = "https://api.breakingbadquotes.xyz/v1/quotes"
    async with httpx.AsyncClient() as client:
        try:
            response = await client.get(url)
            if response.status_code == 200:
                frase = response.json()[0]
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


class AdivinaPersonajeBrakingBadScreen(Screen):
    CSS_PATH = "main.css"

    def compose(self) -> ComposeResult:
        self.backbutton = Button("Volver al menú", classes="danger", id="goBack")
        self.submitButton = Button("Responder", classes="success", id="submit")

        yield Label("Adivina el puto personaje del BrakingBad :)", id="monodeuganda")
        yield Input(placeholder="Respuesta")
        yield self.submitButton
        yield self.backbutton

    def on_button_pressed(self, event: Button.Pressed) -> None:
        # Lógica del botón de salir
        if event.button.id == "goBack":
            self.dismiss()  # Pop screen


class MyFuckingScreen(App):
    CSS_PATH = "main.css"
    frase_aleatoria_text = ""  # Add this line
    def compose(self) -> ComposeResult:
        # Definición de los botones de lanzamiento
        self.Launch_AdivinaPersonaje = Button("Adivina el personaje", id="Launch_AdivinaPersonaje")

        # Definición del botón de cerrar juego
        self.close_button = Button("Salir", classes="danger", id="close")

        #Creando una isntancia de label almacenada en una propiedad de la clase para modificar mas tarde su texto.
        self.frase_aleatoria = Static(id="frase_aleatoria")
        self.frase_aleatoria.update("Loading...")
        # Creación de los objetos
        yield Label("Bienvenido a Breaking Bad Guesser", id="hello")
        yield self.frase_aleatoria
        yield self.Launch_AdivinaPersonaje
        yield self.close_button

    async def obtener_y_actualizar_frase_aleatoria(self):
        frase_aleatoria = await obtener_frase_aleatoria()
        frase_aleatoria = frase_aleatoria["quote"] + "\n  - " + frase_aleatoria["author"]
        if frase_aleatoria is not None:
            self.frase_aleatoria.update(frase_aleatoria)   # Update the frase_aleatoria_text property
        else:
            self.frase_aleatoria.update("Error")  # Update the frase_aleatoria_text property
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


if __name__ == "__main__":
    app = MyFuckingScreen()
    app.run()
