## Ejercicio: **Gestión de avisos**


Imagina un sistema de **avisos** (incidencias, alertas, comunicados internos).

### Entidad: `Aviso`

Campos mínimos:

*   `id` (autogenerado)
*   `titulo` (texto corto) 
*   `mensaje` (texto largo) 
*   `nivel` (enum) 
*   `activo` (boolean)


### Enum: `NivelAviso`

Valores:

*   `INFO`
*   `WARNING`
*   `CRITICO`”

---

### Reglas del ejercicio (importantes)

*   Usa **Spring Boot + JPA**
*   Persistencia **solo en BBDD** (nada de listas en memoria)
*   API **REST limpia**
*   Nada de atajos raros”

---

### Endpoints que debes implementar

*   **POST `/avisos`**  
    Crear un aviso

*   **GET `/avisos`**  
    Listar todos los avisos

*   **GET `/avisos/{id}`**  
    Obtener un aviso

*   **PUT `/avisos/{id}`**  
    Reemplazar aviso completo

*   **PATCH `/avisos/{id}`**  
    Modificar parcialmente

*   **DELETE `/avisos/{id}`**  
    Borrar aviso”


### Condiciones extra (para subir nivel)

*   `nivel` debe mapearse correctamente como `STRING`

*   PATCH solo modifica campos no nulos

*   DELETE no devuelve body

*   Si el id no existe → **404**

*   Usa DTO de entrada (`AvisoRequest`), no la entidad directa”


