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
import random
import random
import re


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

def censurar_palabra(frase):
    palabras = re.findall(r'\b\w+\b', frase)
    palabras_largas = [palabra for palabra in palabras if len(palabra) > 2]  # Filtrar palabras largas
    cantidad_palabras = len(palabras_largas)
    palabras_a_censurar = max(1, int(cantidad_palabras * 0.5))  # Censurar al menos una palabra
    palabras_aleatorias = random.sample(palabras_largas, palabras_a_censurar)

    palabra_a_censurar = random.choice(palabras_aleatorias)
    palabra_oculta = "*" * len(palabra_a_censurar)
    frase_censurada = re.sub(r'\b' + re.escape(palabra_a_censurar) + r'\b', palabra_oculta, frase)

    return palabra_a_censurar, frase_censurada

class AdivinaPersonajeScreen(Screen):
    CSS_PATH = "main.css"
    def compose(self) -> ComposeResult:
                # Definición del botón de cerrar juego
        self.close_button = Button("Salir", classes="danger", id="close")
        self.guessinput = Input(placeholder="Respuesta")
        # Creando un elemento static al que hacer update
        self.frase_aleatoria = Static(id="frase_aleatoria")
        self.frase_aleatoria.update("Loading...")
        # Creación de los objetos
        yield Label("Bienvenido a Breaking Bad Guesser", id="hello")
        yield self.frase_aleatoria
        self.backbutton = Button("Volver al menú", classes="danger", id="goBack")
        self.submitButton = Button("Responder", classes="success", id="submit")
        yield self.guessinput
        self.pasapalabra = Button("Pasa palabra", id="newquestion")
        yield self.submitButton
        yield self.pasapalabra
        # Boton ir atrás
        self.backbutton = Button("Volver al menú", classes="danger", id="goBack")
        yield self.backbutton
    async def obtener_y_actualizar_frase_aleatoria(self):
        frase_aleatoria = await obtener_frase_aleatoria()
        frase = frase_aleatoria["quote"]
        self.answer = frase_aleatoria["author"]
        if frase is not None:
            self.frase_aleatoria.update(frase)   # Update the frase_aleatoria_text property
        else:
            self.frase_aleatoria.update("Error")  # Update the frase_aleatoria_text property
        self.refresh()
    async def on_mount(self):
        await self.obtener_y_actualizar_frase_aleatoria()

    async def on_button_pressed(self, event: Button.Pressed) -> None:
        # Lógica del botón de salir
        if event.button.id == "goBack":
            self.dismiss()  # Pop screen
        # Lógica del botón submit
        if event.button.id == "newquestion":
            await self.obtener_y_actualizar_frase_aleatoria()
        if event.button.id == "submit":
            text = self.guessinput.value
            if(text == self.answer):
                text = "Tu respuesta es correcta"
            else:
                text = "La respuesta correcta era: " + self.answer 
            self.frase_aleatoria.update(text)

class AdivinaPalabraScreen(Screen):
    CSS_PATH = "main.css"


    def compose(self) -> ComposeResult:
        self.answer = ""

        # Creando un elemento static para mostrar la frase
        self.frase = Static(id="frase")
        self.frase.update("Loading...")
        yield Label("Bienvenido a Breaking Bad Guesser, ADIVINA LA PALABRA!!!!!!!", id="hello")
        yield self.frase

        # Creando un elemento input para que el usuario ingrese su respuesta
        self.guessinput = Input(placeholder="Respuesta")
        yield self.guessinput

        # Botón para enviar la respuesta
        self.submitButton = Button("Responder", classes="success", id="submit")
        yield self.submitButton

        # Botón para obtener una nueva frase
        self.newFraseButton = Button("Nueva Frase", id="newFrase")
        yield self.newFraseButton

        # Boton ir atrás
        self.backbutton = Button("Volver al menú", classes="danger", id="goBack")
        yield self.backbutton

    async def obtener_y_actualizar_frase_aleatoria(self):
        frase_aleatoria = await obtener_frase_aleatoria()
        self.answer, frase = censurar_palabra(frase_aleatoria["quote"])
        if frase is not None:
            self.frase.update(frase + "\n -  " + frase_aleatoria["author"])  # Update the frase_text property
        else:
            self.frase.update("Error")  # Update the frase_text property
        self.refresh()

    async def on_mount(self):
        await self.obtener_y_actualizar_frase_aleatoria()

    async def on_button_pressed(self, event: Button.Pressed) -> None:
        # Lógica del botón de volver al menú
        if event.button.id == "goBack":
            self.dismiss()  # Pop screen

        # Lógica del botón de obtener nueva frase
        if event.button.id == "newFrase":
            await self.obtener_y_actualizar_frase_aleatoria()

        # Lógica del botón de submit
        if event.button.id == "submit":
            respuesta_usuario = self.guessinput.value
            if respuesta_usuario == self.answer:
                resultado = "¡Respuesta correcta!"
            else:
                resultado = "Respuesta incorrecta. La respuesta correcta era: " + self.answer
            self.frase.update(resultado)


class MyFuckingScreen(App):
    CSS_PATH = "main.css"

    def compose(self) -> ComposeResult:
        # Definición de los botones de lanzamiento
        self.Launch_AdivinaPersonaje = Button("Adivina el personaje", id="Launch_AdivinaPersonaje")
        self.Launch_AdivinaPalabra = Button("Adivina la palabra", id="Launch_AdivinaPalabra")

        # Definición del botón de cerrar juego
        self.close_button = Button("Salir", classes="danger", id="close")

        # Creando un elemento static al que hacer update
        self.frase_aleatoria = Static(id="frase_aleatoria")
        self.frase_aleatoria.update("Loading...")
        # Creación de los objetos
        yield Label("Bienvenido a Breaking Bad Guesser", id="hello")
        yield self.frase_aleatoria
        yield self.Launch_AdivinaPersonaje
        yield self.Launch_AdivinaPalabra
        yield self.close_button

    async def obtener_y_actualizar_frase_aleatoria(self):
        frase_aleatoria = await obtener_frase_aleatoria()
        frase_aleatoria = frase_aleatoria["quote"] + "\n  - " + frase_aleatoria["author"]
        if frase_aleatoria is not None:
            self.frase_aleatoria.update(frase_aleatoria)  # Update the frase_aleatoria_text property
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

        # Lógica del botón de Adivinar la palabra
        if event.button.id == "Launch_AdivinaPalabra":
            adivinaPalabraScreen = AdivinaPalabraScreen()
            self.push_screen(adivinaPalabraScreen)


if __name__ == "__main__":
    app = MyFuckingScreen()
    app.run()
