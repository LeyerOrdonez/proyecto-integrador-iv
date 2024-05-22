package co.uceva.edu.base.services;
import co.uceva.edu.base.models.SOE;
import co.uceva.edu.base.repositories.SOERepository;
import java.io.FileWriter;
import java.util.List;

public class SOEService {
    SOERepository soeRepository;
    public SOEService() {
        soeRepository = new SOERepository();
    }



    public List<SOE> listarSOE (){
        return soeRepository.listar();
    }

    public boolean crearSOE(SOE soe){
        if(soeRepository.consulta(soe.getId()).size() > 0){
            return false;
        }else{
            return soeRepository.crear(soe);
        }
    }


    public List<SOE> consultarSOE (String id ){
        return soeRepository.consulta(id);
    }

    public boolean actualizarSOE(SOE soe) {
        return soeRepository.actualizarSOE(soe);
    }

    public boolean eliminarSOE(String identificacion) {
        return soeRepository.eliminarSOE(identificacion);
    }

    public void generarCSV(){
        List<SOE> listadoSOE  = soeRepository.listar();

        FileWriter fw = null;
        boolean error = false;
        try {
            fw = new FileWriter("..//z_soe.csv",false);
        } catch (Exception e) {
            error = true;
            System.out.println("Error al crear el archivo");
        }

        if(!error){
            for (int i = 0; i < listadoSOE.size(); i++) {

                try {
                    fw.write(listadoSOE.get(i).getId() + ";" + listadoSOE.get(i).getAvionid() + ";" +
                            listadoSOE.get(i).getCantidad() + ";" + listadoSOE.get(i).getFechaRevis()+ "\r\n");
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

