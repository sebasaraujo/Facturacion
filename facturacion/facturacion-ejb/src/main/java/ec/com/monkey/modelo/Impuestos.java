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
@Table(name = "impuestos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Impuestos.findAll", query = "SELECT i FROM Impuestos i")
    , @NamedQuery(name = "Impuestos.findByCodigoIp", query = "SELECT i FROM Impuestos i WHERE i.codigoIp = :codigoIp")
    , @NamedQuery(name = "Impuestos.findByNombreIp", query = "SELECT i FROM Impuestos i WHERE i.nombreIp = :nombreIp")
    , @NamedQuery(name = "Impuestos.findByValorIp", query = "SELECT i FROM Impuestos i WHERE i.valorIp = :valorIp")
    , @NamedQuery(name = "Impuestos.findByEstadoIp", query = "SELECT i FROM Impuestos i WHERE i.estadoIp = :estadoIp")
    , @NamedQuery(name = "Impuestos.findByUsuarioActIp", query = "SELECT i FROM Impuestos i WHERE i.usuarioActIp = :usuarioActIp")
    , @NamedQuery(name = "Impuestos.findByFechaActIp", query = "SELECT i FROM Impuestos i WHERE i.fechaActIp = :fechaActIp")
    , @NamedQuery(name = "Impuestos.findByTipoImpuesto", query = "SELECT i FROM Impuestos i WHERE i.tipoImpuesto = :tipoImpuesto")})
public class Impuestos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_ip")
    private Integer codigoIp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "nombre_ip")
    private String nombreIp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_ip")
    private double valorIp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "estado_ip")
    private String estadoIp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usuario_act_ip")
    private int usuarioActIp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_act_ip")
    @Temporal(TemporalType.DATE)
    private Date fechaActIp;
    @Column(name = "tipo_impuesto")
    private Integer tipoImpuesto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoIp")
    private List<ImpuestoFactura> impuestoFacturaList;

    public Impuestos() {
    }

    public Impuestos(Integer codigoIp) {
        this.codigoIp = codigoIp;
    }

    public Impuestos(Integer codigoIp, String nombreIp, double valorIp, String estadoIp, int usuarioActIp, Date fechaActIp) {
        this.codigoIp = codigoIp;
        this.nombreIp = nombreIp;
        this.valorIp = valorIp;
        this.estadoIp = estadoIp;
        this.usuarioActIp = usuarioActIp;
        this.fechaActIp = fechaActIp;
    }

    public Integer getCodigoIp() {
        return codigoIp;
    }

    public void setCodigoIp(Integer codigoIp) {
        this.codigoIp = codigoIp;
    }

    public String getNombreIp() {
        return nombreIp;
    }

    public void setNombreIp(String nombreIp) {
        this.nombreIp = nombreIp;
    }

    public double getValorIp() {
        return valorIp;
    }

    public void setValorIp(double valorIp) {
        this.valorIp = valorIp;
    }

    public String getEstadoIp() {
        return estadoIp;
    }

    public void setEstadoIp(String estadoIp) {
        this.estadoIp = estadoIp;
    }

    public int getUsuarioActIp() {
        return usuarioActIp;
    }

    public void setUsuarioActIp(int usuarioActIp) {
        this.usuarioActIp = usuarioActIp;
    }

    public Date getFechaActIp() {
        return fechaActIp;
    }

    public void setFechaActIp(Date fechaActIp) {
        this.fechaActIp = fechaActIp;
    }

    public Integer getTipoImpuesto() {
        return tipoImpuesto;
    }

    public void setTipoImpuesto(Integer tipoImpuesto) {
        this.tipoImpuesto = tipoImpuesto;
    }

    @XmlTransient
    public List<ImpuestoFactura> getImpuestoFacturaList() {
        return impuestoFacturaList;
    }

    public void setImpuestoFacturaList(List<ImpuestoFactura> impuestoFacturaList) {
        this.impuestoFacturaList = impuestoFacturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoIp != null ? codigoIp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Impuestos)) {
            return false;
        }
        Impuestos other = (Impuestos) object;
        if ((this.codigoIp == null && other.codigoIp != null) || (this.codigoIp != null && !this.codigoIp.equals(other.codigoIp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.monkey.modelo.Impuestos[ codigoIp=" + codigoIp + " ]";
    }
    
}
