# SimonDice-MVVC


## Diagrama de flujo

```mermaid
graph TD
    A[MainActivity] -->|Iniciar SimonDiceActivity| B[SimonDiceActivity]
    B -->|Usuario hace clic en el botón de inicio| C[Iniciar nuevo juego]
    C -->|Generar patrón aleatorio| D[Mostrar patrón en la UI]
    B -->|Usuario hace clic en botones de colores (rojo, verde, azul, amarillo)| E[Verificar entrada del jugador]
    E -->|Si la entrada es correcta| F[¿El jugador completó la secuencia actual?]
    F -->|Generar próximo número en el patrón| G[Mostrar nuevo patrón en la UI]
    G -->|Fin del juego| H[¿Juego terminado?]
    H -->|Mostrar mensaje de juego terminado| I[Fin del juego]
    E -->|Si la entrada es incorrecta| I[Mostrar mensaje de juego terminado]
    I -->|Fin del juego| J[Usuario hace clic en el botón de inicio para jugar nuevamente]
    J -->|Fin| K[Fin] 
```
    

## Descripción

Este proyecto es una implementación del clásico juego "Simon Dice" para dispositivos Android. Consiste en replicar una secuencia de colores que se incrementa en dificultad con cada acierto del jugador. El proyecto se divide en varias partes clave que gestionan la interfaz de usuario, la lógica del juego y el almacenamiento del estado del juego.

## Estructura del Proyecto

El proyecto está estructurado en tres partes principales:

### 1. `MainActivity`

- **Función**: Punto de entrada de la aplicación. Inicia `SimonDiceActivity`.
- **Métodos Clave**:
  - `onCreate()`: Configura la actividad inicial y lanza `SimonDiceActivity`.

### 2. `SimonDiceActivity`

- **Función**: Maneja la interfaz de usuario del juego.
- **Métodos Clave**:
  - `onCreate()`: Inicializa la vista y establece los controladores de eventos para los botones.
  - `highlightSequence()`: Resalta la secuencia de colores en la interfaz.
  - `getColorForPattern()`: Devuelve el color correspondiente al patrón.
  - `findButtonForPattern()`: Encuentra el botón correspondiente al patrón de colores.

### 3. `SimonDiceGame` y `SimonDiceViewModel`

- **`SimonDiceGame`**
  - **Función**: Gestiona la lógica y el estado del juego.
  - **Métodos Clave**:
    - `addToPattern()`: Añade un nuevo color a la secuencia.
    - `checkPlayerInput()`: Verifica la entrada del jugador.
    - `resetGame()`: Reinicia el juego.
    - `generateRandomNumber()`: Genera un número aleatorio para el patrón.

- **`SimonDiceViewModel`**
  - **Función**: Intermediario entre la vista y el modelo.
  - **Métodos Clave**:
    - `startNewGame()`: Inicia un nuevo juego.
    - `playerInput()`: Gestiona la entrada del jugador y actualiza la vista.

## Cómo Jugar

- Al iniciar la aplicación, se muestra `SimonDiceActivity`.
- El usuario debe replicar una secuencia de colores mostrada, presionando botones en el orden correcto.
- La secuencia se alarga con cada ronda correcta, aumentando la dificultad.
- Un error en la secuencia termina el juego.