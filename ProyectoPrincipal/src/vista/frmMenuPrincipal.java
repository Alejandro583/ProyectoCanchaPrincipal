/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista; 
import config.sesion;

/**
 *
 * @author tedyf
 */
public class frmMenuPrincipal extends javax.swing.JFrame {

    sesion oSesion;
    public frmMenuPrincipal() {
        initComponents();
    }
    
    public frmMenuPrincipal(sesion pSesion)
    {
        initComponents();
        oSesion = pSesion;
        txtNombre.setText("USUARIO: " + oSesion.getNombreUsuario());
        liberarMenu();
    }
    
    public void liberarMenu()
    {
        if(oSesion.getCargo().equals("Administrador"))
        {
            m_Operacion.setVisible(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jMenu2 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jComboBox1 = new javax.swing.JComboBox<>();
        PanelPrincipal = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        txtNombre = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        m_Registro = new javax.swing.JMenu();
        mr_cliente = new javax.swing.JMenuItem();
        mr_producto = new javax.swing.JMenuItem();
        mr_proveedor = new javax.swing.JMenuItem();
        m_Operacion = new javax.swing.JMenu();
        om_venta = new javax.swing.JMenuItem();
        om_compra = new javax.swing.JMenuItem();
        om_caja = new javax.swing.JMenuItem();
        om_reserva = new javax.swing.JMenuItem();
        m_Consulta = new javax.swing.JMenu();
        cm_detalleVenta = new javax.swing.JMenuItem();
        cm_detalleCompra = new javax.swing.JMenuItem();
        cm_detalleCliente = new javax.swing.JMenuItem();
        cm_stock = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        jMenu5.setText("jMenu5");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout PanelPrincipalLayout = new javax.swing.GroupLayout(PanelPrincipal);
        PanelPrincipal.setLayout(PanelPrincipalLayout);
        PanelPrincipalLayout.setHorizontalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 617, Short.MAX_VALUE)
        );
        PanelPrincipalLayout.setVerticalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 383, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(0, 204, 51));

        txtNombre.setForeground(new java.awt.Color(240, 240, 240));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
        );

        m_Registro.setText("REGISTRO");

        mr_cliente.setText("Cliente");
        mr_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mr_clienteActionPerformed(evt);
            }
        });
        m_Registro.add(mr_cliente);

        mr_producto.setText("Producto");
        mr_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mr_productoActionPerformed(evt);
            }
        });
        m_Registro.add(mr_producto);

        mr_proveedor.setText("Proveedor");
        m_Registro.add(mr_proveedor);

        jMenuBar1.add(m_Registro);

        m_Operacion.setText("OPERACIONES");

        om_venta.setText("Venta");
        om_venta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                om_ventaActionPerformed(evt);
            }
        });
        m_Operacion.add(om_venta);

        om_compra.setText("Compra");
        m_Operacion.add(om_compra);

        om_caja.setText("Caja");
        m_Operacion.add(om_caja);

        om_reserva.setText("Reserva");
        m_Operacion.add(om_reserva);

        jMenuBar1.add(m_Operacion);

        m_Consulta.setText("CONSULTA");

        cm_detalleVenta.setText("Detalle Venta");
        m_Consulta.add(cm_detalleVenta);

        cm_detalleCompra.setText("Detalle Compra");
        cm_detalleCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cm_detalleCompraActionPerformed(evt);
            }
        });
        m_Consulta.add(cm_detalleCompra);

        cm_detalleCliente.setText("Detalle Cliente");
        m_Consulta.add(cm_detalleCliente);

        cm_stock.setText("Stock");
        m_Consulta.add(cm_stock);

        jMenuBar1.add(m_Consulta);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipal)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelPrincipal)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mr_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mr_clienteActionPerformed
        FrmICliente registroCliente = new FrmICliente();
        PanelPrincipal.add(registroCliente);
        registroCliente.setVisible(true);
        
    }//GEN-LAST:event_mr_clienteActionPerformed

    private void mr_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mr_productoActionPerformed
        FrmIProducto registroProducto = new FrmIProducto();
        PanelPrincipal.add(registroProducto);
        registroProducto.setVisible(true);
        m_Consulta.setVisible(false);
        m_Operacion.setVisible(false);
        
    }//GEN-LAST:event_mr_productoActionPerformed

    private void om_ventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_om_ventaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_om_ventaActionPerformed

    private void cm_detalleCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cm_detalleCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cm_detalleCompraActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane PanelPrincipal;
    private javax.swing.JMenuItem cm_detalleCliente;
    private javax.swing.JMenuItem cm_detalleCompra;
    private javax.swing.JMenuItem cm_detalleVenta;
    private javax.swing.JMenuItem cm_stock;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenu m_Consulta;
    private javax.swing.JMenu m_Operacion;
    private javax.swing.JMenu m_Registro;
    private javax.swing.JMenuItem mr_cliente;
    private javax.swing.JMenuItem mr_producto;
    private javax.swing.JMenuItem mr_proveedor;
    private javax.swing.JMenuItem om_caja;
    private javax.swing.JMenuItem om_compra;
    private javax.swing.JMenuItem om_reserva;
    private javax.swing.JMenuItem om_venta;
    private javax.swing.JLabel txtNombre;
    // End of variables declaration//GEN-END:variables
}
