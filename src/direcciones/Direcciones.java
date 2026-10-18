package direcciones;

import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.table.DefaultTableModel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Direcciones extends JFrame implements ActionListener {

    JMenuBar mb;
    JMenu mArchivo, mBD;
    JMenuItem mBD_A, mBD_E, mBD_C, mBD_AC;

    JLabel lb1, lbID, lbCalle, lbAvenida, lbReferencia, lbColonia, lbEstado;
    JTextField txtID;
    JTextField txtCalle;
    JTextField txtAvenida;
    JTextField txtReferencia;
    JTextField txtColonia;
    JTextField txtEstado;

    JButton consultar, agregar, eliminar, actualizar;

    JTable tablaDirecciones;
    DefaultTableModel modeloTabla;
    JScrollPane scrollTabla;

    public Direcciones() {
        this.setBounds(150, 50, 850, 680);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);

        mb = new JMenuBar();
        this.setJMenuBar(mb);

        mArchivo = new JMenu("Archivo");
        mb.add(mArchivo);

        mBD = new JMenu("Base de Datos");
        mb.add(mBD);

        this.mBD_C = new JMenuItem("Consulta");
        mBD.add(mBD_C);

        this.mBD_A = new JMenuItem("Agregar");
        mBD.add(mBD_A);

        this.mBD_AC = new JMenuItem("Actualizar");
        mBD.add(mBD_AC);

        this.mBD_E = new JMenuItem("Eliminar");
        mBD.add(mBD_E);

        this.lb1 = new JLabel("Bienvenido, escriba su direccion..");
        this.lb1.setBounds(250, 15, 250, 30);
        this.add(lb1);

        this.lbID = new JLabel("ID");
        this.lbID.setBounds(150, 55, 100, 30);
        this.add(lbID);

        this.txtID = new JTextField();
        this.txtID.setBounds(255, 55, 200, 30);
        this.add(this.txtID);

        this.lbCalle = new JLabel("Calle");
        this.lbCalle.setBounds(150, 95, 100, 30);
        this.add(lbCalle);

        this.txtCalle = new JTextField();
        this.txtCalle.setBounds(255, 95, 200, 30);
        this.add(this.txtCalle);

        this.lbAvenida = new JLabel("Avenida");
        this.lbAvenida.setBounds(150, 135, 100, 30);
        this.add(lbAvenida);

        this.txtAvenida = new JTextField();
        this.txtAvenida.setBounds(255, 135, 200, 30);
        this.add(this.txtAvenida);

        this.lbReferencia = new JLabel("Referencia");
        this.lbReferencia.setBounds(150, 175, 100, 30);
        this.add(lbReferencia);

        this.txtReferencia = new JTextField();
        this.txtReferencia.setBounds(255, 175, 200, 30);
        this.add(this.txtReferencia);

        this.lbColonia = new JLabel("Colonia");
        this.lbColonia.setBounds(150, 215, 100, 30);
        this.add(lbColonia);

        this.txtColonia = new JTextField();
        this.txtColonia.setBounds(255, 215, 200, 30);
        this.add(this.txtColonia);

        this.lbEstado = new JLabel("Estado");
        this.lbEstado.setBounds(150, 255, 100, 30);
        this.add(lbEstado);

        this.txtEstado = new JTextField();
        this.txtEstado.setBounds(255, 255, 200, 30);
        this.add(this.txtEstado);

        this.consultar = new JButton("Consultar");
        this.consultar.setBounds(100, 310, 130, 30);
        this.add(consultar);

        this.agregar = new JButton("Agregar");
        this.agregar.setBounds(240, 310, 130, 30);
        this.add(this.agregar);

        this.eliminar = new JButton("Eliminar");
        this.eliminar.setBounds(380, 310, 130, 30);
        this.add(eliminar);

        this.actualizar = new JButton("Actualizar");
        this.actualizar.setBounds(520, 310, 130, 30);
        this.add(this.actualizar);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Calle");
        modeloTabla.addColumn("Avenida");
        modeloTabla.addColumn("Referencia");
        modeloTabla.addColumn("Colonia");
        modeloTabla.addColumn("Estado");

        tablaDirecciones = new JTable(modeloTabla);
        scrollTabla = new JScrollPane(tablaDirecciones);
        scrollTabla.setBounds(70, 365, 700, 200);
        this.add(scrollTabla);

        
        this.agregar.addActionListener(this);
        this.consultar.addActionListener(this);
        this.eliminar.addActionListener(this);
        this.actualizar.addActionListener(this);

        this.mBD_A.addActionListener(e -> {
            agregar();
            consultar();
        });
        this.mBD_E.addActionListener(e -> {
            eliminar();
            consultar();
        });
        this.mBD_AC.addActionListener(e -> {
            actualizar();
            consultar();
        });
        this.mBD_C.addActionListener(e -> consultar());

        this.setVisible(true);
        consultar();
    }

    public static void main(String[] args) {
        new Direcciones();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.consultar) {
            consultar();
        }

        if (e.getSource() == this.agregar) {
            agregar();
            consultar();
        }

        if (e.getSource() == this.eliminar) {
            eliminar();
            consultar();
        }

        if (e.getSource() == this.actualizar) {
            actualizar();
            consultar();
        }
    }

    public void agregar() {
        try {
            Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost/direcciones_db", "root", "");
            PreparedStatement ps = cnx.prepareStatement("INSERT INTO direccion (calle, avenida, referencia, colonia, estado) VALUES (?, ?, ?, ?, ?)");

            ps.setString(1, this.txtCalle.getText());
            ps.setString(2, this.txtAvenida.getText());
            ps.setString(3, this.txtReferencia.getText());
            ps.setString(4, this.txtColonia.getText());
            ps.setString(5, this.txtEstado.getText());

            ps.executeUpdate();
            cnx.close();

            limpiarCampos();
            System.out.println("Direccion agregada con exito.");
        } catch (SQLException ex) {
            System.out.println("Error al agregar: " + ex.getMessage());
        }
    }

    public void consultar() {
        try {
            Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost/direcciones_db", "root", "");
            PreparedStatement ps = cnx.prepareStatement("SELECT * FROM direccion");
            ResultSet rs = ps.executeQuery();

            modeloTabla.setRowCount(0);

            while (rs.next()) {
                
                Direccion dir = new Direccion(
                        rs.getInt("id"),
                        rs.getString("calle"),
                        rs.getString("avenida"),
                        rs.getString("referencia"),
                        rs.getString("colonia"),
                        rs.getString("estado")
                );

                Object[] fila = {
                    dir.getId(),
                    dir.getCalle(),
                    dir.getAvenida(),
                    dir.getReferencia(),
                    dir.getColonia(),
                    dir.getEstado()
                };
                modeloTabla.addRow(fila);
            }

            cnx.close();
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getSQLState() + " : " + ex.getMessage());
        }
    }

    public void eliminar() {
        try {
            Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost/direcciones_db", "root", "");
            PreparedStatement ps = cnx.prepareStatement("DELETE FROM direccion WHERE id = ?");

            int idIngresado = Integer.parseInt(this.txtID.getText().trim());
            ps.setInt(1, idIngresado);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Direccion eliminada con éxito.");
            } else {
                System.out.println("No se encontro ninguna direccion con ese ID.");
            }

            cnx.close();
            this.txtID.setText("");
        } catch (SQLException ex) {
            System.out.println("Error al borrar: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("Por favor ingrese un ID numerico valido.");
        }
    }

    public void actualizar() {
        try {
            Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost/direcciones_db", "root", "");
            PreparedStatement ps = cnx.prepareStatement("UPDATE direccion SET calle = ?, avenida = ?, referencia = ?, colonia = ?, estado = ? WHERE id = ?");

            int idIngresado = Integer.parseInt(this.txtID.getText().trim());

            ps.setString(1, this.txtCalle.getText());
            ps.setString(2, this.txtAvenida.getText());
            ps.setString(3, this.txtReferencia.getText());
            ps.setString(4, this.txtColonia.getText());
            ps.setString(5, this.txtEstado.getText());
            ps.setInt(6, idIngresado);

            ps.executeUpdate();

            cnx.close();
            limpiarCampos();
            this.txtID.setText("");

            System.out.println("Direccion actualizada con exito.");
        } catch (SQLException ex) {
            System.out.println("Error al actualizar: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("Por favor ingrese un ID numerico valido.");
        }
    }

    public void limpiarCampos() {
        this.txtID.setText("");
        this.txtCalle.setText("");
        this.txtAvenida.setText("");
        this.txtReferencia.setText("");
        this.txtColonia.setText("");
        this.txtEstado.setText("");
    }
}
