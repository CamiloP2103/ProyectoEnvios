/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaCarga;

import ObjetosProyecto.*;
import EstructurasDeDatos.*;
import LogicaCarga.ZonasEnvio.Zona;
import java.util.Scanner;

/**
 * Clase principal que gestiona el sistema de envío de cajas
 * @author juan
 * @author Camilo
 * @author Rodrigo
 */
public class SistemaEnvios {
    
    private Cola<Caja> colaCajas;
    private Almacen almacenNorte;
    private Almacen almacenSur;
    private Almacen almacenEste;
    private Almacen almacenOeste;
    private Almacen almacenCentro;
    private Camion camionNorte;
    private Camion camionSur;
    private Camion camionEste;
    private Camion camionOeste;
    private Camion camionCentro;
    private EstadisticasZona estadisticasNorte;
    private EstadisticasZona estadisticasSur;
    private EstadisticasZona estadisticasEste;
    private EstadisticasZona estadisticasOeste;
    private EstadisticasZona estadisticasCentro;
    private int contadorCajas;
    
    public SistemaEnvios() {
        // Inicializar estructuras
        colaCajas = new Cola<>(50);
        
        // Crear almacenes por zona
        almacenNorte = new Almacen(Zona.NORTE);
        almacenSur = new Almacen(Zona.SUR);
        almacenEste = new Almacen(Zona.ESTE);
        almacenOeste = new Almacen(Zona.OESTE);
        almacenCentro = new Almacen(Zona.CENTRO);
        
        // Crear camiones por zona
        camionNorte = new Camion(Zona.NORTE);
        camionSur = new Camion(Zona.SUR);
        camionEste = new Camion(Zona.ESTE);
        camionOeste = new Camion(Zona.OESTE);
        camionCentro = new Camion(Zona.CENTRO);
        
        // Inicializar estadísticas
        estadisticasNorte = new EstadisticasZona(Zona.NORTE);
        estadisticasSur = new EstadisticasZona(Zona.SUR);
        estadisticasEste = new EstadisticasZona(Zona.ESTE);
        estadisticasOeste = new EstadisticasZona(Zona.OESTE);
        estadisticasCentro = new EstadisticasZona(Zona.CENTRO);
        
        contadorCajas = 0;
    }
    
    /**
     * Recibe una nueva caja y la encola para su procesamiento
     * @param caja La caja a recibir
     */
    public void recibirCaja(Caja caja) {
        colaCajas.encolar(caja);
        System.out.println("Caja recibida y encolada correctamente.");
    }
    
    /**
     * Muestra todas las cajas en la cola de recepción
     */
    public void mostrarCajasEnCola() {
        if (colaCajas.estaVacia()) {
            System.out.println("No hay cajas en la cola de recepción.");
            return;
        }
        
        System.out.println("\n=== CAJAS EN COLA DE RECEPCIÓN ===");
        int contador = 1;
        
        // Crear una cola temporal para no perder los elementos
        Cola<Caja> colaTemp = new Cola<>(colaCajas.tamano());
        
        while (!colaCajas.estaVacia()) {
            Caja caja = colaCajas.desencolar();
            System.out.println(contador + ". ID: " + caja.getGuia().getIdCaja() +
                    ", Cliente: " + caja.getGuia().getCliente().getNombre() +
                    ", Destino: " + caja.getGuia().getDireccionDestino() +
                    ", Peso: " + caja.getPesoCaja() + " kg");
            colaTemp.encolar(caja);
            contador++;
        }
        
        // Restaurar la cola original
        while (!colaTemp.estaVacia()) {
            colaCajas.encolar(colaTemp.desencolar());
        }
    }
    
