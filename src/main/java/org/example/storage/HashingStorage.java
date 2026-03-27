package org.example.storage;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class HashingStorage {
    private final String DIRECTORY_FILE = "directory.dat";
    private final String BUCKETS_FILE = "buckets.dat";
    private final String DATA_FILE = "users.dat";

    // Capacidad máxima de registros por bucket
    private static final int BUCKET_CAPACITY = 2;

    // Formato de cada bucket: localDepth(int,4) + count(int,4) + BUCKET_CAPACITY * (cc(long,8) + dataOffset(long,8))
    // Tamaño total de un bucket: 8 + BUCKET_CAPACITY * 16 = 40 bytes

    // Formato del directorio: globalDepth(int,4) + 2^globalDepth * puntero(long,8)

    private int globalDepth = 1;

    private void resetFiles() throws IOException {
        Files.deleteIfExists(Path.of(DIRECTORY_FILE));
        Files.deleteIfExists(Path.of(BUCKETS_FILE));
        Files.deleteIfExists(Path.of(DATA_FILE));
    }

    public void initializeFiles() throws Exception {
        resetFiles();
        globalDepth = 1;
        try (RandomAccessFile bucketsFile = new RandomAccessFile(BUCKETS_FILE, "rw");
             RandomAccessFile directoryFile = new RandomAccessFile(DIRECTORY_FILE, "rw");
             RandomAccessFile dataFile = new RandomAccessFile(DATA_FILE, "rw")) {
            // Escribimos la profundidad global en el directorio
            directoryFile.writeInt(globalDepth);

            // Creamos dos buckets vacíos con localDepth = 1
            long bucket1Offset = createEmptyBucket(bucketsFile, 1);
            long bucket2Offset = createEmptyBucket(bucketsFile, 1);

            // Directorio: 2 entradas (2^1), cada una es un long que apunta al offset del bucket
            directoryFile.writeLong(bucket1Offset); // índice 0 → bucket 0
            directoryFile.writeLong(bucket2Offset); // índice 1 → bucket 1

            // Inicializamos el archivo de datos con un contador de registros en 0
            dataFile.writeInt(0);
        }
    }

    /**
     * Crea un bucket vacío al final del archivo de buckets y retorna su offset.
     */
    private long createEmptyBucket(RandomAccessFile bucketsFile, int localDepth) throws IOException {
        long offset = bucketsFile.length();
        bucketsFile.seek(offset);
        bucketsFile.writeInt(localDepth); // Profundidad local
        bucketsFile.writeInt(0);          // Cantidad de registros = 0
        for (int i = 0; i < BUCKET_CAPACITY; i++) {
            bucketsFile.writeLong(-1); // CC placeholder
            bucketsFile.writeLong(-1); // dataOffset placeholder
        }
        return offset;
    }

    private int getDirectoryIndex(long cc) {
        int h = hash(cc);
        return h & ((1 << globalDepth) - 1);
    }

    private int hash(long cc) {
        return (int) (Math.abs(cc) % 97);
    }

    private static void doubleDirectory(RandomAccessFile directory, int oldGlobalDepth) throws IOException {
        //Calculamos el tamaño del directorio antes de la duplicación, el cual es 2^oldGlobalDepth, 
        // y leemos todos los punteros actuales a los buckets para luego escribirlos dos veces en el nuevo directorio.
        int oldSize = 1 << oldGlobalDepth;
        List<Long> oldPointers = new ArrayList<>();
        directory.seek(4); // Saltamos el globalDepth
        //Creamos una lista para almacenar los punteros actuales del directorio, 
        // los cuales se encuentran después de los 4 bytes de la profundidad global.
        for (int i = 0; i < oldSize; i++) {
            oldPointers.add(directory.readLong());
        }
        //Reseteamos el directorio para escribir la nueva profundidad global y luego los punteros a los buckets.
        directory.setLength(0);
        directory.seek(0);
        //Escribimos la nueva profundidad global, que es el doble de la anterior, 
        // y luego escribimos los punteros a los buckets dos veces para reflejar la duplicación del directorio.
        directory.writeInt(oldGlobalDepth + 1);
        for (int i = 0; i < oldSize; i++) {
            directory.writeLong(oldPointers.get(i));
        }
        for (int i = 0; i < oldSize; i++) {
            directory.writeLong(oldPointers.get(i));
        }
    }

    public boolean addUser(User usuarioData) throws Exception {
        //Primero verificamos que el usuario no exista previamente para evitar duplicados.
        if(getUser(usuarioData.cc) != null){
            System.out.println("Usuario con CC " + usuarioData.cc + " ya existe. No se permite duplicados.");
            return false;
        }

        // Primero almacenar al final del archivo de datos para obtener el offset correspondiente
        long dataOffset = addUserData(usuarioData);
        //Segundo, insertar la entrada (cc, dataOffset) en el bucket correspondiente segun el directorio y la función hash. Si el bucket está lleno, se divide y se reintenta la inserción.
        insertEntry(usuarioData.cc, dataOffset);
        System.out.println("Usuario agregado: CC=" + usuarioData.cc + ", Nombre=" + usuarioData.name + ", Email=" + usuarioData.email);
        return true;
    }

    private long addUserData(User usuarioData) throws IOException {
        long dataOffset;
        try (RandomAccessFile dataFile = new RandomAccessFile(DATA_FILE, "rw")) {
            // Leer el contador actual de registros y actualizarlo
            dataFile.seek(0);
            int count = dataFile.readInt();
            dataFile.seek(0);
            dataFile.writeInt(count + 1); //contador++

            // Escribir el registro al final del archivo
            dataOffset = dataFile.length();
            dataFile.seek(dataOffset);
            dataFile.writeLong(usuarioData.cc);
            dataFile.writeUTF(usuarioData.name);
            dataFile.writeUTF(usuarioData.email);
        }
        return dataOffset;
    }


    /**
     * Inserta una entrada (cc, dataOffset) en el bucket correspondiente.
     * Si el bucket está lleno, lo divide y reintenta hasta lograr la inserción.
     */
    private void insertEntry(long cc, long dataOffset) throws Exception {
        try (RandomAccessFile directoryFile = new RandomAccessFile(DIRECTORY_FILE, "rw");
             RandomAccessFile bucketsFile = new RandomAccessFile(BUCKETS_FILE, "rw")) {
            globalDepth = directoryFile.readInt();
            //Es true ya que el criterio de parada es cuando se logra insertar el nuevo registro, lo que puede requerir múltiples splits si los registros siguen cayendo en el mismo bucket.
            while (true) {
                //Obtenemos el indice, el cual está dado por los bits menos significativos del hash de la CC, limitados por la profundidad global (globalDepth).
                int dirIndex = getDirectoryIndex(cc);

                // Leer el puntero al bucket desde el directorio
                //Saltamos los 4 bytes de la profundidad global la cual está almacenada en disco y luego multiplicamos el índice por 8 (tamaño de un long) para obtener la posición del puntero al bucket.
                directoryFile.seek(4 + dirIndex * 8L);
                //Obtenemos el offset del bucket al que corresponde el índice calculado.
                long bucketOffset = directoryFile.readLong();

                // Leer la cabecera del bucket
                //Nos posicionamos en el offset del bucket para leer su cabecera, la cual contiene la profundidad local y la cantidad de registros actualmente almacenados.
                bucketsFile.seek(bucketOffset);
                int localDepth = bucketsFile.readInt();
                int count = bucketsFile.readInt();

                //CASO en el que aun hay espacio en el bucket para insertar la nueva entrada sin necesidad de dividirlo.
                // Si hay espacio, insertar directamente
                if (count < BUCKET_CAPACITY) {
                    //Nos ubicamos en el offset del bucket, saltamos los 8 bytes de la cabecera (localDepth + count) y luego avanzamos
                    // count * 16 bytes para posicionarnos al final de las entradas actuales (cada entrada ocupa 16 bytes: 8 para cc y 8 para dataOffset).
                    bucketsFile.seek(bucketOffset + 8 + (long) count * 16);
                    bucketsFile.writeLong(cc);
                    bucketsFile.writeLong(dataOffset);
                    // Actualizar el contador
                    bucketsFile.seek(bucketOffset + 4);
                    bucketsFile.writeInt(count + 1);
                    return;
                }

                //CASO en el que el bucket ya alcanzó su capacidad máxima, por lo que debemos dividirlo para poder insertar la nueva entrada.
                // El proceso de división implica crear un nuevo bucket, redistribuir las entradas entre el bucket original y el nuevo,
                // y actualizar los punteros en el directorio.
                // Leer las entradas existentes del bucket lleno
                long[] existingCCs = new long[count];
                long[] existingOffsets = new long[count];
                // Nos posicionamos en el offset del bucket, saltamos los 8 bytes de la cabecera (localDepth + count) y luego leemos las entradas
                // existentes (cc y dataOffset) para almacenarlas temporalmente en arrays.
                bucketsFile.seek(bucketOffset + 8);
                for (int i = 0; i < count; i++) {
                    existingCCs[i] = bucketsFile.readLong();
                    existingOffsets[i] = bucketsFile.readLong();
                }

                //CASO en el que el LD=GD, en este caso, antes de dividir el bucket, debemos duplicar el directorio para aumentar la capacidad
                // de direccionamiento y permitir la creación de nuevos buckets.
                //Si la profundidad local == global, duplicar el directorio
                if (localDepth == globalDepth) {
                    doubleDirectory(directoryFile, globalDepth);
                    globalDepth++; //En doubleDirectory ya se actualiza el valor de globalDepth en el archivo, pero es necesario actualizar la variable en memoria para que el resto del código funcione correctamente con la nueva profundidad global.
                }

                int newLocalDepth = localDepth + 1;

                // Crear el nuevo bucket con la nueva profundidad local
                long newBucketOffset = createEmptyBucket(bucketsFile, newLocalDepth);

                // Limpiar el bucket original y actualizar su profundidad local
                bucketsFile.seek(bucketOffset);
                bucketsFile.writeInt(newLocalDepth);
                bucketsFile.writeInt(0); // count = 0
                for (int i = 0; i < BUCKET_CAPACITY; i++) {
                    bucketsFile.writeLong(-1);
                    bucketsFile.writeLong(-1);
                }

                // Actualizar los punteros del directorio
                int dirSize = 1 << globalDepth;
                int oldMask = (1 << localDepth) - 1;
                int pattern = dirIndex & oldMask; // bits bajos que identificaban al bucket viejo

                for (int i = 0; i < dirSize; i++) {
                    if ((i & oldMask) == pattern) {
                        directoryFile.seek(4 + i * 8L);
                        // El bit en la posición localDepth decide si va al bucket viejo (0) o al nuevo (1)
                        if (((i >> localDepth) & 1) == 1) {
                            directoryFile.writeLong(newBucketOffset);
                        } else {
                            directoryFile.writeLong(bucketOffset);
                        }
                    }
                }

                // Redistribuir las entradas existentes entre los dos buckets
                for (int i = 0; i < existingCCs.length; i++) {
                    int idx = hash(existingCCs[i]) & ((1 << globalDepth) - 1);
                    directoryFile.seek(4 + idx * 8L);
                    long targetBucket = directoryFile.readLong();

                    bucketsFile.seek(targetBucket + 4);
                    int targetCount = bucketsFile.readInt();
                    bucketsFile.seek(targetBucket + 8 + (long) targetCount * 16);
                    bucketsFile.writeLong(existingCCs[i]);
                    bucketsFile.writeLong(existingOffsets[i]);
                    bucketsFile.seek(targetBucket + 4);
                    bucketsFile.writeInt(targetCount + 1);
                }

                // Volver al inicio del while para reintentar la inserción del nuevo registro
                // (el bucket destino puede seguir lleno si todos los registros fueron al mismo lado)
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (RandomAccessFile dataFile = new RandomAccessFile(DATA_FILE, "r")) {
            dataFile.seek(4); // Saltamos los 4 bytes del contador de registros
            while (dataFile.getFilePointer() < dataFile.length()) {
                long cc = dataFile.readLong();
                String name = dataFile.readUTF();
                String email = dataFile.readUTF();
                users.add(new User(cc, name, email));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n=== Todos los usuarios ===");
        for (User u : users) {
            System.out.println("  CC: " + u.cc + ", Nombre: " + u.name + ", Email: " + u.email);
        }
        System.out.println("Total: " + users.size() + " usuarios");
        return users;
    }

    public String getUserCount() {
        try (RandomAccessFile dataFile = new RandomAccessFile(DATA_FILE, "r")) {
            // El contador de registros está almacenado en los primeros 4 bytes del archivo
            dataFile.seek(0);
            return String.valueOf(dataFile.readInt());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "0";
    }

    //Busqueda de usuario por CC usando búsqueda secuencial: se recorre el archivo de datos desde el inicio,
    // leyendo cada registro completo (CC, nombre, email) y comparando la CC con la buscada.
    // Si se encuentra una coincidencia, se retorna el usuario.
    public User getUserSeq(long cc) {
        try (RandomAccessFile dataFile = new RandomAccessFile(DATA_FILE, "r")) {
            dataFile.seek(4); // Saltamos los 4 bytes del contador de registros
            while (dataFile.getFilePointer() < dataFile.length()) {
                long readCC = dataFile.readLong();
                String name = dataFile.readUTF();
                String email = dataFile.readUTF();
                if (readCC == cc) {
                    return new User(readCC, name, email);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Usuario con CC " + cc + " no encontrado.");
        return null;
    }

    //Busqueda de usuario por CC usando Extendible Hashing: se calcula el índice del directorio usando la función hash
    // y la profundidad global, luego se accede al bucket correspondiente y se busca la CC entre las entradas del bucket.
    // Si se encuentra, se lee el registro completo del archivo de datos usando el offset almacenado en el bucket.
    public User getUser(long cc) {
        try (RandomAccessFile directoryFile = new RandomAccessFile(DIRECTORY_FILE, "r");
             RandomAccessFile bucketsFile = new RandomAccessFile(BUCKETS_FILE, "r");
             RandomAccessFile dataFile = new RandomAccessFile(DATA_FILE, "r")) {

            globalDepth = directoryFile.readInt();
            int dirIndex = getDirectoryIndex(cc);

            // Leer el puntero al bucket desde el directorio
            directoryFile.seek(4 + dirIndex * 8L);
            long bucketOffset = directoryFile.readLong();

            // Leer la cabecera del bucket
            bucketsFile.seek(bucketOffset);
            int localDepth = bucketsFile.readInt();
            int count = bucketsFile.readInt();

            // Buscar la CC en las entradas del bucket
            for (int i = 0; i < count; i++) {
                long storedCC = bucketsFile.readLong();
                long dataOffset = bucketsFile.readLong();
                if (storedCC == cc) {
                    // Encontrado: leer el registro completo del archivo de datos
                    dataFile.seek(dataOffset);
                    long readCC = dataFile.readLong();
                    String name = dataFile.readUTF();
                    String email = dataFile.readUTF();
                    return new User(readCC, name, email);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Usuario con CC " + cc + " no encontrado.");
        return null;
    }
}
