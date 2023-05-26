function getRandomKanji() {
  // Array de caracteres kanji japoneses
  var kanjiChars = ["漢", "字", "日", "本", "語", "例", "文", "中", "化"];

  // Obtener un carácter kanji aleatorio del array
  var randomIndex = Math.floor(Math.random() * kanjiChars.length);
  return kanjiChars[randomIndex];
}

function animateMatrixText() {
  var originalText = "Software dev, sysadmin, and hacker (Sometimes ethical)";
  var matrixText = document.getElementById("matrix-text");
  var currentText = matrixText.innerHTML;
  var newText = "";

  // Reemplazar caracteres individuales con kanjis aleatorios
  for (var i = 0; i < originalText.length; i++) {
    if (originalText[i] !== " ") {
      if (Math.random() < 0.5) {
        newText += currentText[i];
      } else {
        newText += getRandomKanji();
      }
    } else {
      newText += " ";
    }
  }

  // Actualizar el contenido del elemento HTML
  matrixText.innerHTML = newText;

  // Restaurar el texto original después de 1000 milisegundos (1 segundo)
  setTimeout(function () {
    matrixText.innerHTML = originalText;
  }, 1100);
}

function addErrorAlert(message) {
  // Crear el elemento div para el mensaje de error
  var errorDiv = document.createElement("div");
  errorDiv.className = "alert-error";

  // Generar un ID aleatorio para el div de error
  var errorId = generateRandomId();
  errorDiv.id = errorId;

  // Crear el elemento de párrafo para el mensaje
  var messageParagraph = document.createElement("p");
  messageParagraph.textContent = message;

  // Añadir el mensaje de error al div
  errorDiv.appendChild(messageParagraph);

  // Obtener el elemento con el ID "errors" y añadir el div como hijo
  var errorsElement = document.getElementById("errors");
  errorsElement.appendChild(errorDiv);

  // Eliminar el div de error después de 10 segundos
  setTimeout(() => {
    var errorToRemove = document.getElementById(errorId);
    if (errorToRemove) {
      errorToRemove.remove();
    }
  }, 10000);
}

function generateRandomId() {
  // Generar un ID aleatorio utilizando la función `Math.random()`
  var randomId = "error-" + Math.random().toString(36).substr(2, 9);
  return randomId;
}

// Ejecutar la animación cada 100 milisegundos
setInterval(animateMatrixText, 1000);

function checkForm() {
  document.getElementById("errors").innerHTML = ""; //Evitar que se aniden 91230238 errores xD
  // Obtener los elementos del formulario
  var form = document.querySelector(".contactform");
  var emailInput = document.getElementById("mail");
  var countryInput = document.getElementById("country");
  var commentInput = document.getElementById("comment");

  // Verificar que todos los campos tengan contenido
  valid = true;
  if (countryInput.value === "") {
    addErrorAlert("Porfavor, selecciona un país");
    document.getElementById("country").style.border = "2px solid #e00";
    valid = false;
  } else {
    document.getElementById("country").style.border = "2px solid #eee";
    valid = true;
  }
  var email = emailInput.value;
  if (email.trim() === "") {
    addErrorAlert("Porfavor, indica un correo electrónico");
    document.getElementById("mail").style.border = "2px solid #e00";
    valid = false;
  } else {
    //Hay un mail, comprobar que sea valido
    if (!validateEmail(email)) {
      addErrorAlert("Por favor, ingresa un correo electrónico válido.");
      document.getElementById("mail").style.border = "2px solid #e00";
      return false;
    } else {
      document.getElementById("mail").style.border = "2px solid #eee";
      valid = true;
    }
  }
  if (document.getElementById("comment").value.trim() === "") {
    addErrorAlert("Porfavor, escribe un comentario");
    document.getElementById("comment").style.border = "2px solid #e00";
    valid = false;
  } else {
    document.getElementById("comment").style.border = "2px solid #eee";
    valid = true;
  }

  if (!valid) {
    return false;
  }
  // Crear un objeto con los datos del formulario
  var formData = {
    email: email,
    country: countryInput.value,
    comment: commentInput.value,
  };

  // Enviar los datos en formato JSON a la dirección de acción del formulario
  var jsonData = JSON.stringify(formData);
  var action = form.getAttribute("action");

  var xhr = new XMLHttpRequest();
  xhr.open("POST", "form.php", true);
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
      console.log("Datos enviados correctamente");
    } else {
        addErrorAlert("Algo ha salido mal al procesar tus datos")
    }
  };
  xhr.send(JSON.stringify(jsonData));
  // Opcionalmente, puedes mostrar los datos enviados en la consola
  console.log(jsonData);

  // Retorna false para evitar el envío del formulario por defecto
  return false;
}

// Función para validar el correo electrónico usando una expresión regular
function validateEmail(email) {
  var re = /\S+@\S+\.\S+/;
  return re.test(email);
}
