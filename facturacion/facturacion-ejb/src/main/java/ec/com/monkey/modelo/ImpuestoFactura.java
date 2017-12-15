/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.monkey.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author luisp.araujo
 */
@Entity
@Table(name = "impuesto_factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ImpuestoFactura.findAll", query = "SELECT i FROM ImpuestoFactura i")
    , @NamedQuery(name = "ImpuestoFactura.findByCodigoIf", query = "SELECT i FROM ImpuestoFactura i WHERE i.codigoIf = :codigoIf")
    , @NamedQuery(name = "ImpuestoFactura.findByEstadoIf", query = "SELECT i FROM ImpuestoFactura i WHERE i.estadoIf = :estadoIf")
    , @NamedQuery(name = "ImpuestoFactura.findByUsuarioActIf", query = "SELECT i FROM ImpuestoFactura i WHERE i.usuarioActIf = :usuarioActIf")
    , @NamedQuery(name = "ImpuestoFactura.findByFechaActIf", query = "SELECT i FROM ImpuestoFactura i WHERE i.fechaActIf = :fechaActIf")})
public class ImpuestoFactura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_if")
    private Integer codigoIf;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "estado_if")
    private String estadoIf;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usuario_act_if")
    private int usuarioActIf;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_act_if")
    @Temporal(TemporalType.DATE)
    private Date fechaActIf;
    @JoinColumn(name = "codigo_ip", referencedColumnName = "codigo_ip")
    @ManyToOne(optional = false)
    private Impuestos codigoIp;
    @JoinColumn(name = "codigo_tf", referencedColumnName = "codigo_tf")
    @ManyToOne(optional = false)
    private TipoFactura codigoTf;

    public ImpuestoFactura() {
    }

    public ImpuestoFactura(Integer codigoIf) {
        this.codigoIf = codigoIf;
    }

    public ImpuestoFactura(Integer codigoIf, String estadoIf, int usuarioActIf, Date fechaActIf) {
        this.codigoIf = codigoIf;
        this.estadoIf = estadoIf;
        this.usuarioActIf = usuarioActIf;
        this.fechaActIf = fechaActIf;
    }

    public Integer getCodigoIf() {
        return codigoIf;
    }

    public void setCodigoIf(Integer codigoIf) {
        this.codigoIf = codigoIf;
    }

    public String getEstadoIf() {
        return estadoIf;
    }

    public void setEstadoIf(String estadoIf) {
        this.estadoIf = estadoIf;
    }

    public int getUsuarioActIf() {
        return usuarioActIf;
    }

    public void setUsuarioActIf(int usuarioActIf) {
        this.usuarioActIf = usuarioActIf;
    }

    public Date getFechaActIf() {
        return fechaActIf;
    }

    public void setFechaActIf(Date fechaActIf) {
        this.fechaActIf = fechaActIf;
    }

    public Impuestos getCodigoIp() {
        return codigoIp;
    }

    public void setCodigoIp(Impuestos codigoIp) {
        this.codigoIp = codigoIp;
    }

    public TipoFactura getCodigoTf() {
        return codigoTf;
    }

    public void setCodigoTf(TipoFactura codigoTf) {
        this.codigoTf = codigoTf;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoIf != null ? codigoIf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImpuestoFactura)) {
            return false;
        }
        ImpuestoFactura other = (ImpuestoFactura) object;
        if ((this.codigoIf == null && other.codigoIf != null) || (this.codigoIf != null && !this.codigoIf.equals(other.codigoIf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.monkey.modelo.ImpuestoFactura[ codigoIf=" + codigoIf + " ]";
    }
    
}
