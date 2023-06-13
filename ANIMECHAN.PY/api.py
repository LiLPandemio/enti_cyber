import requests
import asyncio

# Obtener los boards
async def get_4chan_boards():
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

# # Ejemplo de uso
# boards = get_4chan_boards()
# print(boards)

# Obtener hilos de un board
async def get_board_threads(board):
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

async def get_thread_by_id(thread_id):
    url = f"https://a.4cdn.org/po/thread/{thread_id}.json"
    url = f"https://a.4cdn.org/po/thread/570368.json"
    response = requests.get(url)

    if response.status_code == 200:
        thread = response.json()
        return thread
    else:
        print(f"Error al obtener el hilo. Código de estado: {response.status_code}")



# # Ejemplo de uso
# board = "g"  # Tablero /g/ (Tecnología) de 4chan
# threads = get_board_threads(board)
# print(threads)
