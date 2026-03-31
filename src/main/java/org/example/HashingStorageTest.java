package org.example;


import org.example.storage.HashingStorage;
import org.example.storage.User;

import java.util.Scanner;

public class HashingStorageTest {

    public static void main(String[] args) {
        HashingStorage storage = new HashingStorage();
        try {
            storage.initializeFiles();

            User usuario1 = new User(123456789L, "Juan Pérez", "juan.perez@mail.com");
            User usuario2 = new User(987654321L, "María Gómez", "maria.gomez@hotmail.com");
            User usuario3 = new User(555555555L, "Carlos Rodríguez", "carlos.rod@gmail.com");
            User usuario4 = new User(111111111L, "Ana Martínez", "ana.martinez@yahoo.com");
            User usuario5 = new User(222222222L, "Luis Fernández", "luis.fernandez@outlook.com");
            User usuario6 = new User(333333333L, "Sofía López", "sofia.lopez@mail.com");
            User usuario7 = new User(444444444L, "Miguel Sánchez", "miguel.sanchez@gmail.com");
            User usuario8 = new User(666666666L, "Laura García", "laura.garcia@hotmail.com");
            User usuario9 = new User(777777777L, "Diego Torres", "diego.torres@yahoo.com");
            User usuario10 = new User(888888888L, "Elena Díaz", "elena.diaz@outlook.com");
            User usuario11 = new User(100000001L, "Andrés Castillo", "andres.castillo@mail.com");
            User usuario12 = new User(100000002L, "Valentina Ríos", "valentina.rios@gmail.com");
            User usuario13 = new User(100000003L, "Santiago Herrera", "santiago.herrera@yahoo.com");
            User usuario14 = new User(100000004L, "Camila Vargas", "camila.vargas@outlook.com");
            User usuario15 = new User(100000005L, "Mateo Jiménez", "mateo.jimenez@mail.com");
            User usuario16 = new User(100000006L, "Isabella Moreno", "isabella.moreno@hotmail.com");
            User usuario17 = new User(100000007L, "Sebastián Ortiz", "sebastian.ortiz@gmail.com");
            User usuario18 = new User(100000008L, "Luciana Ramírez", "luciana.ramirez@yahoo.com");
            User usuario19 = new User(100000009L, "Nicolás Peña", "nicolas.pena@outlook.com");
            User usuario20 = new User(100000010L, "Mariana Castro", "mariana.castro@mail.com");
            User usuario21 = new User(100000011L, "Daniel Mendoza", "daniel.mendoza@gmail.com");
            User usuario22 = new User(100000012L, "Paula Guerrero", "paula.guerrero@hotmail.com");
            User usuario23 = new User(100000013L, "Alejandro Suárez", "alejandro.suarez@yahoo.com");
            User usuario24 = new User(100000014L, "Gabriela Rojas", "gabriela.rojas@outlook.com");
            User usuario25 = new User(100000015L, "Felipe Navarro", "felipe.navarro@mail.com");
            User usuario26 = new User(100000016L, "Carolina Medina", "carolina.medina@gmail.com");
            User usuario27 = new User(100000017L, "Tomás Aguilar", "tomas.aguilar@hotmail.com");
            User usuario28 = new User(100000018L, "Daniela Flores", "daniela.flores@yahoo.com");
            User usuario29 = new User(100000019L, "Emilio Vega", "emilio.vega@outlook.com");
            User usuario30 = new User(100000020L, "Renata Paredes", "renata.paredes@mail.com");
            User usuario31 = new User(100000021L, "Julián Espinosa", "julian.espinosa@gmail.com");
            User usuario32 = new User(100000022L, "Sara Delgado", "sara.delgado@hotmail.com");
            User usuario33 = new User(100000023L, "Ricardo Acosta", "ricardo.acosta@yahoo.com");
            User usuario34 = new User(100000024L, "Andrea Molina", "andrea.molina@outlook.com");
            User usuario35 = new User(100000025L, "Hugo Salazar", "hugo.salazar@mail.com");
            User usuario36 = new User(100000026L, "Natalia Rincón", "natalia.rincon@gmail.com");
            User usuario37 = new User(100000027L, "Martín Cardenas", "martin.cardenas@hotmail.com");
            User usuario38 = new User(100000028L, "Lorena Parra", "lorena.parra@yahoo.com");
            User usuario39 = new User(100000029L, "Iván Reyes", "ivan.reyes@outlook.com");
            User usuario40 = new User(100000030L, "Claudia Zambrano", "claudia.zambrano@mail.com");
            User usuario41 = new User(100000031L, "Fernando Lara", "fernando.lara@gmail.com");
            User usuario42 = new User(100000032L, "Mónica Quintero", "monica.quintero@hotmail.com");
            User usuario43 = new User(100000033L, "Oscar Bermúdez", "oscar.bermudez@yahoo.com");
            User usuario44 = new User(100000034L, "Patricia Cruz", "patricia.cruz@outlook.com");
            User usuario45 = new User(100000035L, "Rodrigo Castaño", "rodrigo.castano@mail.com");
            User usuario46 = new User(100000036L, "Diana Pacheco", "diana.pacheco@gmail.com");
            User usuario47 = new User(100000037L, "Enrique Duarte", "enrique.duarte@hotmail.com");
            User usuario48 = new User(100000038L, "Teresa Galindo", "teresa.galindo@yahoo.com");
            User usuario49 = new User(100000039L, "Pablo Figueroa", "pablo.figueroa@outlook.com");
            User usuario50 = new User(100000040L, "Silvia Córdoba", "silvia.cordoba@mail.com");
            User usuario51 = new User(100000041L, "Gustavo León", "gustavo.leon@gmail.com");
            User usuario52 = new User(100000042L, "Rosa Villamizar", "rosa.villamizar@hotmail.com");
            User usuario53 = new User(100000043L, "Héctor Barrera", "hector.barrera@yahoo.com");
            User usuario54 = new User(100000044L, "Luz Camacho", "luz.camacho@outlook.com");
            User usuario55 = new User(100000045L, "Alberto Moncada", "alberto.moncada@mail.com");
            User usuario56 = new User(100000046L, "Gloria Sepúlveda", "gloria.sepulveda@gmail.com");
            User usuario57 = new User(100000047L, "Raúl Cifuentes", "raul.cifuentes@hotmail.com");
            User usuario58 = new User(100000048L, "Adriana Velasco", "adriana.velasco@yahoo.com");
            User usuario59 = new User(100000049L, "Jorge Arévalo", "jorge.arevalo@outlook.com");
            User usuario60 = new User(100000050L, "Beatriz Montoya", "beatriz.montoya@mail.com");
            User usuario61 = new User(100000051L, "César Pineda", "cesar.pineda@gmail.com");
            User usuario62 = new User(100000052L, "Inés Cárdenas", "ines.cardenas@hotmail.com");
            User usuario63 = new User(100000053L, "Manuel Orozco", "manuel.orozco@yahoo.com");
            User usuario64 = new User(100000054L, "Verónica Niño", "veronica.nino@outlook.com");
            User usuario65 = new User(100000055L, "Arturo Bernal", "arturo.bernal@mail.com");
            User usuario66 = new User(100000056L, "Liliana Pedraza", "liliana.pedraza@gmail.com");
            User usuario67 = new User(100000057L, "Eduardo Cuellar", "eduardo.cuellar@hotmail.com");
            User usuario68 = new User(100000058L, "Marina Trujillo", "marina.trujillo@yahoo.com");
            User usuario69 = new User(100000059L, "Sergio Palacios", "sergio.palacios@outlook.com");
            User usuario70 = new User(100000060L, "Estela Giraldo", "estela.giraldo@mail.com");
            User usuario71 = new User(100000061L, "Ramón Valencia", "ramon.valencia@gmail.com");
            User usuario72 = new User(100000062L, "Amparo Carvajal", "amparo.carvajal@hotmail.com");
            User usuario73 = new User(100000063L, "Jaime Londoño", "jaime.londono@yahoo.com");
            User usuario74 = new User(100000064L, "Olga Betancourt", "olga.betancourt@outlook.com");
            User usuario75 = new User(100000065L, "Víctor Arango", "victor.arango@mail.com");
            User usuario76 = new User(100000066L, "Pilar Jaramillo", "pilar.jaramillo@gmail.com");
            User usuario77 = new User(100000067L, "Ernesto Zapata", "ernesto.zapata@hotmail.com");
            User usuario78 = new User(100000068L, "Esperanza Ocampo", "esperanza.ocampo@yahoo.com");
            User usuario79 = new User(100000069L, "Ángel Taborda", "angel.taborda@outlook.com");
            User usuario80 = new User(100000070L, "Fabiola Correa", "fabiola.correa@mail.com");
            User usuario81 = new User(100000071L, "Germán Osorio", "german.osorio@gmail.com");
            User usuario82 = new User(100000072L, "Cecilia Duque", "cecilia.duque@hotmail.com");
            User usuario83 = new User(100000073L, "Alfredo Toro", "alfredo.toro@yahoo.com");
            User usuario84 = new User(100000074L, "Constanza Uribe", "constanza.uribe@outlook.com");
            User usuario85 = new User(100000075L, "Leonardo Arias", "leonardo.arias@mail.com");
            User usuario86 = new User(100000076L, "Alicia Henao", "alicia.henao@gmail.com");
            User usuario87 = new User(100000077L, "Rubén Gallego", "ruben.gallego@hotmail.com");
            User usuario88 = new User(100000078L, "Elvira Roldán", "elvira.roldan@yahoo.com");
            User usuario89 = new User(100000079L, "Mauricio Serna", "mauricio.serna@outlook.com");
            User usuario90 = new User(100000080L, "Graciela Botero", "graciela.botero@mail.com");
            User usuario91 = new User(100000081L, "Hernán Marulanda", "hernan.marulanda@gmail.com");
            User usuario92 = new User(100000082L, "Blanca Echeverri", "blanca.echeverri@hotmail.com");
            User usuario93 = new User(100000083L, "Gonzalo Mejía", "gonzalo.mejia@yahoo.com");
            User usuario94 = new User(100000084L, "Nora Saldarriaga", "nora.saldarriaga@outlook.com");
            User usuario95 = new User(100000085L, "Armando Posada", "armando.posada@mail.com");
            User usuario96 = new User(100000086L, "Dora Bustamante", "dora.bustamante@gmail.com");
            User usuario97 = new User(100000087L, "Ignacio Zuluaga", "ignacio.zuluaga@hotmail.com");
            User usuario98 = new User(100000088L, "Stella Montealegre", "stella.montealegre@yahoo.com");
            User usuario99 = new User(100000089L, "Fabio Restrepo", "fabio.restrepo@outlook.com");
            User usuario100 = new User(100000090L, "Marta Aristizábal", "marta.aristizabal@mail.com");

            storage.addUser(usuario1);
            storage.addUser(usuario2);
            storage.addUser(usuario3);
            storage.addUser(usuario4);
            storage.addUser(usuario5);
            storage.addUser(usuario6);
            storage.addUser(usuario7);
            storage.addUser(usuario8);
            storage.addUser(usuario9);
            storage.addUser(usuario10);
            storage.addUser(usuario11);
            storage.addUser(usuario12);
            storage.addUser(usuario13);
            storage.addUser(usuario14);
            storage.addUser(usuario15);
            storage.addUser(usuario16);
            storage.addUser(usuario17);
            storage.addUser(usuario18);
            storage.addUser(usuario19);
            storage.addUser(usuario20);
            storage.addUser(usuario21);
            storage.addUser(usuario22);
            storage.addUser(usuario23);
            storage.addUser(usuario24);
            storage.addUser(usuario25);
            storage.addUser(usuario26);
            storage.addUser(usuario27);
            storage.addUser(usuario28);
            storage.addUser(usuario29);
            storage.addUser(usuario30);
            storage.addUser(usuario31);
            storage.addUser(usuario32);
            storage.addUser(usuario33);
            storage.addUser(usuario34);
            storage.addUser(usuario35);
            storage.addUser(usuario36);
            storage.addUser(usuario37);
            storage.addUser(usuario38);
            storage.addUser(usuario39);
            storage.addUser(usuario40);
            storage.addUser(usuario41);
            storage.addUser(usuario42);
            storage.addUser(usuario43);
            storage.addUser(usuario44);
            storage.addUser(usuario45);
            storage.addUser(usuario46);
            storage.addUser(usuario47);
            storage.addUser(usuario48);
            storage.addUser(usuario49);
            storage.addUser(usuario50);
            storage.addUser(usuario51);
            storage.addUser(usuario52);
            storage.addUser(usuario53);
            storage.addUser(usuario54);
            storage.addUser(usuario55);
            storage.addUser(usuario56);
            storage.addUser(usuario57);
            storage.addUser(usuario58);
            storage.addUser(usuario59);
            storage.addUser(usuario60);
            storage.addUser(usuario61);
            storage.addUser(usuario62);
            storage.addUser(usuario63);
            storage.addUser(usuario64);
            storage.addUser(usuario65);
            storage.addUser(usuario66);
            storage.addUser(usuario67);
            storage.addUser(usuario68);
            storage.addUser(usuario69);
            storage.addUser(usuario70);
            storage.addUser(usuario71);
            storage.addUser(usuario72);
            storage.addUser(usuario73);
            storage.addUser(usuario74);
            storage.addUser(usuario75);
            storage.addUser(usuario76);
            storage.addUser(usuario77);
            storage.addUser(usuario78);
            storage.addUser(usuario79);
            storage.addUser(usuario80);
            storage.addUser(usuario81);
            storage.addUser(usuario82);
            storage.addUser(usuario83);
            storage.addUser(usuario84);
            storage.addUser(usuario85);
            storage.addUser(usuario86);
            storage.addUser(usuario87);
            storage.addUser(usuario88);
            storage.addUser(usuario89);
            storage.addUser(usuario90);
            storage.addUser(usuario91);
            storage.addUser(usuario92);
            storage.addUser(usuario93);
            storage.addUser(usuario94);
            storage.addUser(usuario95);
            storage.addUser(usuario96);
            storage.addUser(usuario97);
            storage.addUser(usuario98);
            storage.addUser(usuario99);
            storage.addUser(usuario100);

            storage.addUser(usuario100); // Intento de agregar un usuario con CC duplicado

            storage.getAllUsers();
            System.out.println("-----------------------------");
            System.out.println("La cantidad de usuarios almacenados es:" + storage.getUserCount());
            System.out.println("-----------------------------");

            // Búsqueda por hashing — usuario existente cercano al inicio
            long startTime = System.nanoTime();
            User userBusqueda=storage.getUser(123456789L);
            long endTime = System.nanoTime();
            System.out.println("\n=== Usuario encontrado (Hashing) ===");
            System.out.println("Nombre: " + userBusqueda.getCc());
            System.out.println("CC: " + userBusqueda.getName());
            System.out.println("Correo: " + userBusqueda.getEmail());
            System.out.println("Tiempo getUser (hashing):    " + (endTime - startTime) + " ns (" + (endTime - startTime) / 1_000_000.0 + " ms)");

            // Búsqueda secuencial — usuario existente cercano al inicio
            startTime = System.nanoTime();
            userBusqueda=storage.getUserSeq(123456789L);
            endTime = System.nanoTime();
            System.out.println("\n=== Usuario encontrado (búsqueda secuencial) ===");
            System.out.println("Nombre: " + userBusqueda.getCc());
            System.out.println("CC: " + userBusqueda.getName());
            System.out.println("Correo: " + userBusqueda.getEmail());
            System.out.println("Tiempo getUserSeq (secuencial): " + (endTime - startTime) + " ns (" + (endTime - startTime) / 1_000_000.0 + " ms)");

            System.out.println("-----------------------------");

            // Búsqueda por hashing — usuario existente cercano al inicio
            startTime = System.nanoTime();
            userBusqueda=storage.getUser(100000037L);
            endTime = System.nanoTime();
            System.out.println("\n=== Usuario encontrado (Hashing) ===");
            System.out.println("Nombre: " + userBusqueda.getCc());
            System.out.println("CC: " + userBusqueda.getName());
            System.out.println("Correo: " + userBusqueda.getEmail());
            System.out.println("Tiempo getUser (hashing):    " + (endTime - startTime) + " ns (" + (endTime - startTime) / 1_000_000.0 + " ms)");

            // Búsqueda secuencial — usuario existente cercano al inicio
            startTime = System.nanoTime();
            userBusqueda=storage.getUserSeq(100000037L);
            endTime = System.nanoTime();
            System.out.println("\n=== Usuario encontrado (búsqueda secuencial) ===");
            System.out.println("Nombre: " + userBusqueda.getCc());
            System.out.println("CC: " + userBusqueda.getName());
            System.out.println("Correo: " + userBusqueda.getEmail());
            System.out.println("Tiempo getUserSeq (secuencial): " + (endTime - startTime) + " ns (" + (endTime - startTime) / 1_000_000.0 + " ms)");

            System.out.println("-----------------------------");

            // Búsqueda por hashing — usuario inexistente
            startTime = System.nanoTime();
            storage.getUser(999999999L);
            endTime = System.nanoTime();
            System.out.println("Tiempo getUser (hashing):    " + (endTime - startTime) + " ns (" + (endTime - startTime) / 1_000_000.0 + " ms)");

            // Búsqueda secuencial — usuario inexistente
            startTime = System.nanoTime();
            storage.getUserSeq(999999999L);
            endTime = System.nanoTime();
            System.out.println("Tiempo getUserSeq (secuencial): " + (endTime - startTime) + " ns (" + (endTime - startTime) / 1_000_000.0 + " ms)");

            // ===================== MENÚ INTERACTIVO =====================
            Scanner scanner = new Scanner(System.in);
            System.out.println("\n========================================");
            System.out.println("  Bienvenido al Sistema de Usuarios");
            System.out.println("  (Extendible Hashing Storage)");
            System.out.println("========================================");

            boolean running = true;
            while (running) {
                System.out.println("\n--- Menú Principal ---");
                System.out.println("1. Agregar usuario");
                System.out.println("2. Buscar usuario por Hashing");
                System.out.println("3. Buscar usuario por búsqueda Secuencial");
                System.out.println("4. Mostrar todos los usuarios");
                System.out.println("5. Mostrar cantidad de usuarios");
                System.out.println("6. Salir");
                System.out.print("Seleccione una opción: ");

                String opcion = scanner.nextLine().trim();

                switch (opcion) {
                    case "1":
                        System.out.print("Ingrese la CC: ");
                        long cc = Long.parseLong(scanner.nextLine().trim());
                        System.out.print("Ingrese el nombre: ");
                        String nombre = scanner.nextLine().trim();
                        System.out.print("Ingrese el email: ");
                        String email = scanner.nextLine().trim();

                        User nuevoUsuario = new User(cc, nombre, email);
                        boolean agregado = storage.addUser(nuevoUsuario);
                        if (agregado) {
                            System.out.println("✔ Usuario agregado exitosamente.");
                        } else {
                            System.out.println("✘ No se pudo agregar el usuario (CC duplicada).");
                        }
                        break;

                    case "2":
                        System.out.print("Ingrese la CC a buscar: ");
                        long ccHash = Long.parseLong(scanner.nextLine().trim());

                        startTime = System.nanoTime();
                        User resultHash = storage.getUser(ccHash);
                        endTime = System.nanoTime();

                        if (resultHash != null) {
                            System.out.println("\n=== Usuario encontrado (Hashing) ===");
                            System.out.println("  CC:     " + resultHash.getCc());
                            System.out.println("  Nombre: " + resultHash.getName());
                            System.out.println("  Email:  " + resultHash.getEmail());
                        } else {
                            System.out.println("Usuario no encontrado.");
                        }
                        System.out.println("Tiempo: " + (endTime - startTime) + " ns (" + (endTime - startTime) / 1_000_000.0 + " ms)");
                        break;

                    case "3":
                        System.out.print("Ingrese la CC a buscar: ");
                        long ccSeq = Long.parseLong(scanner.nextLine().trim());

                        startTime = System.nanoTime();
                        User resultSeq = storage.getUserSeq(ccSeq);
                        endTime = System.nanoTime();

                        if (resultSeq != null) {
                            System.out.println("\n=== Usuario encontrado (Secuencial) ===");
                            System.out.println("  CC:     " + resultSeq.getCc());
                            System.out.println("  Nombre: " + resultSeq.getName());
                            System.out.println("  Email:  " + resultSeq.getEmail());
                        } else {
                            System.out.println("Usuario no encontrado.");
                        }
                        System.out.println("Tiempo: " + (endTime - startTime) + " ns (" + (endTime - startTime) / 1_000_000.0 + " ms)");
                        break;

                    case "4":
                        storage.getAllUsers();
                        break;

                    case "5":
                        System.out.println("Cantidad de usuarios almacenados: " + storage.getUserCount());
                        break;

                    case "6":
                        System.out.println("¡Hasta luego!");
                        running = false;
                        break;

                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                        break;
                }
            }
            scanner.close();



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
