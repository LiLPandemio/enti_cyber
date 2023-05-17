import requests
from datetime import datetime
from fabulous import image

fecha_hora_actual = datetime.now()
fecha_hora_formateada = fecha_hora_actual.strftime("%d-%m-%y %H:%M")

url = "https://api.catboys.com/img"
response = requests.get(url)
r = response.json()

uri = r["url"]

print(uri)
img = image.Image(uri)