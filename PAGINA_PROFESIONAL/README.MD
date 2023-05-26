# Markdown

Markdown es un lenguaje de marcado utilizado para estructurar y organizar datos. Se originó como una forma de crear contenido legible tanto para humanos como para máquinas. Es ampliamente utilizado en aplicaciones y plataformas que requieren el intercambio de información entre diferentes sistemas.

# Uso de Markdown

Markdown se utiliza en diversas situaciones, entre las cuales se incluyen:

    - Creación de documentos y contenido en plataformas de blogging.
    - Documentación de proyectos de software y desarrollo.
    - Creación de páginas web sencillas.
    - Comunicación en foros y plataformas de colaboración.
    - Escritura de notas y apuntes.

# DTD (Document Type Definition)

DTD es una especificación que define la estructura y el formato válidos para un documento XML. Proporciona un conjunto de reglas y restricciones que los documentos XML deben seguir. El DTD especifica los tipos de elementos permitidos, los atributos que pueden tener y cómo deben estar estructurados los documentos XML.

En resumen, Markdown es un lenguaje de marcado utilizado para estructurar y organizar datos. Se utiliza ampliamente en aplicaciones y plataformas para el intercambio de información entre diferentes sistemas. Permite definir elementos, atributos y anidar elementos dentro de otros elementos. También se puede utilizar en diversas situaciones, como la creación de documentos, la documentación de proyectos y la escritura de notas. El DTD es una especificación que define las reglas y restricciones válidas para un documento XML.

# Elementos de Markdown
Markdown permite formatear documentos con muchos elementos. A continuacion hay una muestra de ellos:



# h1 Titulo 8-)
## h2 Titulo
### h3 Titulo
#### h4 Titulo
##### h5 Titulo
###### h6 Titulo


## Lineas horizontales (Rules)

___

---

***



## Enfasis

**Negrita**

__Negrita__

*Cursiva*

_Cursiva_

~~Tachado~~


## Citas


> Esto es una cita...
>> ...que esta dentro de otra cita...
> > > ...y se ponen añadiendo flechas con o sin espacios.


## Listas

Desordenadas

+ Crea una lista desordeanda con `+`, `-`, o `*`
+ Las listas anidadas se hacen tabulando:
  - Cambiar el caracter fuerza a cambiar la lista:
+ Y asi sin parar!

Listas ordenadas

1. Lorem ipsum
2. dolor sit
3. amet me quedo sin ideaas mohamed


1. Ah y puedes poner los numeros bien o...
1. ...te la puede pelar y poner el mismo sin parar
5. que el solito pone el correcto

Si pones sangrado al texto md lo pone bien solo:

1.  foo
2. bar


## Codigo

Codigo en la misma linea con tildes `sudo apt install nyan-cat`

Con indentaciones

    // Some comments
    linea 1 de codigo
    linea 2 de codigo
    linea 3 de codigo


Bloque de codigo con tides o comillas

```
Codigo sencillo o sin detect sintaxis...
```

Sobresaltado de sintaxis:

``` js
var leche = ordeñar (cabra) {
  return leche++;
};

console.log(leche(5));
```

## Tablas

| Option | Description |
| ------ | ----------- |
| cosa   | cosas. |
| cosillas | cosotas. |

Parece csv

## Links

[Adoro los patos](https://duckduckgo.com/)

## Imagenes

![Superduck](https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2F4.bp.blogspot.com%2F-AAOWzD6wjDU%2FUAT83Qpxp4I%2FAAAAAAAAM-s%2Fu9BOkKeDD3s%2Fs1600%2FDuck2.jpg&f=1&nofb=1&ipt=6a5134cc4698b0aa957e91f848f3fca3ae8faa46115d51a0fd85902ada71ad93&ipo=images)


### Listas de definicion

Cosa

:   Definicion
continuando en la
siguientelinea. No para hasta que metes dos
saltos de linea

Asi se sale de la definicion
