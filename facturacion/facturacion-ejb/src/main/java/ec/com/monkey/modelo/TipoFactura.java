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
@Table(name = "tipo_factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoFactura.findAll", query = "SELECT t FROM TipoFactura t")
    , @NamedQuery(name = "TipoFactura.findByCodigoTf", query = "SELECT t FROM TipoFactura t WHERE t.codigoTf = :codigoTf")
    , @NamedQuery(name = "TipoFactura.findByNombreTf", query = "SELECT t FROM TipoFactura t WHERE t.nombreTf = :nombreTf")
    , @NamedQuery(name = "TipoFactura.findByEstadoTf", query = "SELECT t FROM TipoFactura t WHERE t.estadoTf = :estadoTf")
    , @NamedQuery(name = "TipoFactura.findByUsuarioActTf", query = "SELECT t FROM TipoFactura t WHERE t.usuarioActTf = :usuarioActTf")
    , @NamedQuery(name = "TipoFactura.findByFechaActTf", query = "SELECT t FROM TipoFactura t WHERE t.fechaActTf = :fechaActTf")})
public class TipoFactura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_tf")
    private Integer codigoTf;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "nombre_tf")
    private String nombreTf;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "estado_tf")
    private String estadoTf;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usuario_act_tf")
    private int usuarioActTf;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_act_tf")
    @Temporal(TemporalType.DATE)
    private Date fechaActTf;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTf")
    private List<ImpuestoFactura> impuestoFacturaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTf")
    private List<Factura> facturaList;

    public TipoFactura() {
    }

    public TipoFactura(Integer codigoTf) {
        this.codigoTf = codigoTf;
    }

    public TipoFactura(Integer codigoTf, String nombreTf, String estadoTf, int usuarioActTf, Date fechaActTf) {
        this.codigoTf = codigoTf;
        this.nombreTf = nombreTf;
        this.estadoTf = estadoTf;
        this.usuarioActTf = usuarioActTf;
        this.fechaActTf = fechaActTf;
    }

    public Integer getCodigoTf() {
        return codigoTf;
    }

    public void setCodigoTf(Integer codigoTf) {
        this.codigoTf = codigoTf;
    }

    public String getNombreTf() {
        return nombreTf;
    }

    public void setNombreTf(String nombreTf) {
        this.nombreTf = nombreTf;
    }

    public String getEstadoTf() {
        return estadoTf;
    }

    public void setEstadoTf(String estadoTf) {
        this.estadoTf = estadoTf;
    }

    public int getUsuarioActTf() {
        return usuarioActTf;
    }

    public void setUsuarioActTf(int usuarioActTf) {
        this.usuarioActTf = usuarioActTf;
    }

    public Date getFechaActTf() {
        return fechaActTf;
    }

    public void setFechaActTf(Date fechaActTf) {
        this.fechaActTf = fechaActTf;
    }

    @XmlTransient
    public List<ImpuestoFactura> getImpuestoFacturaList() {
        return impuestoFacturaList;
    }

    public void setImpuestoFacturaList(List<ImpuestoFactura> impuestoFacturaList) {
        this.impuestoFacturaList = impuestoFacturaList;
    }

    @XmlTransient
    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoTf != null ? codigoTf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoFactura)) {
            return false;
        }
        TipoFactura other = (TipoFactura) object;
        if ((this.codigoTf == null && other.codigoTf != null) || (this.codigoTf != null && !this.codigoTf.equals(other.codigoTf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.monkey.modelo.TipoFactura[ codigoTf=" + codigoTf + " ]";
    }
    
}
