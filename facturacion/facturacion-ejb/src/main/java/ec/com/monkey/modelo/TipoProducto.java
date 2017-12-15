/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.monkey.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author luisp.araujo
 */
@Entity
@Table(name = "tipo_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoProducto.findAll", query = "SELECT t FROM TipoProducto t")
    , @NamedQuery(name = "TipoProducto.findByCodigoTp", query = "SELECT t FROM TipoProducto t WHERE t.codigoTp = :codigoTp")
    , @NamedQuery(name = "TipoProducto.findByNombreTp", query = "SELECT t FROM TipoProducto t WHERE t.nombreTp = :nombreTp")
    , @NamedQuery(name = "TipoProducto.findByEstadoTp", query = "SELECT t FROM TipoProducto t WHERE t.estadoTp = :estadoTp")
    , @NamedQuery(name = "TipoProducto.findByUsuarioActTp", query = "SELECT t FROM TipoProducto t WHERE t.usuarioActTp = :usuarioActTp")
    , @NamedQuery(name = "TipoProducto.findByFechaActTp", query = "SELECT t FROM TipoProducto t WHERE t.fechaActTp = :fechaActTp")})
public class TipoProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_tp")
    private Integer codigoTp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "nombre_tp")
    private String nombreTp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "estado_tp")
    private String estadoTp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usuario_act_tp")
    private int usuarioActTp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_act_tp")
    @Temporal(TemporalType.DATE)
    private Date fechaActTp;
    @JoinColumn(name = "codigo_em", referencedColumnName = "codigo_em")
    @ManyToOne(optional = false)
    private Empresa codigoEm;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTp")
    private List<Productos> productosList;

    public TipoProducto() {
    }

    public TipoProducto(Integer codigoTp) {
        this.codigoTp = codigoTp;
    }

    public TipoProducto(Integer codigoTp, String nombreTp, String estadoTp, int usuarioActTp, Date fechaActTp) {
        this.codigoTp = codigoTp;
        this.nombreTp = nombreTp;
        this.estadoTp = estadoTp;
        this.usuarioActTp = usuarioActTp;
        this.fechaActTp = fechaActTp;
    }

    public Integer getCodigoTp() {
        return codigoTp;
    }

    public void setCodigoTp(Integer codigoTp) {
        this.codigoTp = codigoTp;
    }

    public String getNombreTp() {
        return nombreTp;
    }

    public void setNombreTp(String nombreTp) {
        this.nombreTp = nombreTp;
    }

    public String getEstadoTp() {
        return estadoTp;
    }

    public void setEstadoTp(String estadoTp) {
        this.estadoTp = estadoTp;
    }

    public int getUsuarioActTp() {
        return usuarioActTp;
    }

    public void setUsuarioActTp(int usuarioActTp) {
        this.usuarioActTp = usuarioActTp;
    }

    public Date getFechaActTp() {
        return fechaActTp;
    }

    public void setFechaActTp(Date fechaActTp) {
        this.fechaActTp = fechaActTp;
    }

    public Empresa getCodigoEm() {
        return codigoEm;
    }

    public void setCodigoEm(Empresa codigoEm) {
        this.codigoEm = codigoEm;
    }

    @XmlTransient
    public List<Productos> getProductosList() {
        return productosList;
    }

    public void setProductosList(List<Productos> productosList) {
        this.productosList = productosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoTp != null ? codigoTp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoProducto)) {
            return false;
        }
        TipoProducto other = (TipoProducto) object;
        if ((this.codigoTp == null && other.codigoTp != null) || (this.codigoTp != null && !this.codigoTp.equals(other.codigoTp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.monkey.modelo.TipoProducto[ codigoTp=" + codigoTp + " ]";
    }
    
}