    /**
     * Procesa las cajas en la cola y las envía a los almacenes correspondientes según su zona
     */
    public void procesarCajasPorZona() {
        if (colaCajas.estaVacia()) {
            System.out.println("No hay cajas para procesar en la cola.");
            return;
        }
        
        int procesadas = 0;
        
        while (!colaCajas.estaVacia()) {
            Caja caja = colaCajas.desencolar();
            GuiaDeEnvio guia = caja.getGuia();
            String direccion = guia.getDireccionDestino().toUpperCase();
            
            // Determinar la zona basada en la dirección de destino
            if (direccion.contains("NORTE")) {
                almacenNorte.agregarCaja(caja);
            } else if (direccion.contains("SUR")) {
                almacenSur.agregarCaja(caja);
            } else if (direccion.contains("ESTE")) {
                almacenEste.agregarCaja(caja);
            } else if (direccion.contains("OESTE")) {
                almacenOeste.agregarCaja(caja);
            } else {
                almacenCentro.agregarCaja(caja);
            }
            
            procesadas++;
        }
        
        System.out.println("Se procesaron " + procesadas + " cajas y se enviaron a sus respectivos almacenes.");
    }
    
    /**
     * Muestra las cajas en un almacén específico
     * @param zona La zona del almacén a mostrar
     */
    public void mostrarCajasEnAlmacen(Zona zona) {
        Almacen almacen = getAlmacenPorZona(zona);
        
        if (almacen.estaVacio()) {
            System.out.println("El almacén de la zona " + zona.getNombre() + " está vacío.");
            return;
        }
        
        System.out.println("\n=== CAJAS EN ALMACÉN " + zona.getNombre().toUpperCase() + " ===");
        int contador = 1;
        
        for (Caja caja : almacen.getCajas()) {
            System.out.println(contador + ". ID: " + caja.getGuia().getIdCaja() +
                    ", Cliente: " + caja.getGuia().getCliente().getNombre() +
                    ", Destino: " + caja.getGuia().getDireccionDestino() +
                    ", Distancia: " + caja.getGuia().getDistanciaEnvio() + " km" +
                    ", Peso: " + caja.getPesoCaja() + " kg");
            contador++;
        }
    }
    
    /**
     * Ordena las cajas en un almacén específico por distancia de envío (menor a mayor)
     * @param zona La zona del almacén a ordenar
     */
    public void ordenarCajasEnAlmacen(Zona zona) {
        Almacen almacen = getAlmacenPorZona(zona);
        
        if (almacen.estaVacio()) {
            System.out.println("El almacén de la zona " + zona.getNombre() + " está vacío. No hay cajas para ordenar.");
            return;
        }
        
        almacen.ordenarPorDistancia();
        System.out.println("Cajas en el almacén " + zona.getNombre() + " ordenadas por distancia (menor a mayor).");
    }
    
    /**
     * Carga cajas desde un almacén específico al camión correspondiente
     * @param zona La zona del almacén y camión
     */
    public void cargarCamion(Zona zona) {
        Almacen almacen = getAlmacenPorZona(zona);
        Camion camion = getCamionPorZona(zona);
        
        if (almacen.estaVacio()) {
            System.out.println("El almacén de la zona " + zona.getNombre() + " está vacío. No hay cajas para cargar.");
            return;
        }
        
        // Ordenar las cajas por distancia primero
        almacen.ordenarPorDistancia();
        
        // Crear una lista temporal para almacenar las cajas que no se pudieron cargar
        Lista<Caja> cajasNoCargadas = new Lista<>(10);
        int cargadas = 0;
        
        // Cargar cajas desde el almacén al camión
        while (!almacen.estaVacio()) {
            Caja caja = almacen.getCajas().obtenerDe(0);
            almacen.getCajas().eliminarDe(0);
            
            // Intentar cargar la caja en el camión
            if (camion.cargarCaja(caja)) {
                cargadas++;
            } else {
                // Si no se puede cargar, guardar en la lista temporal
                cajasNoCargadas.agregarAlFinal(caja);
            }
        }
        
        // Devolver al almacén las cajas que no se pudieron cargar
        while (!cajasNoCargadas.estaVacia()) {
            almacen.agregarCaja(cajasNoCargadas.obtenerDe(0));
            cajasNoCargadas.eliminarDe(0);
        }
        
        System.out.println("Se cargaron " + cargadas + " cajas en el camión de la zona " + zona.getNombre() + ".");
    }
    
