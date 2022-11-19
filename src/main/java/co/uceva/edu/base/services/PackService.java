package co.uceva.edu.base.services;

import co.uceva.edu.base.models.Empleado;
import co.uceva.edu.base.models.Pack;
import co.uceva.edu.base.models.Usuario;
import co.uceva.edu.base.repositories.EmpleadoRepository;
import co.uceva.edu.base.repositories.PackRepository;

import java.io.FileWriter;
import java.util.List;

public class PackService {
    PackRepository paqueteRepository;

    public PackService() {
        paqueteRepository = new PackRepository();
    }

    public List<Pack> listar (){
        return paqueteRepository.listar();
    }

    public boolean crear(Pack paquete){
        if(paqueteRepository.consulta(paquete.getId()).size() > 0){
            return false;
        }else{
            return paqueteRepository.crear(paquete);
        }
    }
    public List<Pack> consultar (String id ){
        return paqueteRepository.consulta(id);
    }

    public List<Pack> consultarXTipo (String tipo ){
        return paqueteRepository.consultaXTipo(tipo);
    }

    public boolean eliminarPack(String identificacion) {
        return paqueteRepository.eliminarPack(identificacion);
    }

    public boolean actualizarPack(Pack paquete) {
        return paqueteRepository.actualizarPack(paquete);
    }

    public void generarCSV(){
        List<Pack> listado = paqueteRepository.listar();

        FileWriter fw = null;
        boolean error = false;
        try {
            fw = new FileWriter("z_paquetes.csv",false);
        } catch (Exception e) {
            error = true;
            System.out.println("Error al crear el archivo");
        }

        if(!error){
            for (int i = 0; i < listado.size(); i++) {

                try {
                    fw.write(listado.get(i).getId() + ";" + listado.get(i).getDescripcion() + ";" +
                            listado.get(i).getPuntosVisita()+ ";" + listado.get(i).getsN()+ ";" +
                            listado.get(i).getDias() + ";" + listado.get(i).getNoches()+";"+
                            listado.get(i).getPrecio() + ";" + listado.get(i).getTipo()+ ";"+
                            listado.get(i).getFecha() + ";"+"\r\n");
                } catch (Exception e) {
                    System.out.println("Error al guardar en el archivo");
                    break;
                }
            }//fin del for

            try {
                fw.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar el archivo");
            }
        }

    }
}
