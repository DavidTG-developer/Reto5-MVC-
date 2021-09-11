
package Controlador;

/**
 *
 * @author David Tellez
 */
import Modelo.Planeta;
import Modelo.PlanetaDAO;
import Vista.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class Controlador implements ActionListener{
    
    PlanetaDAO dao = new PlanetaDAO();
    Planeta p=new Planeta();
    Vista vista=new Vista();
    DefaultTableModel modelo=new DefaultTableModel();

    public Controlador(Vista v){
        this.vista=v;
        this.vista.btnListar.addActionListener(this);
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnCargar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnConsultar.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnListar){
            listar(vista.tabla);
            
    }
        if(e.getSource()==vista.btnAgregar){
            add();
            limpiarTabla();
        }
        if(e.getSource()==vista.btnCargar){
            int fila=vista.tabla.getSelectedRow();
            if(fila==-1){
                JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
            }else{
                int id=Integer.parseInt((String)vista.tabla.getValueAt(fila,0).toString());
                String nom = (String)vista.tabla.getValueAt(fila, 1);
                int poblacion = Integer.parseInt((String)vista.tabla.getValueAt(fila,2).toString());
                float nivelOx= Float.parseFloat((String)vista.tabla.getValueAt(fila, 3).toString());
                boolean hab=Boolean.parseBoolean((String)vista.tabla.getValueAt(fila, 4).toString());
                vista.txtCodigo.setText(""+id);
                vista.txtNombre.setText(nom);
                vista.txtPoblacion.setText(""+poblacion);
                vista.txtNivelOxigeno.setText(""+nivelOx);
                vista.txtHabitabilidad.setText(""+hab);
                limpiarTabla();
                
            }
            
        }
        if (e.getSource()==vista.btnActualizar){
            Actualizar();
            listar(vista.tabla);
            nuevo();
            limpiarTabla();
        }
        if(e.getSource()==vista.btnEliminar){
            int fila=vista.tabla.getSelectedRow();
            
            if(fila==-1){
                JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
            }else{
                int id=Integer.parseInt((String)vista.tabla.getValueAt(fila,0).toString());
                dao.delete(id);
                JOptionPane.showMessageDialog(vista, "Registro Eliminado");
                
        }
            limpiarTabla();
        }

    }
    void nuevo() {
        vista.txtCodigo.setText("");
        vista.txtNombre.setText("");
        vista.txtPoblacion.setText("");
        vista.txtNivelOxigeno.setText("");
        vista.txtHabitabilidad.setText("");
    }
    public void Actualizar(){
        int id=Integer.parseInt(vista.txtCodigo.getText());
        String nombre = vista.txtNombre.getText();
        int poblacion= Integer.parseInt(vista.txtPoblacion.getText());
        float nivelOxigeno = Float.parseFloat(vista.txtNivelOxigeno.getText());
        boolean habitabilidad=Boolean.parseBoolean(vista.txtHabitabilidad.getText());
        p.setCodigo(id);
        p.setNombre(nombre);
        p.setPoblacion(poblacion);
        p.setNivelOxigeno(nivelOxigeno);
        p.setHabitabilidad(habitabilidad);
        
        
        int r = dao.Actualizar(p);
        if(r==1){
            JOptionPane.showMessageDialog(vista, "Usuario Actualizado");
        }else{
            JOptionPane.showMessageDialog(vista, "Error");
        }
        limpiarTabla();
    }
    public void add(){
        //int Codigo= Integer.parseInt(vista.txtCodigo.getText());
        String nombre = vista.txtNombre.getText();
        int poblacion= Integer.parseInt(vista.txtPoblacion.getText());
        float nivelOxigeno = Float.parseFloat(vista.txtNivelOxigeno.getText());
        boolean habitabilidad=Boolean.parseBoolean(vista.txtHabitabilidad.getText());
        //p.setCodigo(Codigo);
        p.setNombre(nombre);
        p.setPoblacion(poblacion);
        p.setNivelOxigeno(nivelOxigeno);
        p.setHabitabilidad(habitabilidad);
        int r=dao.agregar(p);
        if(r==1){
            JOptionPane.showMessageDialog(vista, "Usuario Agregado con Exito");
        }else{
            JOptionPane.showMessageDialog(vista, "Error");
        }
        limpiarTabla();
        
    }
    
    public void listar(JTable tabla){
        modelo =  (DefaultTableModel)tabla.getModel();
        tabla.setModel(modelo);
        List<Planeta>lista=dao.listar();
        Object[]object=new Object[5];
        for(int i=0; i< lista.size();i++){
            object[0]=lista.get(i).getCodigo();
            object[1]=lista.get(i).getNombre();
            object[2]=lista.get(i).getPoblacion();
            object[3]=lista.get(i).getNivelOxigeno();
            object[4]=lista.get(i).isHabitabilidad();
            modelo.addRow(object);
        }
        vista.tabla.setModel(modelo);
        
    }

    void limpiarTabla() {
        for (int i = 0; i < vista.tabla.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    
}
