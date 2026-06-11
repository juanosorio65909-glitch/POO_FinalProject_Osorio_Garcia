# POO_FinalProject_Osorio_Garcia

## Sistema de Gestion Hotelera

## Integrantes

Juan Esteban Osorio 65909 y Jose Manuel Garcia 63911

## Descripcion General

Este proyecto es un sistema interno de gestion hotelera desarrollado en Java 17/21 aplicando programacion orientada a objetos. Esta pensado para ser usado por personal del hotel, como operadores, empleados o administradores, no por clientes finales.

El sistema permite administrar informacion del hotel, habitaciones, huespedes, reservas y empleados. Tambien conserva el estado completo de la aplicacion entre ejecuciones mediante archivos serializados usando `ObjectOutputStream` y `ObjectInputStream`.

La aplicacion incluye un sistema basico de recomendacion de habitaciones. Este componente analiza las habitaciones disponibles y recomienda la opcion mas economica que cumpla con la cantidad de camas solicitada y el presupuesto maximo ingresado por el operador.

## Arquitectura

El codigo fuente esta organizado en los paquetes requeridos:

```text
src/
  ui/       Interfaz de usuario por consola
  domain/   Clases del modelo y logica de negocio
  data/     Persistencia mediante archivos serializados
```

## Instrucciones Para Ejecutar

Desde la carpeta principal del proyecto, ejecutar:

```powershell
javac -d out src/data/*.java src/domain/*.java src/ui/*.java
java -cp out ui.Main
```

El proyecto usa solamente librerias estandar de Java. No utiliza bases de datos, frameworks ni librerias externas.

## Ejemplo De Entrada Y Salida

```text
- - - HOTEL MANAGEMENT SYSTEM - - -
(1) Configure hotel information
(2) Manage rooms
(3) Manage reservations
(4) Manage employees
(5) Records and searches
(6) Room recommendation
(7) Save and exit

- Enter an option: 2

- - - ROOM MENU - - -
(1) Add room
(2) List rooms
(3) Search room
(4) Update room
(5) Delete room
(6) Go back
```

## Diagrama De Clases

El diagrama de clases se encuentra en la raiz del proyecto:

[ModelDiagram.gif](ModelDiagram.gif)