    /**
     * Muestra las cajas cargadas en un camión específico
     * @param zona La zona del camión a mostrar
     */
    public void mostrarCajasEnCamion(Zona zona) {
        Camion camion = getCamionPorZona(zona);
        
        if (camion.estaVacio()) {
            System.out.println("El camión de la zona " + zona.getNombre() + " está vacío.");
            return;
        }
        
        System.out.println("\n=== CAJAS EN CAMIÓN " + zona.getNombre().toUpperCase() + " ===");
        System.out.println("Carga actual: " + String.format("%.2f", camion.getPesoActual()) + 
                " kg (" + String.format("%.2f", camion.getPorcentajeCarga()) + "% de capacidad)");
        
        // Para mostrar las cajas necesitamos crear una pila temporal
        Pila<Caja> pilaTemp = new Pila<>(camion.numeroCajas());
        int contador = 1;
        
        while (!camion.getCajas().isEmpty()) {
            Caja caja = camion.getCajas().pop();
            System.out.println(contador + ". ID: " + caja.getGuia().getIdCaja() +
                    ", Cliente: " + caja.getGuia().getCliente().getNombre() +
                    ", Destino: " + caja.getGuia().getDireccionDestino() +
                    ", Distancia: " + caja.getGuia().getDistanciaEnvio() + " km" +
                    ", Peso: " + caja.getPesoCaja() + " kg");
            pilaTemp.push(caja);
            contador++;
        }
        
        // Restaurar la pila original
        while (!pilaTemp.isEmpty()) {
            camion.getCajas().push(pilaTemp.pop());
        }
    }
    
    /**
     * Despacha un camión específico si cumple con los requisitos de carga
     * @param zona La zona del camión a despachar
     */
    public void despacharCamion(Zona zona) {
        Camion camion = getCamionPorZona(zona);
        EstadisticasZona estadisticas = getEstadisticasPorZona(zona);
        
        if (camion.estaVacio()) {
            System.out.println("El camión de la zona " + zona.getNombre() + " está vacío. No hay cajas para entregar.");
            return;
        }
        
        if (!camion.puedeDespachar()) {
            System.out.println("El camión de la zona " + zona.getNombre() + 
                    " no puede ser despachado porque no alcanza el 50% de su capacidad de carga.");
            System.out.println("Carga actual: " + String.format("%.2f", camion.getPesoActual()) + 
                    " kg (" + String.format("%.2f", camion.getPorcentajeCarga()) + "% de capacidad)");
            return;
        }
        
        // Despachar el camión y obtener las cajas entregadas
        Lista<Caja> cajasEntregadas = camion.despachar();
        
        System.out.println("\n=== ORDEN DE ENTREGA DE CAJAS DEL CAMIÓN " + zona.getNombre().toUpperCase() + " ===");
        int contador = 1;
        
        // Las cajas se entregan desde la de menor distancia a la de mayor distancia
        for (Caja caja : cajasEntregadas) {
            System.out.println(contador + ". ID: " + caja.getGuia().getIdCaja() +
                    ", Cliente: " + caja.getGuia().getCliente().getNombre() +
                    ", Destino: " + caja.getGuia().getDireccionDestino() +
                    ", Distancia: " + caja.getGuia().getDistanciaEnvio() + " km");
            
            // Registrar la entrega en las estadísticas
            estadisticas.registrarEntrega(caja);
            contador++;
        }
        
        System.out.println("\nCamión despachado exitosamente. Se entregaron " + 
                (contador - 1) + " cajas en la zona " + zona.getNombre() + ".");
    }
    
    /**
     * Muestra las estadísticas de envío para una zona específica
     * @param zona La zona de la cual mostrar estadísticas
     */
    public void mostrarEstadisticasZona(Zona zona) {
        EstadisticasZona estadisticas = getEstadisticasPorZona(zona);
        System.out.println("\n" + estadisticas.toString());
    }
    
