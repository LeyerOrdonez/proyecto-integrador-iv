package co.uceva.edu.base.services;
import co.uceva.edu.base.models.Empleado;
import co.uceva.edu.base.models.Pack;
import co.uceva.edu.base.models.Usuario;
import co.uceva.edu.base.repositories.EmpleadoRepository;

import java.io.FileWriter;
import java.util.List;

public class EmpleadoService {
    EmpleadoRepository empleadoRepository;

    public EmpleadoService() {
        empleadoRepository = new EmpleadoRepository();
    }

    public Empleado autenticar(String correo, String password){
        return empleadoRepository.autenticarEmpleado(correo,password);
    }

    public List<Empleado> listar (){
       return empleadoRepository.listar();
    }

    public boolean crear(Empleado empleado){
        if(empleadoRepository.consulta(empleado.getId()).size() > 0){
            return false;
        }else{
            return empleadoRepository.crear(empleado);
        }
    }

    public List<Empleado> consultar (int id ){
        return empleadoRepository.consulta(id);
    }

    public boolean eliminarEmpleado(int identificacion) {
        return empleadoRepository.eliminarEmpleado(identificacion);
    }

    public boolean actualizarEmpleado(Empleado empleado) {
        return empleadoRepository.actualizarEmpleado(empleado);
    }

    public void generarCSV(){
        List<Empleado> listado = empleadoRepository.listar();

        FileWriter fw = null;
        boolean error = false;
        try {
            fw = new FileWriter("z_empleados.csv",false);
        } catch (Exception e) {
            error = true;
            System.out.println("Error al crear el archivo");
        }

        if(!error){
            for (int i = 0; i < listado.size(); i++) {

                try {
                    fw.write(listado.get(i).getId() + ";" + listado.get(i).getNombre() + ";" +
                            listado.get(i).getCorreo()+ ";" + listado.get(i).getPassword()+ ";" +
                            listado.get(i).getSalario() + ";" + listado.get(i).getDepartamento()+";"+"\r\n");
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
