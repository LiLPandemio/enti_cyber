<?php
$data = json_decode(file_get_contents('php://input'), true);
$filename = 'formdata_supersecret.csv';

// Comprobar si el archivo existe
if (!file_exists($filename)) {
  // Si no existe, se crea el archivo y se agrega el encabezado del CSV
  $header = array_keys($data);
  $headerLine = implode(",", $header);
  file_put_contents($filename, $headerLine . PHP_EOL);
}

// Agregar los datos al archivo CSV
$dataLine = implode(",", $data);
file_put_contents($filename, $dataLine . PHP_EOL, FILE_APPEND);
?>
