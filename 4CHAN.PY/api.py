import requests
import asyncio

# Obtener los boards de 4chan
async def get_4chan_boards():
    """
    Esta funcion realiza una solicitud HTTP GET a la API de 4chan para obtener
    la lista de boards disponibles en 4chan.

    Retorna:
    - Una lista de nombres de boards disponibles en 4chan.
    - Si ocurre un error durante la solicitud, retorna una lista vacia.

    Utiliza el modulo 'requests' para realizar la solicitud HTTP.
    """
    url = "https://a.4cdn.org/boards.json"
    response = requests.get(url)
    if response.status_code == 200:
        data = response.json()
        boards = data["boards"]
        board_names = [board["board"] for board in boards]
        return board_names
    else:
        print("Error: Failed to fetch 4chan boards.")
        return []

# Obtener hilos de un board especifico
async def get_board_threads(board):
    """
    Esta funcion realiza una solicitud HTTP GET a la API de 4chan para obtener
    los hilos de un board especifico en 4chan.

    Argumentos:
    - board: El nombre del board en 4chan del cual se desean obtener los hilos.

    Retorna:
    - Una lista de hilos del board especificado.
    - Si ocurre un error durante la solicitud, retorna una lista vacia.

    Utiliza el modulo 'requests' para realizar la solicitud HTTP.
    """
    url = f"https://a.4cdn.org/{board}/1.json"
    response = requests.get(url)
    if response.status_code == 200:
        data = response.json()
        threads = []
        for thread in data['threads']:
            for post in thread['posts']:
                threads.append(post)
        return threads
    else:
        print(f"Error: Failed to fetch threads for board /{board}/")
        return []

# Obtener un hilo especifico por su ID
async def get_thread_by_id(thread_id):
    """
    Esta funcion realiza una solicitud HTTP GET a la API de 4chan para obtener
    un hilo especifico en 4chan dado su ID.

    Argumentos:
    - thread_id: El ID del hilo que se desea obtener.

    Retorna:
    - Los datos del hilo especificado.
    - Si ocurre un error durante la solicitud, se imprime un mensaje de error.

    Utiliza el modulo 'requests' para realizar la solicitud HTTP.
    """
    url = f"https://a.4cdn.org/po/thread/{thread_id}.json"
    response = requests.get(url)

    if response.status_code == 200:
        thread = response.json()
        return thread
    else:
        print(f"Error al obtener el hilo. Codigo de estado: {response.status_code}")

async def get_4chan_boards_async():
    """
    Esta funcion envuelve la funcion 'get_4chan_boards' en una corutina de asyncio,
    permitiendo llamarla de forma asincrona.

    Retorna:
    - El resultado de la funcion 'get_4chan_boards'.

    Utiliza el modulo 'asyncio' para definir una corutina.
    """
    return await get_4chan_boards()

# ...