    /**
     * Crea un nuevo cliente para el sistema
     * @return El cliente creado
     */
    public Cliente crearCliente(Scanner scanner) {
        System.out.println("\n=== CREAR CLIENTE ===");
        
        System.out.print("Nombre del cliente: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        
        // Generar un ID único para el cliente
        String idCliente = "CLI" + System.currentTimeMillis();
        
        return new Cliente(idCliente, nombre, telefono, email, direccion);
    }
    
    /**
     * Crea una nueva caja con su guía de envío
     * @param cliente El cliente que envía la caja
     * @return La caja creada
     */
    public Caja crearCaja(Cliente cliente, Scanner scanner) {
        System.out.println("\n=== CREAR CAJA ===");
        
        double alto, ancho, largo, peso, distancia;
        
        System.out.print("Alto de la caja (cm): ");
        alto = Double.parseDouble(scanner.nextLine());
        
        System.out.print("Ancho de la caja (cm): ");
        ancho = Double.parseDouble(scanner.nextLine());
        
        System.out.print("Largo de la caja (cm): ");
        largo = Double.parseDouble(scanner.nextLine());
        
        System.out.print("Peso de la caja (kg): ");
        peso = Double.parseDouble(scanner.nextLine());
        
        // Seleccionar zona de destino
        Zona zonaDestino = seleccionarZona(scanner);
        
        // Crear dirección de destino basada en la zona
        System.out.print("Ciudad de destino: ");
        String ciudad = scanner.nextLine();
        
        String direccionDestino = ciudad + ", Zona " + zonaDestino.getNombre();
        
        System.out.print("Distancia de envío (km): ");
        distancia = Double.parseDouble(scanner.nextLine());
        
        // Generar ID único para la caja
        String idCaja = "CAJA" + (++contadorCajas);
        
        // Crear la guía de envío
        double pesoFacturado = Math.max(peso, (alto * ancho * largo) / 5000.0);
        GuiaDeEnvio guia = new GuiaDeEnvio(idCaja, cliente, direccionDestino, distancia, pesoFacturado);
        
        // Crear la caja con la guía
        return new Caja(alto, ancho, largo, peso, guia);
    }
    
    /**
     * Permite al usuario seleccionar una zona
     * @return La zona seleccionada
     */
    private Zona seleccionarZona(Scanner scanner) {
        System.out.println("\n=== SELECCIONAR ZONA DE DESTINO ===");
        System.out.println("1. Norte");
        System.out.println("2. Sur");
        System.out.println("3. Este");
        System.out.println("4. Oeste");
        System.out.println("5. Centro");
        
        int opcion = 0;
        while (opcion < 1 || opcion > 5) {
            System.out.print("Seleccione una zona (1-5): ");
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }
        
        switch (opcion) {
            case 1: return Zona.NORTE;
            case 2: return Zona.SUR;
            case 3: return Zona.ESTE;
            case 4: return Zona.OESTE;
            default: return Zona.CENTRO;
        }
    }
    
    /**
     * Obtiene el almacén correspondiente a una zona
     * @param zona La zona del almacén
     * @return El almacén de la zona especificada
     */
    private Almacen getAlmacenPorZona(Zona zona) {
        switch (zona) {
            case NORTE: return almacenNorte;
            case SUR: return almacenSur;
            case ESTE: return almacenEste;
            case OESTE: return almacenOeste;
            default: return almacenCentro;
        }
    }
    
    /**
     * Obtiene el camión correspondiente a una zona
     * @param zona La zona del camión
     * @return El camión de la zona especificada
     */
    private Camion getCamionPorZona(Zona zona) {
        switch (zona) {
            case NORTE: return camionNorte;
            case SUR: return camionSur;
            case ESTE: return camionEste;
            case OESTE: return camionOeste;
            default: return camionCentro;
        }
    }
    
    /**
     * Obtiene las estadísticas correspondientes a una zona
     * @param zona La zona de las estadísticas
     * @return Las estadísticas de la zona especificada
     */
    private EstadisticasZona getEstadisticasPorZona(Zona zona) {
        switch (zona) {
            case NORTE: return estadisticasNorte;
            case SUR: return estadisticasSur;
            case ESTE: return estadisticasEste;
            case OESTE: return estadisticasOeste;
            default: return estadisticasCentro;
        }
    }
    
}
