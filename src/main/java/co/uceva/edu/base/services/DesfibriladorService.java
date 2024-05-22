package co.uceva.edu.base.services;
import co.uceva.edu.base.models.Desfibrilador;
import co.uceva.edu.base.models.SOE;
import co.uceva.edu.base.repositories.DesfibriladorRepository;
import java.io.FileWriter;
import java.util.List;

public class DesfibriladorService {
    DesfibriladorRepository defRepository;
    public DesfibriladorService() {
        defRepository = new DesfibriladorRepository();
    }



    public List<Desfibrilador>listarDesfibrilador (){
        return defRepository.listar();
    }

    public boolean crearDesfibrilador(Desfibrilador def){
        if(defRepository.consulta(def.getId()).size() > 0){
            return false;
        }else{
            return defRepository.crear(def);
        }
    }


    public List<Desfibrilador> consultarDesfibrilador (String id ){
        return defRepository.consulta(id);
    }

    public boolean actualizarDesfibrilador(Desfibrilador def) {
        return defRepository.actualizarDesfibrilador(def);
    }

    public boolean eliminarDesfibrilador(String identificacion) {
        return defRepository.eliminarDesfibrilador(identificacion);
    }

    public void generarCSV(){
        List<Desfibrilador> listado  = defRepository.listar();

        FileWriter fw = null;
        boolean error = false;
        try {
            fw = new FileWriter("..//z_soe.csv",false);
        } catch (Exception e) {
            error = true;
            System.out.println("Error al crear el archivo");
        }

        if(!error){
            for (int i = 0; i < listado.size(); i++) {

                try {
                    fw.write(listado.get(i).getId() + ";" + listado.get(i).getHotel() + ";" +
                            listado.get(i).getDescripcion() + ";" + listado.get(i).getPosRelativa()+
                            listado.get(i).getCoordenadas() + ";" + listado.get(i).getFechaRevis()+
                            listado.get(i).getEstado() + "\r\n");
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


