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
@Table(name = "empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e")
    , @NamedQuery(name = "Empresa.findByCodigoEm", query = "SELECT e FROM Empresa e WHERE e.codigoEm = :codigoEm")
    , @NamedQuery(name = "Empresa.findByNombreEm", query = "SELECT e FROM Empresa e WHERE e.nombreEm = :nombreEm")
    , @NamedQuery(name = "Empresa.findByDireccion", query = "SELECT e FROM Empresa e WHERE e.direccion = :direccion")
    , @NamedQuery(name = "Empresa.findByTelefonoEm", query = "SELECT e FROM Empresa e WHERE e.telefonoEm = :telefonoEm")
    , @NamedQuery(name = "Empresa.findByCorreoEm", query = "SELECT e FROM Empresa e WHERE e.correoEm = :correoEm")
    , @NamedQuery(name = "Empresa.findByRuc", query = "SELECT e FROM Empresa e WHERE e.ruc = :ruc")
    , @NamedQuery(name = "Empresa.findByEstadoEm", query = "SELECT e FROM Empresa e WHERE e.estadoEm = :estadoEm")
    , @NamedQuery(name = "Empresa.findByUsuarioActEm", query = "SELECT e FROM Empresa e WHERE e.usuarioActEm = :usuarioActEm")
    , @NamedQuery(name = "Empresa.findByFechaActEm", query = "SELECT e FROM Empresa e WHERE e.fechaActEm = :fechaActEm")})
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_em")
    private Integer codigoEm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "nombre_em")
    private String nombreEm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "telefono_em")
    private String telefonoEm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "correo_em")
    private String correoEm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "ruc")
    private String ruc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "estado_em")
    private String estadoEm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usuario_act_em")
    private int usuarioActEm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_act_em")
    @Temporal(TemporalType.DATE)
    private Date fechaActEm;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoEm")
    private List<Local> localList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoEm")
    private List<Factura> facturaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoEm")
    private List<TipoProducto> tipoProductoList;
    @JoinColumn(name = "codigo_pa", referencedColumnName = "codigo_pa")
    @ManyToOne(optional = false)
    private Pais codigoPa;

    public Empresa() {
    }

    public Empresa(Integer codigoEm) {
        this.codigoEm = codigoEm;
    }

    public Empresa(Integer codigoEm, String nombreEm, String direccion, String telefonoEm, String correoEm, String ruc, String estadoEm, int usuarioActEm, Date fechaActEm) {
        this.codigoEm = codigoEm;
        this.nombreEm = nombreEm;
        this.direccion = direccion;
        this.telefonoEm = telefonoEm;
        this.correoEm = correoEm;
        this.ruc = ruc;
        this.estadoEm = estadoEm;
        this.usuarioActEm = usuarioActEm;
        this.fechaActEm = fechaActEm;
    }

    public Integer getCodigoEm() {
        return codigoEm;
    }

    public void setCodigoEm(Integer codigoEm) {
        this.codigoEm = codigoEm;
    }

    public String getNombreEm() {
        return nombreEm;
    }

    public void setNombreEm(String nombreEm) {
        this.nombreEm = nombreEm;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefonoEm() {
        return telefonoEm;
    }

    public void setTelefonoEm(String telefonoEm) {
        this.telefonoEm = telefonoEm;
    }

    public String getCorreoEm() {
        return correoEm;
    }

    public void setCorreoEm(String correoEm) {
        this.correoEm = correoEm;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getEstadoEm() {
        return estadoEm;
    }

    public void setEstadoEm(String estadoEm) {
        this.estadoEm = estadoEm;
    }

    public int getUsuarioActEm() {
        return usuarioActEm;
    }

    public void setUsuarioActEm(int usuarioActEm) {
        this.usuarioActEm = usuarioActEm;
    }

    public Date getFechaActEm() {
        return fechaActEm;
    }

    public void setFechaActEm(Date fechaActEm) {
        this.fechaActEm = fechaActEm;
    }

    @XmlTransient
    public List<Local> getLocalList() {
        return localList;
    }

    public void setLocalList(List<Local> localList) {
        this.localList = localList;
    }

    @XmlTransient
    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

    @XmlTransient
    public List<TipoProducto> getTipoProductoList() {
        return tipoProductoList;
    }

    public void setTipoProductoList(List<TipoProducto> tipoProductoList) {
        this.tipoProductoList = tipoProductoList;
    }

    public Pais getCodigoPa() {
        return codigoPa;
    }

    public void setCodigoPa(Pais codigoPa) {
        this.codigoPa = codigoPa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoEm != null ? codigoEm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.codigoEm == null && other.codigoEm != null) || (this.codigoEm != null && !this.codigoEm.equals(other.codigoEm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.monkey.modelo.Empresa[ codigoEm=" + codigoEm + " ]";
    }
    
}
