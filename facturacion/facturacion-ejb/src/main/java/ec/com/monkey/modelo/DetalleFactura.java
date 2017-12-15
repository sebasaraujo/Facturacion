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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author luisp.araujo
 */
@Entity
@Table(name = "detalle_factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleFactura.findAll", query = "SELECT d FROM DetalleFactura d")
    , @NamedQuery(name = "DetalleFactura.findByCodigoDf", query = "SELECT d FROM DetalleFactura d WHERE d.codigoDf = :codigoDf")
    , @NamedQuery(name = "DetalleFactura.findByCantidadDf", query = "SELECT d FROM DetalleFactura d WHERE d.cantidadDf = :cantidadDf")
    , @NamedQuery(name = "DetalleFactura.findByValorDf", query = "SELECT d FROM DetalleFactura d WHERE d.valorDf = :valorDf")
    , @NamedQuery(name = "DetalleFactura.findByValorImpuestoDf", query = "SELECT d FROM DetalleFactura d WHERE d.valorImpuestoDf = :valorImpuestoDf")
    , @NamedQuery(name = "DetalleFactura.findByValorTotalDf", query = "SELECT d FROM DetalleFactura d WHERE d.valorTotalDf = :valorTotalDf")
    , @NamedQuery(name = "DetalleFactura.findByUsuarioActDf", query = "SELECT d FROM DetalleFactura d WHERE d.usuarioActDf = :usuarioActDf")
    , @NamedQuery(name = "DetalleFactura.findByFechaActDf", query = "SELECT d FROM DetalleFactura d WHERE d.fechaActDf = :fechaActDf")})
public class DetalleFactura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_df")
    private Integer codigoDf;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_df")
    private int cantidadDf;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_df")
    private double valorDf;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_impuesto_df")
    private double valorImpuestoDf;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_total_df")
    private double valorTotalDf;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usuario_act_df")
    private int usuarioActDf;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_act_df")
    @Temporal(TemporalType.DATE)
    private Date fechaActDf;
    @JoinColumn(name = "codigo_fc", referencedColumnName = "codigo_fc")
    @ManyToOne(optional = false)
    private Factura codigoFc;
    @JoinColumn(name = "codigo_pr", referencedColumnName = "codigo_pr")
    @ManyToOne(optional = false)
    private Productos codigoPr;

    public DetalleFactura() {
    }

    public DetalleFactura(Integer codigoDf) {
        this.codigoDf = codigoDf;
    }

    public DetalleFactura(Integer codigoDf, int cantidadDf, double valorDf, double valorImpuestoDf, double valorTotalDf, int usuarioActDf, Date fechaActDf) {
        this.codigoDf = codigoDf;
        this.cantidadDf = cantidadDf;
        this.valorDf = valorDf;
        this.valorImpuestoDf = valorImpuestoDf;
        this.valorTotalDf = valorTotalDf;
        this.usuarioActDf = usuarioActDf;
        this.fechaActDf = fechaActDf;
    }

    public Integer getCodigoDf() {
        return codigoDf;
    }

    public void setCodigoDf(Integer codigoDf) {
        this.codigoDf = codigoDf;
    }

    public int getCantidadDf() {
        return cantidadDf;
    }

    public void setCantidadDf(int cantidadDf) {
        this.cantidadDf = cantidadDf;
    }

    public double getValorDf() {
        return valorDf;
    }

    public void setValorDf(double valorDf) {
        this.valorDf = valorDf;
    }

    public double getValorImpuestoDf() {
        return valorImpuestoDf;
    }

    public void setValorImpuestoDf(double valorImpuestoDf) {
        this.valorImpuestoDf = valorImpuestoDf;
    }

    public double getValorTotalDf() {
        return valorTotalDf;
    }

    public void setValorTotalDf(double valorTotalDf) {
        this.valorTotalDf = valorTotalDf;
    }

    public int getUsuarioActDf() {
        return usuarioActDf;
    }

    public void setUsuarioActDf(int usuarioActDf) {
        this.usuarioActDf = usuarioActDf;
    }

    public Date getFechaActDf() {
        return fechaActDf;
    }

    public void setFechaActDf(Date fechaActDf) {
        this.fechaActDf = fechaActDf;
    }

    public Factura getCodigoFc() {
        return codigoFc;
    }

    public void setCodigoFc(Factura codigoFc) {
        this.codigoFc = codigoFc;
    }

    public Productos getCodigoPr() {
        return codigoPr;
    }

    public void setCodigoPr(Productos codigoPr) {
        this.codigoPr = codigoPr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoDf != null ? codigoDf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleFactura)) {
            return false;
        }
        DetalleFactura other = (DetalleFactura) object;
        if ((this.codigoDf == null && other.codigoDf != null) || (this.codigoDf != null && !this.codigoDf.equals(other.codigoDf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.monkey.modelo.DetalleFactura[ codigoDf=" + codigoDf + " ]";
    }
    
}
