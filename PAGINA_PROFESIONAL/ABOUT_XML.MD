## Conceptos básicos de XML

1. Etiquetas:
   - Las etiquetas son donde se definen los elementos. Se definen con corchetes angulares `< >` y pueden tener nombres personalizados. Por ejemplo: `<nombre>Contenido</nombre>`.
   
2. Elementos:
   - Los elementos son unidades básicas de información en XML. Consisten en una etiqueta de apertura `<etiqueta>` que encierra contenido o subelementos, y una etiqueta de cierre `</etiqueta>`. Por ejemplo: `<personal><nombre>Superpandemio</nombre><edad>22</edad></personal>`.
   
3. Atributos:
   - Los atributos proporcionan información adicional sobre los elementos. Se componen de un nombre y un valor, separados por un signo igual `=`. Por ejemplo: `<persona id="12345" sexo="walmart bag"><nombres>Superpandemio</nombres><edad>20</edad></persona>`.
   
4. Anidamiento:
   - XML permite anidar elementos dentro de otros elementos. Esto significa que puedes tener elementos dentro de otros elementos, creando una estructura jerárquica. Por ejemplo:
   ```xml
   <libro>
       <titulo>Como ser una z****</titulo>
       <autor>Mi ex</autor>
       <editorial>Relatos basados en echos reales SL</editorial>
   </libro>
