/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

import LogicaCarga.SistemaEnvios;
import LogicaCarga.ZonasEnvio;
import ObjetosProyecto.Caja;
import ObjetosProyecto.Cliente;
import java.util.Scanner;

/**
 * Clase principal para la interfaz de usuario del sistema de gestión
 * @author juan
 * @author Camilo
 * @author Rodrigo
 */

public class InterfazUsuario {
    
    private SistemaEnvios sistema;
    private Scanner scanner;
    
    public InterfazUsuario() {
        sistema = new SistemaEnvios();
        scanner = new Scanner(System.in);
    }
    
    /**
     * Muestra el menú principal y gestiona la interacción con el usuario
     */
    public void mostrarMenu() {
        int opcion = 0;
        
        do {
            System.out.println("\n=== SISTEMA DE GESTIÓN DE ENVÍOS ===");
            System.out.println("1. Registrar nueva caja");
            System.out.println("2. Mostrar cajas en cola");
            System.out.println("3. Procesar cajas por zona");
            System.out.println("4. Mostrar cajas en almacén");
            System.out.println("5. Ordenar cajas en almacén por distancia");
            System.out.println("6. Cargar camión");
            System.out.println("7. Mostrar cajas en camión");
            System.out.println("8. Despachar camión");
            System.out.println("9. Mostrar estadísticas por zona");
            System.out.println("0. Salir");
            
            System.out.print("\nSeleccione una opción: ");
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                
                switch (opcion) {
                    case 1:
                        registrarNuevaCaja();
                        break;
                    case 2:
                        sistema.mostrarCajasEnCola();
                        break;
                    case 3:
                        sistema.procesarCajasPorZona();
                        break;
                    case 4:
                        mostrarCajasEnAlmacen();
                        break;
                    case 5:
                        ordenarCajasEnAlmacen();
                        break;
                    case 6:
                        cargarCamion();
                        break;
                    case 7:
                        mostrarCajasEnCamion();
                        break;
                    case 8:
                        despacharCamion();
                        break;
                    case 9:
                        mostrarEstadisticas();
                        break;
                    case 0:
                        System.out.println("Gracias por usar el Sistema de Gestión de Envíos.");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
            }
            
            if (opcion != 0) {
                System.out.print("\nPresione Enter para continuar...");
                scanner.nextLine();
            }
            
        } while (opcion != 0);
        
        scanner.close();
    }
    
    /**
     * Registra una nueva caja en el sistema
     */
    private void registrarNuevaCaja() {
        
        Cliente cliente = sistema.crearCliente(scanner);
        
        
        Caja caja = sistema.crearCaja(cliente, scanner);
        
        
        sistema.recibirCaja(caja);
    }
    
    /**
     * Permite al usuario seleccionar una zona y ver las cajas en su almacén
     */
    private void mostrarCajasEnAlmacen() {
        System.out.println("\n=== MOSTRAR CAJAS EN ALMACÉN ===");
        ZonasEnvio.Zona zona = seleccionarZona();
        sistema.mostrarCajasEnAlmacen(zona);
    }
    
    /**
     * Permite al usuario seleccionar una zona y ordenar las cajas en su almacén
     */
    private void ordenarCajasEnAlmacen() {
        System.out.println("\n=== ORDENAR CAJAS EN ALMACÉN ===");
        ZonasEnvio.Zona zona = seleccionarZona();
        sistema.ordenarCajasEnAlmacen(zona);
    }
    
    /**
     * Permite al usuario seleccionar una zona y cargar su camión
     */
    private void cargarCamion() {
        System.out.println("\n=== CARGAR CAMIÓN ===");
        ZonasEnvio.Zona zona = seleccionarZona();
        sistema.cargarCamion(zona);
    }
    
    /**
     * Permite al usuario seleccionar una zona y ver las cajas en su camión
     */
    private void mostrarCajasEnCamion() {
        System.out.println("\n=== MOSTRAR CAJAS EN CAMIÓN ===");
        ZonasEnvio.Zona zona = seleccionarZona();
        sistema.mostrarCajasEnCamion(zona);
    }
    
    /**
     * Permite al usuario seleccionar una zona y despachar su camión
     */
    private void despacharCamion() {
        System.out.println("\n=== DESPACHAR CAMIÓN ===");
        ZonasEnvio.Zona zona = seleccionarZona();
        sistema.despacharCamion(zona);
    }
    
    /**
     * Permite al usuario seleccionar una zona y ver sus estadísticas
     */
    private void mostrarEstadisticas() {
        System.out.println("\n=== ESTADÍSTICAS POR ZONA ===");
        ZonasEnvio.Zona zona = seleccionarZona();
        sistema.mostrarEstadisticasZona(zona);
    }
    
    /**
     * Solicita al usuario que seleccione una zona
     * @return La zona seleccionada
     */
    private ZonasEnvio.Zona seleccionarZona() {
        System.out.println("Seleccione una zona:");
        System.out.println("1. Norte");
        System.out.println("2. Sur");
        System.out.println("3. Este");
        System.out.println("4. Oeste");
        System.out.println("5. Centro");
        
        int opcion = 0;
        while (opcion < 1 || opcion > 5) {
            System.out.print("Opción (1-5): ");
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }
        
        switch (opcion) {
            case 1: return ZonasEnvio.Zona.NORTE;
            case 2: return ZonasEnvio.Zona.SUR;
            case 3: return ZonasEnvio.Zona.ESTE;
            case 4: return ZonasEnvio.Zona.OESTE;
            default: return ZonasEnvio.Zona.CENTRO;
        }
    }
    
    /**
     * Método principal para iniciar la aplicación
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        InterfazUsuario interfaz = new InterfazUsuario();
        interfaz.mostrarMenu();
    }
    
}