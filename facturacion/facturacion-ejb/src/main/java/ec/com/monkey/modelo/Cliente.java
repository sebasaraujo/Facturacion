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
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
    , @NamedQuery(name = "Cliente.findByCodigoCl", query = "SELECT c FROM Cliente c WHERE c.codigoCl = :codigoCl")
    , @NamedQuery(name = "Cliente.findByNombreCl", query = "SELECT c FROM Cliente c WHERE c.nombreCl = :nombreCl")
    , @NamedQuery(name = "Cliente.findByApellidoCl", query = "SELECT c FROM Cliente c WHERE c.apellidoCl = :apellidoCl")
    , @NamedQuery(name = "Cliente.findByIdentificacionCl", query = "SELECT c FROM Cliente c WHERE c.identificacionCl = :identificacionCl")
    , @NamedQuery(name = "Cliente.findByCorreoCl", query = "SELECT c FROM Cliente c WHERE c.correoCl = :correoCl")
    , @NamedQuery(name = "Cliente.findByDireccionCl", query = "SELECT c FROM Cliente c WHERE c.direccionCl = :direccionCl")
    , @NamedQuery(name = "Cliente.findByTelefonoCl", query = "SELECT c FROM Cliente c WHERE c.telefonoCl = :telefonoCl")
    , @NamedQuery(name = "Cliente.findByEstadoCl", query = "SELECT c FROM Cliente c WHERE c.estadoCl = :estadoCl")
    , @NamedQuery(name = "Cliente.findByUsuarioActCl", query = "SELECT c FROM Cliente c WHERE c.usuarioActCl = :usuarioActCl")
    , @NamedQuery(name = "Cliente.findByFechaActCl", query = "SELECT c FROM Cliente c WHERE c.fechaActCl = :fechaActCl")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_cl")
    private Integer codigoCl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "nombre_cl")
    private String nombreCl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "apellido_cl")
    private String apellidoCl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "identificacion_cl")
    private String identificacionCl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "correo_cl")
    private String correoCl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "direccion_cl")
    private String direccionCl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "telefono_cl")
    private String telefonoCl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "estado_cl")
    private String estadoCl;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usuario_act_cl")
    private int usuarioActCl;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_act_cl")
    @Temporal(TemporalType.DATE)
    private Date fechaActCl;
    @JoinColumn(name = "codigo_ci", referencedColumnName = "codigo_ci")
    @ManyToOne(optional = false)
    private Ciudad codigoCi;
    @JoinColumn(name = "codigo_ti", referencedColumnName = "codigo_ti")
    @ManyToOne(optional = false)
    private TipoIdentificacion codigoTi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoCliente")
    private List<Factura> facturaList;

    public Cliente() {
    }

    public Cliente(Integer codigoCl) {
        this.codigoCl = codigoCl;
    }

    public Cliente(Integer codigoCl, String nombreCl, String apellidoCl, String identificacionCl, String correoCl, String direccionCl, String telefonoCl, String estadoCl, int usuarioActCl, Date fechaActCl) {
        this.codigoCl = codigoCl;
        this.nombreCl = nombreCl;
        this.apellidoCl = apellidoCl;
        this.identificacionCl = identificacionCl;
        this.correoCl = correoCl;
        this.direccionCl = direccionCl;
        this.telefonoCl = telefonoCl;
        this.estadoCl = estadoCl;
        this.usuarioActCl = usuarioActCl;
        this.fechaActCl = fechaActCl;
    }

    public Integer getCodigoCl() {
        return codigoCl;
    }

    public void setCodigoCl(Integer codigoCl) {
        this.codigoCl = codigoCl;
    }

    public String getNombreCl() {
        return nombreCl;
    }

    public void setNombreCl(String nombreCl) {
        this.nombreCl = nombreCl;
    }

    public String getApellidoCl() {
        return apellidoCl;
    }

    public void setApellidoCl(String apellidoCl) {
        this.apellidoCl = apellidoCl;
    }

    public String getIdentificacionCl() {
        return identificacionCl;
    }

    public void setIdentificacionCl(String identificacionCl) {
        this.identificacionCl = identificacionCl;
    }

    public String getCorreoCl() {
        return correoCl;
    }

    public void setCorreoCl(String correoCl) {
        this.correoCl = correoCl;
    }

    public String getDireccionCl() {
        return direccionCl;
    }

    public void setDireccionCl(String direccionCl) {
        this.direccionCl = direccionCl;
    }

    public String getTelefonoCl() {
        return telefonoCl;
    }

    public void setTelefonoCl(String telefonoCl) {
        this.telefonoCl = telefonoCl;
    }

    public String getEstadoCl() {
        return estadoCl;
    }

    public void setEstadoCl(String estadoCl) {
        this.estadoCl = estadoCl;
    }

    public int getUsuarioActCl() {
        return usuarioActCl;
    }

    public void setUsuarioActCl(int usuarioActCl) {
        this.usuarioActCl = usuarioActCl;
    }

    public Date getFechaActCl() {
        return fechaActCl;
    }

    public void setFechaActCl(Date fechaActCl) {
        this.fechaActCl = fechaActCl;
    }

    public Ciudad getCodigoCi() {
        return codigoCi;
    }

    public void setCodigoCi(Ciudad codigoCi) {
        this.codigoCi = codigoCi;
    }

    public TipoIdentificacion getCodigoTi() {
        return codigoTi;
    }

    public void setCodigoTi(TipoIdentificacion codigoTi) {
        this.codigoTi = codigoTi;
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
        hash += (codigoCl != null ? codigoCl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.codigoCl == null && other.codigoCl != null) || (this.codigoCl != null && !this.codigoCl.equals(other.codigoCl))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.monkey.modelo.Cliente[ codigoCl=" + codigoCl + " ]";
    }
    
}
