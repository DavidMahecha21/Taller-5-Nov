# Taller: Un Proxy para migrar sin salir de main

## Preguntas de reflexión

### 1. ¿Qué habría pasado con una rama feature/smart-shipping de tres semanas mientras el resto del equipo seguía cambiando main?

Habríamos tenido muchos problemas al momento de unirla con main. Durante esas tres semanas el resto del equipo habría seguido cambiando archivos, y como nuestra rama se quedó desactualizada, al hacer el merge saldrían muchos conflictos, sobre todo si tocamos las mismas clases (por ejemplo Checkout). Resolver todo eso de una sola vez es difícil y es fácil dañar algo sin darnos cuenta. Además, como nuestro código no estaría probado junto con el de los demás, los errores se descubrirían tarde, cuando ya es más complicado arreglarlos. Con Trunk-Based Development pasa lo contrario: como subimos cambios pequeños a main todos los días, los problemas se ven rápido y son fáciles de corregir.

### 2. ¿En qué se diferencia su Proxy de un Decorator? ¿Y de un Adapter?

Los tres son patrones que envuelven a otro objeto, pero cada uno lo hace por una razón distinta.

- **Proxy:** controla el acceso al objeto real. En nuestro caso, el Proxy decide a cuál calculador llamar (el legacy o el smart) según el flag, y el cliente no se entera. Usa la misma interfaz que los objetos que envuelve.
- **Decorator:** también usa la misma interfaz, pero su objetivo es agregarle funciones extra al objeto sin cambiarlo, como si le pusiera capas (por ejemplo, agregar un descuento o un log encima del cálculo). No decide si se llama o no al objeto, solo le suma cosas.
- **Adapter:** cambia la interfaz. Se usa cuando tenemos una clase con una interfaz que no encaja con la que necesita el cliente, y el Adapter traduce de una a la otra. En nuestro taller no lo necesitamos porque los dos calculadores ya compartían la interfaz ShippingCalculator.

En resumen: el Proxy controla el acceso, el Decorator agrega comportamiento y el Adapter traduce interfaces.

### 3. ¿Por qué es importante borrar el flag al final? ¿Qué pasa en un proyecto con 200 flags olvidados?

Porque un flag que ya cumplió su función se vuelve código muerto. Si dejamos el flag, seguimos teniendo el código viejo y el nuevo mezclados, con if por todas partes, y quien llegue después no sabe cuál de los dos caminos es el que realmente se usa. Eso es deuda técnica.

En un proyecto con 200 flags olvidados sería un desastre: el código sería muy difícil de leer y de mantener, nadie sabría cuáles están encendidos o apagados ni si se pueden borrar sin romper algo. Además habría muchísimas combinaciones posibles entre flags, y es casi imposible probarlas todas, así que cualquier cambio podría causar un error que nadie espera. Por eso el último paso del taller es borrar el código viejo y el flag.