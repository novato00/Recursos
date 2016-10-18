/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.model.dao;

import cl.model.pojos.Producto;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import java.util.List;
/**
 *
 * @author Daniel
 */
public class ProductoDAO {
    public void ingresarProducto(Producto p){
        SessionFactory sf = null;
        Session sesion = null;
        Transaction tx = null;
        try {
            sf = HibernateUtil.getSessionFactory();
            sesion = sf.openSession();
            tx = sesion.beginTransaction();
            sesion.save(p);
            tx.commit();
            sesion.close();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException("No se pudo cargar el producto!!!");
        }
    }
    public String consultarProducto(int codigo){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session  sesion = sf.openSession();
        Producto p = (Producto) sesion.get(Producto.class, codigo);
        sesion.close();
        if (p!=null) {
            return "El producto de codigo "+p.getCodigo()+ "cuyo nombre es "+p.getNombre()+
                    "cuesta "+p.getPrecio()+ " tiene "+p.getStock()+"y su descripcion es "+p.getDescripcion();
        }
        else{
            return "El producto no existe!!!";
        }
    }
    public List<Producto> verProductos(){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session  sesion = sf.openSession();
        Query query = sesion.createQuery("from Producto");
        List<Producto> lista = query.list();
        return lista; 
    }
}
