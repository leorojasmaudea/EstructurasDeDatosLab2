# HashingDinamico (Lab 2 - Estructuras de Datos)

Implementación en **Java** de un almacenamiento usando **hashing** (con comparación contra búsqueda secuencial), junto con una clase de pruebas/benchmark manual.

## Requisitos
- **Java 24** (según `pom.xml`)
- **Maven**

## Cómo compilar
```bash
mvn clean package
```

## Cómo ejecutar (pruebas manuales)
Este proyecto no tiene pruebas unitarias (JUnit). En su lugar, incluye una clase con `main` para ejecutar pruebas manuales y medir tiempos:

- `org.example.HashingStorageTest`

Ejecuta la clase con Maven usando el plugin `exec` (si lo tienes configurado) o directamente con el classpath.

### Opción A: usando Maven Exec Plugin (recomendado)
> Nota: si tu `pom.xml` **no** tiene configurado `exec-maven-plugin`, puedes agregarlo o usar la Opción B.

```bash
mvn -q clean package exec:java -Dexec.mainClass=org.example.HashingStorageTest
```

### Opción B: ejecutando el `.class` desde `target/classes`
```bash
mvn -q clean package
java -cp target/classes org.example.HashingStorageTest
```

## Qué hace `HashingStorageTest` (pruebas manuales)

La clase `org.example.HashingStorageTest` funciona como **prueba manual** y **benchmark** (no JUnit) para validar inserciones y comparar tiempos de búsqueda **por hashing vs búsqueda secuencial**.

### Escenarios que ejecuta

1. **Inicialización**
   - Crea una instancia de `HashingStorage`.
   - Ejecuta `storage.initializeFiles()` para preparar los archivos de almacenamiento.

2. **Carga de datos**
   - Crea **100 usuarios (`User`)** y los inserta con `storage.addUser(...)`.
   - CC usados:
     - Un primer grupo: `123456789`, `987654321`, `555555555`, `111111111`, `222222222`, `333333333`, `444444444`, `666666666`, `777777777`, `888888888`
     - Luego un rango desde **`100000001`** hasta **`100000090`**.

3. **Duplicados**
   - Inserta el mismo usuario dos veces para probar el manejo de duplicados:
     - `usuario100` con CC **`100000090`** se intenta agregar nuevamente.

4. **Listado y conteo**
   - Lista los usuarios con `storage.getAllUsers()`.
   - Imprime la cantidad total con `storage.getUserCount()`.

5. **Benchmark (Hashing vs Secuencial)**
   Mide tiempo con `System.nanoTime()` para comparar:
   - **Caso A (existente):** CC `123456789`
     - Hashing: `getUser(123456789L)`
     - Secuencial: `getUserSeq(123456789L)`
   - **Caso B (existente):** CC `100000037`
     - Hashing: `getUser(100000037L)`
     - Secuencial: `getUserSeq(100000037L)`
   - **Caso C (inexistente):** CC `999999999`
     - Hashing: `getUser(999999999L)`
     - Secuencial: `getUserSeq(999999999L)`

### Nota sobre la salida en consola
En la impresión del usuario encontrado, los labels están invertidos:
- Se imprime **"Nombre:"** con `getCc()`
- Se imprime **"CC:"** con `getName()`

(Esto es solo un tema de presentación en consola; no cambia la lógica de búsqueda).

## Estructura (convención Maven)
- `src/main/java/`: código fuente

## Autor
GitHub: **leorojasmaudea**