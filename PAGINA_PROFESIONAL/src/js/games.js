// Variable global para almacenar las adivinanzas cargadas desde el archivo JSON
var adivinanzas = [];

// Función para cargar las adivinanzas desde el archivo JSON
function cargarAdivinanzas() {
    fetch('./src/js/adivinanzas.json')
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            adivinanzas = data;
            mostrarAdivinanza();
        })
        .catch(function (error) {
            console.log('Error al cargar las adivinanzas:', error);
        });
}

// Función para mostrar una adivinanza aleatoria en la página
function mostrarAdivinanza() {
    var indice = Math.floor(Math.random() * adivinanzas.length);
    var adivinanza = adivinanzas[indice];

    // Mostrar el título de la adivinanza
    var titulo = document.getElementById('titulo');
    titulo.textContent = adivinanza.adivinanza;

    // Limpiar el campo de entrada de texto
    var respuestaInput = document.getElementById('respuesta');
    respuestaInput.value = '';
}

// Función para comprobar la respuesta del usuario
function comprobarRespuesta() {
    var respuestaInput = document.getElementById('respuesta');
    var respuestaUsuario = respuestaInput.value.toLowerCase().trim();

    var adivinanzaActual = adivinanzas.find(function (adivinanza) {
        return adivinanza.respuesta.toLowerCase().trim() === respuestaUsuario;
    });

    if (adivinanzaActual) {
        alert('¡Respuesta correcta!');
    } else {
        alert('Respuesta incorrecta. Intenta de nuevo.');
    }

    mostrarAdivinanza();
}

// Función para cambiar a una nueva adivinanza
function cambiarAdivinanza() {
    mostrarAdivinanza();
}